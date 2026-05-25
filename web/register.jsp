<!DOCTYPE html>
<html>
<head>
    <title>Register Employee</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

<div class="form-container">
    <h2>Employee Registration</h2>

    <% if (request.getParameter("error") != null) { %>
        <p class="error"><%= request.getParameter("error") %></p>
    <% } %>

    <form action="RegisterServlet" method="post">
        <input type="text" name="employee_id" placeholder="Employee ID" required>
        <input type="text" name="full_name" placeholder="Full Name" required>
        <input type="text" name="department" placeholder="Department" required>
        <input type="text" name="role" placeholder="Role" required>
        <input type="password" name="password" placeholder="Password" required>
        <input type="text" name="phone" placeholder="Phone Number">
        <input type="email" name="email" placeholder="Email Address" required>

        <button type="submit">Register</button>
    </form>

    <p>Already registered? <a href="login.jsp">Login here</a></p>
</div>

</body>
</html>
