/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.employee.factory;

import com.mycompany.employee.dao.daoImpl.DepartmentDAOImpl;
import com.mycompany.employee.dao.daoImpl.EmployeeDAOImpl;
import com.mycompany.employee.utils.DBConnection;
import javax.sql.DataSource;

/**
 *
 * @author huydao
 */
public class DaoFactory {
    private static EmployeeDAOImpl emp = new EmployeeDAOImpl();
    private static DepartmentDAOImpl de = new DepartmentDAOImpl();

    
    public static EmployeeDAOImpl getEmployeeDAO() {
        return emp;
    }
    
    public static DepartmentDAOImpl getDepartmentDAO() {
        return de;
    }
}
