import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 601037
 */
public class DepartmentPanel extends javax.swing.JFrame {

    private final employee db = new employeedb();
    private Connection conn;

    public DepartmentPanel() {
        initComponents();
        initializeDatabase();
        loadDepartmentTable();
        loadEmployeeDepartmentTable();
    }

     private void initializeDatabase() {
         conn = db.connect();
    }

    private void loadDepartmentTable() {
    DefaultTableModel model = (DefaultTableModel) tblDeptData1.getModel();
    model.setRowCount(0);

    List<Department> departments = db.getAllDepartments();
    for (Department dept : departments) {
        model.addRow(new Object[]{
            dept.getDepartmentId(),
            dept.getDepartmentName()  // Make sure this matches your Department class getter
        });
    }
}


    private void loadEmployeeDepartmentTable() {
        DefaultTableModel model = (DefaultTableModel) tblEmpDeptData.getModel();
        model.setRowCount(0);

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                 "SELECT e.emp_id, e.full_name, e.role, d.department_name " +
                 "FROM Employee e LEFT JOIN Department d ON e.department_id = d.department_id")) {
            
            while (rs.next()) {
                String fullName = rs.getString("full_name");
                String[] nameParts = fullName != null ? fullName.split(" ", 2) : new String[]{"", ""};
                
                model.addRow(new Object[]{
                    rs.getString("emp_id"),
                    nameParts[0],
                    rs.getString("role"),
                    rs.getString("department_name") != null ? rs.getString("department_name") : "Unassigned"
                });
            }
        } catch (SQLException ex) {
            showError("Database Error", "Failed to load employee departments: " + ex.getMessage());
        }
    }

    private void clearForm() {
        txtDeptID.setText("");
        txtDeptName.setText("");
        txtEmpAssignID.setText("");
        txtEmpDebtID.setText("");
        
    }

    private void showError(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }

    private void showSuccess(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * 
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblDeptID = new javax.swing.JLabel();
        lblDebtName = new javax.swing.JLabel();
        lblEmpDept = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmpDeptData = new javax.swing.JTable();
        txtDeptID = new javax.swing.JTextField();
        txtDeptName = new javax.swing.JTextField();
        javax.swing.JButton btnAddDept = new javax.swing.JButton();
        javax.swing.JButton btnUpdateDept = new javax.swing.JButton();
        javax.swing.JButton btnDeleteDept = new javax.swing.JButton();
        javax.swing.JButton btnHomePageDept = new javax.swing.JButton();
        javax.swing.JButton btnClearDept = new javax.swing.JButton();
        javax.swing.JButton btnViewDept = new javax.swing.JButton();
        lblEmpAssignID = new javax.swing.JLabel();
        txtEmpAssignID = new javax.swing.JTextField();
        lblEmpDebtID = new javax.swing.JLabel();
        txtEmpDebtID = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDeptData1 = new javax.swing.JTable();
        javax.swing.JButton btnRemoveEmpDept = new javax.swing.JButton();
        javax.swing.JButton btnAssignDept = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(900, 800));

        jPanel1.setBackground(new java.awt.Color(158, 200, 158));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 0), 3));
        jPanel1.setPreferredSize(new java.awt.Dimension(900, 700));

        lblDeptID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDeptID.setText("Department ID:");

        lblDebtName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDebtName.setText("Department Name:");

        lblEmpDept.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        tblEmpDeptData.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 0), 4));
        tblEmpDeptData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Employee ID", "Name", "Role", "Assigned Department"
            }
        ));
        jScrollPane1.setViewportView(tblEmpDeptData);

        txtDeptID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDeptIDActionPerformed(evt);
            }
        });

        btnAddDept.setForeground(new java.awt.Color(153, 0, 0));
        btnAddDept.setText("Add");
        btnAddDept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddDeptActionPerformed(evt);
            }
        });

        btnUpdateDept.setForeground(new java.awt.Color(153, 0, 0));
        btnUpdateDept.setText("Update");
        btnUpdateDept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateDeptActionPerformed(evt);
            }
        });

        btnDeleteDept.setBackground(new java.awt.Color(0, 51, 0));
        btnDeleteDept.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteDept.setText("Delete");
        btnDeleteDept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteDeptActionPerformed(evt);
            }
        });

        btnHomePageDept.setBackground(new java.awt.Color(0, 153, 0));
        btnHomePageDept.setForeground(new java.awt.Color(255, 255, 255));
        btnHomePageDept.setText("HomePage");
        btnHomePageDept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomePageDeptActionPerformed(evt);
            }
        });

        btnClearDept.setForeground(new java.awt.Color(153, 0, 0));
        btnClearDept.setText("Clear");
        btnClearDept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearDeptActionPerformed(evt);
            }
        });

        btnViewDept.setForeground(new java.awt.Color(153, 0, 0));
        btnViewDept.setText("View all departments");
        btnViewDept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewDeptActionPerformed(evt);
            }
        });

        lblEmpAssignID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblEmpAssignID.setText("EmployeeID assign:");

        txtEmpAssignID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmpAssignIDActionPerformed(evt);
            }
        });

        lblEmpDebtID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblEmpDebtID.setText("Department ID:");

        tblDeptData1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 0), 4));
        tblDeptData1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Department Name"
            }
        ));
        jScrollPane2.setViewportView(tblDeptData1);

        btnRemoveEmpDept.setBackground(new java.awt.Color(0, 51, 0));
        btnRemoveEmpDept.setForeground(new java.awt.Color(255, 255, 255));
        btnRemoveEmpDept.setText("Remove ");
        btnRemoveEmpDept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveEmpDeptActionPerformed(evt);
            }
        });

        btnAssignDept.setForeground(new java.awt.Color(153, 0, 0));
        btnAssignDept.setText("Assign");
        btnAssignDept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAssignDeptActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDebtName)
                            .addComponent(lblDeptID))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDeptName, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                            .addComponent(txtDeptID)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEmpAssignID)
                            .addComponent(lblEmpDebtID))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmpAssignID, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmpDebtID, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnHomePageDept))))
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAddDept)
                            .addComponent(btnDeleteDept)
                            .addComponent(btnUpdateDept)
                            .addComponent(btnClearDept))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnViewDept)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnRemoveEmpDept)
                            .addComponent(btnAssignDept))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblEmpDept)
                        .addGap(14, 14, 14))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(btnViewDept)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnDeleteDept)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDeptID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDeptID)
                            .addComponent(btnAddDept))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdateDept)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblDebtName)
                                    .addComponent(txtDeptName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnClearDept))))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblEmpDept)
                        .addGap(206, 206, 206))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtEmpAssignID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnRemoveEmpDept)
                                    .addComponent(lblEmpAssignID))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnAssignDept)
                                    .addComponent(lblEmpDebtID)
                                    .addComponent(txtEmpDebtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(106, 106, 106)
                                .addComponent(btnHomePageDept)))
                        .addContainerGap(91, Short.MAX_VALUE))))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(4, 73, 4));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/Department Manage.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1223, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 661, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddDeptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddDeptActionPerformed
        if (txtDeptID.getText().trim().isEmpty() || txtDeptName.getText().trim().isEmpty()) {
            showError("Validation Error", "Department ID and Name are required!");
            return;
        }

        Department dept = new Department(
                txtDeptID.getText().trim(),
                txtDeptName.getText().trim()
        );
        db.insertDepartment(dept);
        loadDepartmentTable();
        clearForm();
        showSuccess("Department added successfully!");
    }//GEN-LAST:event_btnAddDeptActionPerformed

    private void btnUpdateDeptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateDeptActionPerformed
         int selectedRow = tblDeptData1.getSelectedRow();
        if (selectedRow == -1) {
            showError("Selection Error", "Please select a department to update");
            return;
        }

        Department dept = new Department(
                txtDeptID.getText().trim(),
                txtDeptName.getText().trim()
        );
        db.updateDepartment(dept);
        loadDepartmentTable();
        showSuccess("Department updated successfully!");
    }

    private void btnDeleteDeptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteDeptActionPerformed
        int selectedRow = tblDeptData1.getSelectedRow();
        if (selectedRow == -1) {
            showError("Selection Error", "Please select a department to delete");
            return;
        }

        String deptId = tblDeptData1.getValueAt(selectedRow, 0).toString();
        
        db.deleteDepartment(deptId);
        loadDepartmentTable();
        showSuccess("Department deleted successfully!");
    }//GEN-LAST:event_btnDeleteDeptActionPerformed

    private void btnHomePageDeptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomePageDeptActionPerformed
        this.dispose();
        new MainDashboard().setVisible(true);
    }//GEN-LAST:event_btnHomePageDeptActionPerformed

    private void btnClearDeptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearDeptActionPerformed
        clearForm();
    }//GEN-LAST:event_btnClearDeptActionPerformed

    private void btnViewDeptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewDeptActionPerformed
        loadDepartmentTable();
        loadEmployeeDepartmentTable();
    }//GEN-LAST:event_btnViewDeptActionPerformed

    private void btnRemoveEmpDeptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveEmpDeptActionPerformed
        int selectedRow = tblEmpDeptData.getSelectedRow();
        if (selectedRow == -1) {
            showError("Selection Error", "Please select an employee to remove from department");
            return;
        }

        String empId = tblEmpDeptData.getValueAt(selectedRow, 0).toString();
        
        try {
            // Set department ID to null or default
            String query = "UPDATE Employee SET department_id = NULL WHERE emp_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, Integer.parseInt(empId));
                pstmt.executeUpdate();
                loadEmployeeDepartmentTable();
                showSuccess("Employee removed from department successfully!");
            }
        } catch (SQLException ex) {
            showError("Database Error", "Failed to remove employee from department: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnRemoveEmpDeptActionPerformed

    private void btnAssignDeptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAssignDeptActionPerformed
        if (txtEmpAssignID.getText().trim().isEmpty() || txtEmpDebtID.getText().trim().isEmpty()) {
        showError("Validation Error", "Employee ID and Department ID are required!");
        return;
        }

        try {
            // First verify the department exists
            String checkDeptQuery = "SELECT 1 FROM Department WHERE department_id = ?";
            try (PreparedStatement checkDeptStmt = conn.prepareStatement(checkDeptQuery)) {
                checkDeptStmt.setInt(1, Integer.parseInt(txtEmpDebtID.getText().trim()));
                if (!checkDeptStmt.executeQuery().next()) {
                    showError("Not Found", "Department not found with ID: " + txtEmpDebtID.getText().trim());
                    return;
                }
            }

            // Update only the specific employee
            String updateQuery = "UPDATE Employee SET department_id = ? WHERE emp_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {
                pstmt.setInt(1, Integer.parseInt(txtEmpDebtID.getText().trim()));
                pstmt.setInt(2, Integer.parseInt(txtEmpAssignID.getText().trim()));
                
                int rowsAffected = pstmt.executeUpdate();
                
                if (rowsAffected > 0) {
                    loadEmployeeDepartmentTable();
                    showSuccess("Employee " + txtEmpAssignID.getText().trim() + 
                            " assigned to department " + txtEmpDebtID.getText().trim() + " successfully!");
                } else {
                    showError("Not Found", "Employee not found with ID: " + txtEmpAssignID.getText().trim());
                }
            }
        } catch (SQLException | NumberFormatException ex) {
            showError("Database Error", "Failed to assign department: " + ex.getMessage());
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnAssignDeptActionPerformed

    private void txtDeptIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDeptIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDeptIDActionPerformed

    private void txtEmpAssignIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmpAssignIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmpAssignIDActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DepartmentManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DepartmentManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DepartmentManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DepartmentManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DepartmentManagement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDebtName;
    private javax.swing.JLabel lblDeptID;
    private javax.swing.JLabel lblEmpAssignID;
    private javax.swing.JLabel lblEmpDebtID;
    private javax.swing.JLabel lblEmpDept;
    private javax.swing.JTable tblDeptData1;
    private javax.swing.JTable tblEmpDeptData;
    private javax.swing.JTextField txtDeptID;
    private javax.swing.JTextField txtDeptName;
    private javax.swing.JTextField txtEmpAssignID;
    private javax.swing.JTextField txtEmpDebtID;
    // End of variables declaration//GEN-END:variables
}
