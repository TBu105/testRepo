<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Employee Form</title>
    <link rel="stylesheet" href="<c:url value='/css/style.css'/>">
</head>

<body>

<jsp:include page="/WEB-INF/jsp/common/navigation.jsp"/>

<div class="container">
    <h2>Create Employee</h2>

    <!-- ===== ERROR BOX (CHỈ HIỆN KHI CÓ LỖI) ===== -->
    <logic:messagesPresent>
        <div class="error-box">
            <strong>Please fix the following errors:</strong>
            <br />
            <html:errors/>
        </div>
    </logic:messagesPresent>

    <!-- ===== FORM ===== -->
    <html:form action="/employeeCreate.do">
        <html:hidden property="method" value="createEmployee"/>

        <table class="form-table">
            <tr>
                <td>Department:</td>
                <td>
                    <html:select property="departmentId">
                        <html:optionsCollection
                                name="departments"
                                label="departmentName"
                                value="departmentId"/>
                    </html:select>
                </td>
            </tr>

            <tr>
                <td>First Name:</td>
                <td>
                    <html:text property="firstName" size="30"/>
                    <div class="field-error">
                        <html:errors property="firstName"/>
                    </div>
                </td>
            </tr>

            <tr>
                <td>Last Name:</td>
                <td>
                    <html:text property="lastName" size="30"/>
                    <div class="field-error">
                        <html:errors property="lastName"/>
                    </div>
                </td>
            </tr>

            <tr>
                <td>Email:</td>
                <td>
                    <html:text property="email" size="30"/>
                    <div class="field-error">
                        <html:errors property="email"/>
                    </div>
                </td>
            </tr>

            <tr>
                <td>Phone Number:</td>
                <td>
                    <html:text property="phoneNumber" size="20"/>
                    <div class="field-error">
                        <html:errors property="phoneNumber"/>
                    </div>
                </td>
            </tr>

            <tr>
                <td>Hire Date:</td>
                <td>
                    <html:text property="hireDate" size="25"/>
                </td>
            </tr>

            <tr>
                <td>Job Title:</td>
                <td>
                    <html:text property="jobTitle" size="30"/>
                    <div class="field-error">
                        <html:errors property="jobTitle"/>
                    </div>
                </td>
            </tr>

            <tr>
                <td>Salary:</td>
                <td>
                    <html:text property="salary" size="15"/>
                    <div class="field-error">
                        <html:errors property="salary"/>
                    </div>
                </td>
            </tr>

            <tr>
                <td>Status:</td>
                <td>
                    <html:select property="status">
                        <html:option value="1">Active</html:option>
                        <html:option value="0">Inactive</html:option>
                    </html:select>
                </td>
            </tr>

            <tr>
                <td colspan="2" class="form-actions">
                    <html:submit value="Save Employee"/>
                    &nbsp;
                    <a class="action-link"
                       href="employee.do?method=listEmployees&departmentId=${employeeForm.departmentId}">
                        Cancel
                    </a>
                </td>
            </tr>
        </table>

    </html:form>
</div>

<jsp:include page="/WEB-INF/jsp/common/footer.jsp"/>

</body>
</html>
