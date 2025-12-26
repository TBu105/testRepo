/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.employee.dao;

import com.mycompany.employee.dto.EmployeeDetailDTO;
import com.mycompany.employee.exception.DuplicateEmailException;
import com.mycompany.employee.model.Employee;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author huydao
 */
public interface EmployeeDAO {
    public List<EmployeeDetailDTO> getAllEmployees (int departmentId) throws SQLException;
    public Optional<Employee> getEmployeeById (int employeeId) throws SQLException;
    public int addEmployee(Employee employee) throws SQLException, DuplicateEmailException;
    public Optional<Integer> findManagerIdByDepartment(int departmentId) throws SQLException;
}
