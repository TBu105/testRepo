/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.employee.service;

import com.mycompany.employee.model.Department;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author huydao
 */
public interface DepartmentService {
    public List<Department> getAllDepartments() throws SQLException;
}
