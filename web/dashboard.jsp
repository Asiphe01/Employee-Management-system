<%
    String employeeName = (String) session.getAttribute("employeeName");

    if (employeeName == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Main Dashboard</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

<div class="dashboard">
    <h1>Welcome, <%= employeeName %></h1>
    <p>Employee Management System Dashboard</p>

    <div class="dashboard-grid">

        <div class="card">
            <h3>Employee Management</h3>
            <p>Manage employee records and information.</p>
            <button>Open</button>
        </div>

        <div class="card">
            <h3>Department Management</h3>
            <p>Manage departments and assignments.</p>
            <button>Open</button>
        </div>

        <div class="card">
            <h3>Payroll Management</h3>
            <p>View and process employee payroll.</p>
            <button>Open</button>
        </div>

    </div>

    <a href="LogoutServlet" class="btn danger">Logout</a>
</div>

</body>
</html>