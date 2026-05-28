/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main.dashboard;

import java.awt.*;
import javax.swing.*;

/**
 * Main Dashboard - the first screen shown after login.
 * Contains buttons to navigate to each management panel.
 * * @author 601037
 */
public class MainDashboard extends JFrame {

    public MainDashboard() {
        initComponents();
        setLocationRelativeTo(null); // Center on screen
    }

    private void initComponents() {
        setTitle("Employee Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setResizable(false);

        // Shut down DB connection when the window closes
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                // Safely close connection if the utility class exists
                try {
                    // Using full package path just in case DBConnection is in your other package
                    employeemanagement.util.eymployeedb.closeConnection();
                } catch (Exception ex) {
                    System.out.println("DBConnection utility missing or unconfigured.");
                }
            }
        });

        // ---- Header ----
        JLabel titleLabel = new JLabel("Employee Management System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(33, 97, 140));
        titleLabel.setPreferredSize(new Dimension(600, 60));

        // ---- Navigation Buttons ----
        JButton btnEmployee   = createNavButton("Manage Employees",   new Color(52, 152, 219));
        JButton btnDepartment = createNavButton("Manage Departments", new Color(39, 174, 96));
        JButton btnRole       = createNavButton("Manage Roles",       new Color(230, 126, 34));
        JButton btnPayroll    = createNavButton("Manage Payroll",     new Color(155, 89, 182));

        // Open each panel in a new window on button click
        btnEmployee.addActionListener(e -> new employeemanagement.gui.EmployeePanel().setVisible(true));
        btnDepartment.addActionListener(e -> new employeemanagement.gui.DepartmentPanel().setVisible(true));
        btnRole.addActionListener(e -> new employeemanagement.gui.RolePanel().setVisible(true));
        btnPayroll.addActionListener(e -> new employeemanagement.gui.PayrollPanel().setVisible(true));

        // ---- Layout ----
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.add(btnEmployee);
        buttonPanel.add(btnDepartment);
        buttonPanel.add(btnRole);
        buttonPanel.add(btnPayroll);

        setLayout(new BorderLayout());
        add(titleLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        // Footer
        JLabel footer = new JLabel("PRG361 - Employee Management System", SwingConstants.CENTER);
        footer.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        footer.setFont(new Font("Arial", Font.ITALIC, 11));
        footer.setForeground(Color.GRAY);
        add(footer, BorderLayout.SOUTH);
    }

    /** Creates a styled navigation button. */
    private JButton createNavButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorderPainted(false);
        return btn;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Fallback to default cross-platform UI if system L&F fails
        }

        // Run the dashboard view
        SwingUtilities.invokeLater(() -> new MainDashboard().setVisible(true));
    }
    
}