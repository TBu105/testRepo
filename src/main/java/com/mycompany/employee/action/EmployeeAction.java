/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.employee.action;

import com.mycompany.employee.dto.EmployeeDetailDTO;
import com.mycompany.employee.exception.DuplicateEmailException;
import com.mycompany.employee.factory.ServiceFactory;
import com.mycompany.employee.form.EmployeeForm;
import com.mycompany.employee.model.Department;
import com.mycompany.employee.service.serviceImpl.DepartmentServiceImpl;
import com.mycompany.employee.service.serviceImpl.EmployeeServiceImpl;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;
import org.apache.struts.actions.DispatchAction;

public class EmployeeAction extends DispatchAction{
    EmployeeServiceImpl em = ServiceFactory.getEmployeeService();
    DepartmentServiceImpl de = ServiceFactory.getDepartmentService();

    public EmployeeAction() {}
    
    public ActionForward listEmployees(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
        EmployeeForm f = (EmployeeForm) form;
        
        Integer departId = f.getDepartmentId();
        
        if (departId == 0) {
            departId = 2;
            f.setDepartmentId(departId);
        }

        List<EmployeeDetailDTO> emList = em.getAllEmployees(departId);
        List<Department> deList = de.getAllDepartments();
        
        request.setAttribute("departments", deList);
        request.setAttribute("employees", emList);
        
        return mapping.findForward("employeeList");
    }    
    
    public ActionForward prepareAddEmployee(ActionMapping mapping, ActionForm form, 
                                            HttpServletRequest request, HttpServletResponse response) 
                                            throws SQLException {
        EmployeeForm f = (EmployeeForm) form;

        List<Department> list = de.getAllDepartments();
        request.setAttribute("departments", list);

        Integer departId = f.getDepartmentId();
        if (departId == null || departId == 0) {
            departId = 2;
            f.setDepartmentId(departId);
        }

        return mapping.findForward("createEmployee");
    }

    public ActionForward createEmployee(ActionMapping mapping, ActionForm form, 
                                        HttpServletRequest request, HttpServletResponse response) 
                                        throws SQLException, DuplicateEmailException {

        EmployeeForm f = (EmployeeForm) form;

        Integer selectedDeptId = f.getDepartmentId();

        int validManagerId = em.findManagerIdByDepartment(selectedDeptId)
                .orElseThrow(() -> new RuntimeException("No Manager found for Department ID: " + selectedDeptId));

        f.setManagerId(validManagerId);

        em.addEmployee(f);

        ActionRedirect redirect = new ActionRedirect("/employee.do");
        redirect.addParameter("method", "listEmployees");
        redirect.addParameter("departmentId", selectedDeptId);
        
        return redirect;
    }
}
