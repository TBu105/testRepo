<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Nếu muốn format ngày tháng/tiền tệ đẹp hơn thì thêm thư viện fmt -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Employee List</title>
    <link rel="stylesheet" href="<c:url value='/css/style.css'/>">
</head>

<body>
    <jsp:include page="/WEB-INF/jsp/common/navigation.jsp"/>

    <div class="container">
        <h2>Employee List</h2>

        <!-- ========================= -->
        <!-- FORM CHỌN DEPARTMENT -->
        <!-- ========================= -->
        <html:form action="/employee.do">

            <!-- method cho DispatchAction -->
            <html:hidden property="method" value="listEmployees"/>

            <!-- KHÔNG dùng managerId ở màn list -->
            <html:hidden property="managerId" value=""/>

            <label>Department:</label>

            <html:select property="departmentId" onchange="this.form.submit()">
                <html:optionsCollection
                        name="departments"
                        label="departmentName"
                        value="departmentId"/>
            </html:select>

        </html:form>

        <br/>

        <!-- ========================= -->
        <!-- BẢNG DANH SÁCH EMPLOYEE -->
        <!-- ========================= -->
        <table border="1" cellpadding="5" cellspacing="0">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>First Name</th> <!-- Đã sửa lỗi chính tả -->
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Phone Number</th>
                    <th>Hire Date</th>
                    <th>Job Title</th>
                    <th>Salary</th>
                    <th>Status</th>
                    <th>Dept ID</th>
                </tr>
            </thead>

            <!-- Debug dòng này để kiểm tra dữ liệu nếu cần -->
            <!-- <h3>DEBUG: List employees size = ${employees.size()}</h3> -->

            <tbody>
                <c:choose>
                    <c:when test="${empty employees}">
                        <tr>
                            <!-- colspan phải bằng tổng số cột th ở trên (10 cột) -->
                            <td colspan="10" align="center">
                                No employees found
                            </td>
                        </tr>
                    </c:when>

                    <c:otherwise>
                        <c:forEach var="e" items="${employees}">
                            <tr>
                                <td>${e.employeeId}</td>
                                <td>${e.firstName}</td>
                                <td>${e.lastName}</td>
                                <td>${e.email}</td>
                                <td>${e.phoneNumber}</td>

                                <!-- Hiển thị ngày tháng. Vì là LocalDateTime nên in trực tiếp sẽ ra dạng ISO -->
                                <!-- Nếu muốn format đẹp, cần dùng taglib fmt hoặc xử lý String từ Action -->
                                <td>${e.hireDate}</td>

                                <td>${e.jobTitle}</td>
                                <td>${e.salary}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${e.status == 1}">
                                            Active
                                        </c:when>
                                        <c:otherwise>
                                            Inactive
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${e.departmentId}</td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>

        <br/>

        <!-- NÚT CREATE EMPLOYEE -->
        <a 
            class="action-link"
            href="employee.do?method=prepareAddEmployee&departmentId=${employeeForm.departmentId}"
            >
            Create Employee
        </a>
    </div>

    <jsp:include page="/WEB-INF/jsp/common/footer.jsp"/>
</body>
</html>