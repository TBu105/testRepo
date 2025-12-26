/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.employee.dao.daoImpl;

import com.mycompany.employee.dao.EmployeeDAO;
import com.mycompany.employee.dto.EmployeeDetailDTO;
import com.mycompany.employee.exception.DuplicateEmailException;
import com.mycompany.employee.model.Employee;
import com.mycompany.employee.utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class EmployeeDAOImpl implements EmployeeDAO{    
    public EmployeeDAOImpl() {}
    
    @Override
    public List<EmployeeDetailDTO> getAllEmployees(int departmentId) throws SQLException {
        
        List<EmployeeDetailDTO> employees = new ArrayList<>();
        
        String sql = "SELECT e.*, d.DEPARTMENT_NAME, d.LOCATION " +
                "FROM EMPLOYEES e " +
                "INNER JOIN DEPARTMENTS d ON e.DEPARTMENT_ID = d.DEPARTMENT_ID " +
                "WHERE d.DEPARTMENT_ID = ? AND e.STATUS = 1";
        
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);) {
            
            ps.setInt(1, departmentId);
            
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    employees.add(mapResultSetToEmployeeDTO(rs));
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        
        return employees;
    }
    
    @Override
    public Optional<Employee> getEmployeeById(int employeeId) throws SQLException{
        String sql = "SELECT e.* FROM EMPLOYEE e WHERE e.EMPLOYEE_ID = ?";
        
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, employeeId);
            
            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToEmployee(rs));
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
            throw e;
        }
        
        return Optional.empty();
    }
    
    @Override
    public int addEmployee(Employee emp) throws SQLException, DuplicateEmailException{
        String sql = "INSERT INTO EMPLOYEES " +
                "(FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_TITLE, SALARY, DEPARTMENT_ID, MANAGER_ID, STATUS) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        int generatedId = -1;
        
        try (Connection conn = DBConnection.getConnection()) {
            
            conn.setAutoCommit(false);
            
            try(PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, emp.getFirstName());
                ps.setString(2, emp.getLastName());
                ps.setString(3, emp.getEmail());
                ps.setString(4, emp.getPhoneNumber());
                
                if (emp.getHireDate() != null) {
                    ps.setTimestamp(5, Timestamp.valueOf(emp.getHireDate()));
                } else {
                    ps.setNull(5, Types.TIMESTAMP); 
                }
                
                ps.setString(6, emp.getJobTitle());
                ps.setInt(7, emp.getSalary());
            
                int departmentId = emp.getDepartmentId();
                if(departmentId > 0) ps.setInt(8, departmentId);
                else ps.setNull(8, Types.INTEGER);

                Integer managerId = emp.getManagerId();
                if(managerId != null && managerId > 0) ps.setInt(9, managerId);
                else ps.setNull(9, Types.INTEGER);

                ps.setInt(10, emp.getStatus());

                int rowAffected = ps.executeUpdate();

                if (rowAffected > 0) {
                    try (ResultSet rs = ps.getGeneratedKeys()) {
                        if (rs.next()) {
                            generatedId = rs.getInt(1);
                        }
                    }

                    conn.commit();
                } else {
                    conn.rollback();
                    }
            } catch(SQLException e) {
                conn.rollback();
            
                if (e.getErrorCode() == 1062 && e.getMessage().contains("EMAIL")) {
                    throw new DuplicateEmailException("Email " + emp.getEmail() + " đã tồn tại trong hệ thống.");
                }   
                throw e;
            }      
        }
        
        return generatedId;
    }
    
    @Override
    public Optional<Integer> findManagerIdByDepartment(int departmentId) throws SQLException{
        String sql = "SELECT e.EMPLOYEE_ID " +
                "FROM EMPLOYEES e " +
                "WHERE DEPARTMENT_ID = ? AND JOB_TITLE LIKE ?";
        
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            
            System.out.println("DAOImpl findManagerIdByDepartment departmentId: " + departmentId);

            
            ps.setInt(1, departmentId);
            ps.setString(2, "%Manager%");
            
            try(ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    System.out.println("DAOImpl findManagerIdByDepartment managerId: " + rs.getInt("EMPLOYEE_ID"));
                    return Optional.of(rs.getInt("EMPLOYEE_ID"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        
        return Optional.empty();
    }
    
    private EmployeeDetailDTO mapResultSetToEmployeeDTO (ResultSet rs) throws SQLException {
        EmployeeDetailDTO emp = new EmployeeDetailDTO();
        emp.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
        emp.setFirstName(rs.getString("FIRST_NAME"));
        emp.setLastName(rs.getString("LAST_NAME"));
        emp.setEmail(rs.getString("EMAIL"));
        emp.setPhoneNumber(rs.getString("PHONE_NUMBER"));
        emp.setHireDate(rs.getObject("HIRE_DATE", LocalDateTime.class));
        emp.setJobTitle(rs.getString("JOB_TITLE"));
        emp.setSalary(rs.getInt("SALARY"));
        emp.setDepartmentId(rs.getInt("DEPARTMENT_ID"));
        emp.setStatus(rs.getInt("STATUS"));
        emp.setCreatedDate(rs.getObject("CREATED_DATE", LocalDateTime.class));
        emp.setModifiedDate(rs.getObject("MODIFIED_DATE", LocalDateTime.class));
        emp.setDepartmentName(rs.getString("DEPARTMENT_NAME"));
        emp.setDepartmentLocation(rs.getString("LOCATION"));
        
        int managerId = rs.getInt("MANAGER_ID");
        if (!rs.wasNull()) {
            emp.setManagerId(managerId);
        }
        
        return emp;
    }
    
    private Employee mapResultSetToEmployee (ResultSet rs) throws SQLException {
        Employee emp = new Employee();
        emp.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
        emp.setFirstName(rs.getString("FIRST_NAME"));
        emp.setLastName(rs.getString("LAST_NAME"));
        emp.setEmail(rs.getString("EMAIL"));
        emp.setPhoneNumber(rs.getString("PHONE_NUMBER"));
        emp.setEmail(rs.getString("EMAIL"));
        emp.setHireDate(rs.getObject("HIRE_DATE", LocalDateTime.class));
        emp.setJobTitle(rs.getString("JOB_TITLE"));
        emp.setSalary(rs.getInt("SALARY"));
        emp.setDepartmentId(rs.getInt("DEPARTMENT_ID"));
        emp.setStatus(rs.getInt("STATUS"));
        emp.setCreatedDate(rs.getObject("CREATED_DATE", LocalDateTime.class));
        emp.setModifiedDate(rs.getObject("MODIFIED_DATE", LocalDateTime.class));
        
        int managerId = rs.getInt("MANAGER_ID");
        if (!rs.wasNull()) {
            emp.setManagerId(managerId);
        }
        
        return emp;
    }
}
