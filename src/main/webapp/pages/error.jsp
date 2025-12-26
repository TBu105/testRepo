<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>System Error</title>
    <style>
        body {
            font-family: Arial;
            background: #f4f6f8;
        }
        .error-box {
            width: 600px;
            margin: 100px auto;
            padding: 30px;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,.1);
            text-align: center;
        }
        h2 {
            color: #d9534f;
        }
        p {
            color: #555;
        }
        a {
            text-decoration: none;
            color: #337ab7;
        }
    </style>
</head>

<body>
<div class="error-box">
    <h2>⚠️ System Error</h2>
    <p>Đã xảy ra lỗi trong hệ thống.</p>
    <p>Vui lòng thử lại sau hoặc liên hệ quản trị viên.</p>

    <br/>
    <a href="<%= request.getContextPath() %>/index.jsp">
        ⬅ Quay về trang chủ
    </a>
</div>
</body>
</html>
