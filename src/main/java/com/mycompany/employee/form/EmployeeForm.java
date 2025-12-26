/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.employee.form;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author huydao
 */
public class EmployeeForm extends ActionForm{
    private String employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String hireDate;
    private String jobTitle;
    private int salary;
    private int status;
    private Integer departmentId;
    private int managerId;
    
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (firstName == null || firstName.length() < 1) {
            errors.add("firstName", new ActionMessage("error.employee.firstName.required"));
        }
        if (lastName == null || lastName.length() < 1) {
            errors.add("lastName", new ActionMessage("error.employee.lastName.required"));
        }
        if (email == null || email.length() < 1) {
            errors.add("email", new ActionMessage("error.employee.email.required"));
        }
        if (phoneNumber == null || phoneNumber.length() < 1) {
            errors.add("phoNumber", new ActionMessage("error.employee.phoneNumber.required"));
        }
        if (salary < 1000 || salary > 999999) {
            errors.add("salary", new ActionMessage("error.employee.salary.required"));
        }
        if (jobTitle == null || jobTitle.length() < 1) {
            errors.add("jobTitle", new ActionMessage("error.employee.jobTitle.required"));
        }
        return errors;
    }
    
    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        this.firstName = "";
        this.lastName = "";  
        this.email = "";
        this.phoneNumber = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.hireDate = sdf.format(new Date());
        this.jobTitle = "";
        this.salary = 0;
        this.status = 1;
        this.departmentId = 0;
        this.managerId = 0;
    }
}
