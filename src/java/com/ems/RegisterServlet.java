package com.ems;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String employeeId = request.getParameter("employee_id");
        String fullName = request.getParameter("full_name");
        String department = request.getParameter("department");
        String role = request.getParameter("role");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        try {
            Connection con = DBConnection.getConnection();

            String checkSql = "SELECT * FROM employee WHERE employee_id = ? OR email = ?";
            PreparedStatement checkPs = con.prepareStatement(checkSql);
            checkPs.setString(1, employeeId);
            checkPs.setString(2, email);

            ResultSet rs = checkPs.executeQuery();

            if (rs.next()) {
                response.sendRedirect("register.jsp?error=Employee ID or Email already exists");
            } else {
                String sql = "INSERT INTO employee(employee_id, full_name, department, role, password, phone, email) VALUES (?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, employeeId);
                ps.setString(2, fullName);
                ps.setString(3, department);
                ps.setString(4, role);
                ps.setString(5, password);
                ps.setString(6, phone);
                ps.setString(7, email);

                ps.executeUpdate();

                response.sendRedirect("login.jsp?success=Registration successful. Please login.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("register.jsp?error=Something went wrong");
        }
    }
}
