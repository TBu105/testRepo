/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.employee.dao;

import com.mycompany.employee.model.Department;
import java.sql.SQLException;
import java.util.List;

public interface DepartmentDAO {
    public List<Department> getAllDepartment() throws SQLException;
}
