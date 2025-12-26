/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.employee.service.serviceImpl;

import com.mycompany.employee.dao.daoImpl.EmployeeDAOImpl;
import com.mycompany.employee.dto.EmployeeDetailDTO;
import com.mycompany.employee.exception.DuplicateEmailException;
import com.mycompany.employee.factory.DaoFactory;
import com.mycompany.employee.form.EmployeeForm;
import com.mycompany.employee.model.Employee;
import com.mycompany.employee.service.EmployeeService;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author huydao
 */
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeDAOImpl em = DaoFactory.getEmployeeDAO();
    
    @Override
    public List<EmployeeDetailDTO> getAllEmployees (int departmentId) throws SQLException {
        List<EmployeeDetailDTO> list = em.getAllEmployees(departmentId);
        
        return list;
    }
    
    @Override
    public Optional<Employee> getEmployeeById (int employeeId) throws SQLException {
        Optional<Employee> employee = em.getEmployeeById(employeeId);
        
        return employee;
    }
    
    @Override
    public int addEmployee(EmployeeForm f) throws SQLException, DuplicateEmailException{
        Employee employee = new Employee();
        
        employee.setFirstName(f.getFirstName());
        employee.setLastName(f.getLastName());
        employee.setEmail(f.getEmail());
        employee.setPhoneNumber(f.getPhoneNumber());
        
        if (f.getHireDate() != null && !f.getHireDate().isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            LocalDateTime ldt = LocalDate.parse(f.getHireDate(), formatter)
                             .atStartOfDay();

            employee.setHireDate(ldt);
        }
        
        employee.setJobTitle(f.getJobTitle());
        employee.setSalary(f.getSalary());
        employee.setStatus(f.getStatus());
        employee.setDepartmentId(f.getDepartmentId());
        employee.setManagerId(f.getManagerId());

        
        int emId = em.addEmployee(employee);
        
        return emId;
    }
    
    @Override
    public Optional<Integer> findManagerIdByDepartment (int departmentId) throws SQLException {
        Optional<Integer> managerId = em.findManagerIdByDepartment(departmentId);
        
        return managerId;
    }
}
