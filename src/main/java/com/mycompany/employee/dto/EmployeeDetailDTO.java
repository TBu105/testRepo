/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.employee.dto;

import java.time.LocalDateTime;

/**
 *
 * Join with Department Table
 */

public class EmployeeDetailDTO {
    private int employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDateTime hireDate;
    private String jobTitle;
    private int salary;
    private int departmentId;
    private Integer managerId;
    private int status = 1; // 1: active, 0: inactive
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
//    Department data
    private String departmentName;
    private String departmentLocation;
    
    public EmployeeDetailDTO() {}

    public EmployeeDetailDTO(int employeeId, String firstName, String lastName, String email, String phoneNumber, LocalDateTime hireDate, String jobTitle, int salary, int departmentId, Integer managerId, LocalDateTime createdDate, LocalDateTime modifiedDate, String departmentName, String departmentLocation) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hireDate = hireDate;
        this.jobTitle = jobTitle;
        this.salary = salary;
        this.departmentId = departmentId;
        this.managerId = managerId;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.departmentName = departmentName;
        this.departmentLocation = departmentLocation;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
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

    public LocalDateTime getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDateTime hireDate) {
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

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentLocation() {
        return departmentLocation;
    }

    public void setDepartmentLocation(String departmentLocation) {
        this.departmentLocation = departmentLocation;
    }

    @Override
    public String toString() {
        return "EmployeeDetailDTO{" + "employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phoneNumber=" + phoneNumber + ", hireDate=" + hireDate + ", jobTitle=" + jobTitle + ", salary=" + salary + ", departmentId=" + departmentId + ", managerId=" + managerId + ", status=" + status + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + ", departmentName=" + departmentName + ", departmentLocation=" + departmentLocation + '}';
    }
}
