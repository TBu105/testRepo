/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.employee.factory;

import com.mycompany.employee.service.serviceImpl.DepartmentServiceImpl;
import com.mycompany.employee.service.serviceImpl.EmployeeServiceImpl;

/**
 *
 * @author huydao
 */
public class ServiceFactory {
    private static DepartmentServiceImpl de = new DepartmentServiceImpl();
        private static EmployeeServiceImpl em = new EmployeeServiceImpl();

    
    public static DepartmentServiceImpl getDepartmentService() {
        return de;
    }
    
    public static EmployeeServiceImpl getEmployeeService() {
        return em;
    }
}
