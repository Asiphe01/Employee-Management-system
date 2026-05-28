package employeemanagement.gui;

import employeemanagement.dao.DepartmentDAO;
import employeemanagement.dao.EmployeeDAO;
import employeemanagement.dao.RoleDAO;
import employeemanagement.model.Department;
import employeemanagement.model.Employee;
import employeemanagement.model.Role;
import java.awt.*;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *  @author 601037
 * Panel for managing employees — Add, View, Update, Delete.
 */
public class EmployeeManagement extends JFrame {

    private final EmployeeDAO employeeDAO = new EmployeeDAO();
    private final DepartmentDAO deptDAO   = new DepartmentDAO();
    private final RoleDAO roleDAO         = new RoleDAO();

    private JTextField txtId, txtName, txtEmail, txtPhone;
    private JComboBox<Department> cbDept;
    private JComboBox<Role> cbRole;

    private JTable table;
    private DefaultTableModel tableModel;

    public EmployeeManagement() {
        initComponents();
        loadDepartmentsAndRoles();
        refreshTable();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("Employee Management");
        setSize(900, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Employee Details"));
        formPanel.setPreferredSize(new Dimension(280, 0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtId    = new JTextField(15);
        txtName  = new JTextField(15);
        txtEmail = new JTextField(15);
        txtPhone = new JTextField(15);
        cbDept   = new JComboBox<>();
        cbRole   = new JComboBox<>();

        addFormRow(formPanel, gbc, 0, "Employee ID:", txtId);
        addFormRow(formPanel, gbc, 1, "Full Name:",   txtName);
        addFormRow(formPanel, gbc, 2, "Email:",       txtEmail);
        addFormRow(formPanel, gbc, 3, "Phone:",       txtPhone);
        addFormRow(formPanel, gbc, 4, "Department:",  cbDept);
        addFormRow(formPanel, gbc, 5, "Role:",        cbRole);

        JButton btnAdd    = new JButton("Add");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        JButton btnClear  = new JButton("Clear");

        btnAdd.setBackground(new Color(39, 174, 96));   btnAdd.setForeground(Color.WHITE);
        btnUpdate.setBackground(new Color(52, 152, 219)); btnUpdate.setForeground(Color.WHITE);
        btnDelete.setBackground(new Color(231, 76, 60)); btnDelete.setForeground(Color.WHITE);

        btnAdd.addActionListener(e    -> addEmployee());
        btnUpdate.addActionListener(e -> updateEmployee());
        btnDelete.addActionListener(e -> deleteEmployee());
        btnClear.addActionListener(e  -> clearForm());

        JPanel btnPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        btnPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5));
        btnPanel.add(btnAdd); btnPanel.add(btnUpdate);
        btnPanel.add(btnDelete); btnPanel.add(btnClear);

        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2;
        formPanel.add(btnPanel, gbc);

        String[] columns = {"ID", "Name", "Email", "Phone", "Department", "Role"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; } 
        };
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowHeight(22);
        table.getSelectionModel().addListSelectionListener(e -> populateFormFromTable());

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Employee List"));

        setLayout(new BorderLayout(10, 10));
        add(formPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
        
        if (getContentPane() instanceof JPanel) {
            ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        }
    }

    private void addFormRow(JPanel panel, GridBagConstraints gbc, int row, String label, JComponent field) {
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 1; gbc.weightx = 0;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        panel.add(field, gbc);
    }

    private void loadDepartmentsAndRoles() {
        cbDept.removeAllItems();
        for (Department d : deptDAO.getAll()) cbDept.addItem(d);
        cbRole.removeAllItems();
        for (Role r : roleDAO.getAll()) cbRole.addItem(r);
    }

    private void refreshTable() {
        tableModel.setRowCount(0); 
        for (Employee emp : employeeDAO.getAll()) {
            tableModel.addRow(new Object[]{
                emp.getEmployeeId(), emp.getName(), emp.getEmail(),
                emp.getPhone(), emp.getDeptName(), emp.getRoleName()
            });
        }
    }

    private void populateFormFromTable() {
        int row = table.getSelectedRow();
        if (row < 0) return;

        // FIXED: Used .toString() to prevent potential ClassCastExceptions from numeric values
        txtId.setText(tableModel.getValueAt(row, 0).toString());
        txtId.setEditable(false); 
        txtName.setText(tableModel.getValueAt(row, 1) != null ? tableModel.getValueAt(row, 1).toString() : "");
        txtEmail.setText(tableModel.getValueAt(row, 2) != null ? tableModel.getValueAt(row, 2).toString() : "");
        txtPhone.setText(tableModel.getValueAt(row, 3) != null ? tableModel.getValueAt(row, 3).toString() : "");

        String deptName = tableModel.getValueAt(row, 4) != null ? tableModel.getValueAt(row, 4).toString() : "";
        String roleName = tableModel.getValueAt(row, 5) != null ? tableModel.getValueAt(row, 5).toString() : "";
        
        for (int i = 0; i < cbDept.getItemCount(); i++) {
            if (cbDept.getItemAt(i).getDeptName().equals(deptName)) { cbDept.setSelectedIndex(i); break; }
        }
        for (int i = 0; i < cbRole.getItemCount(); i++) {
            if (cbRole.getItemAt(i).getRoleName().equals(roleName)) { cbRole.setSelectedIndex(i); break; }
        }
    }

    private void addEmployee() {
        if (!validateForm()) return;
        Employee emp = buildEmployeeFromForm();
        employeeDAO.add(emp);
        refreshTable();
        clearForm();
        JOptionPane.showMessageDialog(this, "Employee added successfully!");
    }

    private void updateEmployee() {
        if (table.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Please select an employee to update.");
            return;
        }
        if (!validateForm()) return;
        employeeDAO.update(buildEmployeeFromForm());
        refreshTable();
        clearForm();
        JOptionPane.showMessageDialog(this, "Employee updated successfully!");
    }

    private void deleteEmployee() {
        if (table.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Please select an employee to delete.");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this employee?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        employeeDAO.delete(txtId.getText().trim());
        refreshTable();
        clearForm();
        JOptionPane.showMessageDialog(this, "Employee deleted successfully!");
    }

    private Employee buildEmployeeFromForm() {
        Department dept = (Department) cbDept.getSelectedItem();
        Role role       = (Role) cbRole.getSelectedItem();
        return new Employee(
            txtId.getText().trim(),
            txtName.getText().trim(),
            txtEmail.getText().trim(),
            txtPhone.getText().trim(),
            dept != null ? dept.getDeptId() : 0,
            role != null ? role.getRoleId() : 0
        );
    }

    private boolean validateForm() {
        if (txtId.getText().trim().isEmpty() || txtName.getText().trim().isEmpty() || txtEmail.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "ID, Name, and Email are required.");
            return false;
        }
        if (cbDept.getItemCount() == 0 || cbRole.getItemCount() == 0) {
            JOptionPane.showMessageDialog(this, "Please add at least one Department and Role first.");
            return false;
        }
        return true;
    }

    private void clearForm() {
        txtId.setText(""); txtId.setEditable(true);
        txtName.setText(""); txtEmail.setText(""); txtPhone.setText("");
        table.clearSelection();
    }
}