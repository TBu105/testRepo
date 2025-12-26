/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.employee.service.serviceImpl;

import com.mycompany.employee.dao.daoImpl.DepartmentDAOImpl;
import com.mycompany.employee.factory.DaoFactory;
import com.mycompany.employee.model.Department;
import com.mycompany.employee.service.DepartmentService;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author huydao
 */
public class DepartmentServiceImpl implements DepartmentService{
    DepartmentDAOImpl de = DaoFactory.getDepartmentDAO();
    
    @Override
    public List<Department> getAllDepartments() throws SQLException{
        return de.getAllDepartment();
    }
}
