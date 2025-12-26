/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.employee.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Department {
    private int departmentId;
    private String departmentName;
    private String location;
    private LocalDateTime createdDate;
    
    public Department() {}
    
    public Department(int departmentId, String departmentName, String location, LocalDateTime createdDate) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.location = location;
        this.createdDate = createdDate;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.departmentId;
        hash = 53 * hash + Objects.hashCode(this.departmentName);
        hash = 53 * hash + Objects.hashCode(this.location);
        hash = 53 * hash + Objects.hashCode(this.createdDate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Department other = (Department) obj;
        if (this.departmentId != other.departmentId) {
            return false;
        }
        if (!Objects.equals(this.departmentName, other.departmentName)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        return Objects.equals(this.createdDate, other.createdDate);
    }

    @Override
    public String toString() {
        return "Department{" + "departmentId=" + departmentId + ", departmentName=" + departmentName + ", location=" + location + ", createdDate=" + createdDate + '}';
    }
}
