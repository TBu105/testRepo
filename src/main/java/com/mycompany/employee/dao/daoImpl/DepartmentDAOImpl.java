/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.employee.dao.daoImpl;

import com.mycompany.employee.dao.DepartmentDAO;
import com.mycompany.employee.model.Department;
import com.mycompany.employee.utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huydao
 */
public class DepartmentDAOImpl implements DepartmentDAO{
    public DepartmentDAOImpl() {}
    
    @Override
    public List<Department> getAllDepartment() throws SQLException{
        List<Department> de = new ArrayList<>();
        
        String sql = "SELECT * FROM DEPARTMENTS";
        
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            
            while(rs.next()) {
                de.add(mapResultSetToDepartment(rs));
            }
        }
        
        return de;
    }
    
    private Department mapResultSetToDepartment(ResultSet rs) throws SQLException{
        Department de = new Department();
        
        de.setDepartmentId(rs.getInt("DEPARTMENT_ID"));
        de.setDepartmentName(rs.getString("DEPARTMENT_NAME"));
        de.setLocation(rs.getString("LOCATION"));
        de.setCreatedDate(rs.getObject("CREATED_DATE", LocalDateTime.class));
        
        return de;
    }
}
