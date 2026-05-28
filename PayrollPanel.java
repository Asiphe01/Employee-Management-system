import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Date; 
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */



/**
 *
 * @author 601037
 */
public class PayrollManagement extends javax.swing.JFrame {

    private final employee db = new employeedb();
    private Connection conn;

    public PayrollManagement() {
        initComponents();
        initializeDatabase();
    }

    private void initializeDatabase() {
        conn = db.connect();
    }

    private void loadPayrollHistory(String empId) {
        DefaultTableModel model = (DefaultTableModel) tblDeptData.getModel();
        model.setRowCount(0);

        try {
            String query = "SELECT * FROM Payroll WHERE emp_id = ? ORDER BY date_paid DESC";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, Integer.parseInt(empId));
                ResultSet rs = pstmt.executeQuery();
                
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getString("payroll_id"),
                        rs.getString("emp_id"),
                        rs.getDouble("amount"),
                        rs.getDate("date_paid")
                    });
                }
            }
        } catch (SQLException ex) {
            showError("Database Error", "Failed to load payroll history: " + ex.getMessage());
        }
    }

    private void generatePayslipPreview() {
        String empId = txtPayEmpID.getText().trim();
        String amount = txtAmount.getText().trim();
        String month = txtMonth.getText().trim();
        
        if (empId.isEmpty() || amount.isEmpty() || month.isEmpty()) {
            showError("Validation Error", "Employee ID, Amount and Month are required!");
            return;
        }

        try {
            // Get employee details
            String empQuery = "SELECT full_name, role, salary FROM Employee WHERE emp_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(empQuery)) {
                pstmt.setInt(1, Integer.parseInt(empId));
                ResultSet rs = pstmt.executeQuery();
                
                if (rs.next()) {
                    String payslip = "=== PAYSLIP ===\n" +
                                    "Employee ID: " + empId + "\n" +
                                    "Name: " + rs.getString("full_name") + "\n" +
                                    "Position: " + rs.getString("role") + "\n" +
                                    "Basic Salary: " + rs.getDouble("salary") + "\n" +
                                    "Month: " + month + "\n" +
                                    "Amount Paid: " + amount + "\n" +
                                    "Date: " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "\n" +
                                    "=== END ===";
                    
                    txtAreaPreview.setText(payslip);
                } else {
                    showError("Not Found", "Employee not found with ID: " + empId);
                }
            }
        } catch (SQLException | NumberFormatException ex) {
            showError("Database Error", "Failed to generate payslip: " + ex.getMessage());
        }
    }

    private void processSalary() {
        String payrollId = txtPayrolID.getText().trim();
        String empId = txtPayEmpID.getText().trim();
        String amount = txtAmount.getText().trim();
        String month = txtMonth.getText().trim();
        String datePaid = txtDatePaid.getText().trim();
        
        if (payrollId.isEmpty() || empId.isEmpty() || amount.isEmpty() || month.isEmpty() || datePaid.isEmpty()) {
            showError("Validation Error", "All fields are required!");
            return;
        }

        // Validate date format
        if (!isValidDate(datePaid)) {
            showError("Invalid Date", "Date must be in YYYY-MM-DD format");
            return;
        }

        try {
            Payroll pay = new Payroll(
                payrollId,
                empId,
                month,
                Double.parseDouble(amount),
                datePaid
            );
            
            db.insertPayroll(pay);
            loadPayrollHistory(empId);
            showSuccess("Salary processed successfully!");
        } catch (NumberFormatException ex) {
            showError("Database Error", "Failed to process salary: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    
    private boolean isValidDate(String dateStr) {
        try {
            java.sql.Date.valueOf(dateStr); // This will throw IllegalArgumentException if invalid
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private void updateSalaryInfo() {
        String payrollId = txtPayrolID.getText().trim();
        String amount = txtAmount.getText().trim();
        
        if (payrollId.isEmpty() || amount.isEmpty()) {
            showError("Validation Error", "Payroll ID and Amount are required!");
            return;
        }

        try {
            db.updatePayroll(payrollId, Double.parseDouble(amount));
            showSuccess("Salary information updated successfully!");
        } catch (NumberFormatException ex) {
            showError("Database Error", "Failed to update salary: " + ex.getMessage());
        }
    }

    private void showError(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }

    private void showSuccess(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblPayrolID = new javax.swing.JLabel();
        lblAmount = new javax.swing.JLabel();
        lblMonth = new javax.swing.JLabel();
        lblPayslip = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDeptData = new javax.swing.JTable();
        txtPayEmpID = new javax.swing.JTextField();
        txtMonth = new javax.swing.JTextField();
        txtAmount = new javax.swing.JTextField();
        javax.swing.JButton btnViewHistory = new javax.swing.JButton();
        javax.swing.JButton btnGeneratePaysheet = new javax.swing.JButton();
        javax.swing.JButton btnHomePagePay = new javax.swing.JButton();
        lblDatePaid = new javax.swing.JLabel();
        txtDatePaid = new javax.swing.JTextField();
        lblPayEmpID = new javax.swing.JLabel();
        txtPayrolID = new javax.swing.JTextField();
        javax.swing.JButton btnProcessSalary = new javax.swing.JButton();
        javax.swing.JButton btnUpdateSalary = new javax.swing.JButton();
        lblPayHistory1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAreaPreview = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 0, 0));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/payroll Manage.png"))); // NOI18N
        jLabel1.setText("p");

        jPanel1.setBackground(new java.awt.Color(158, 200, 158));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 0), 3));

        lblPayrolID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPayrolID.setText("Payroll ID:");

        lblAmount.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblAmount.setText("Amount:");

        lblMonth.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMonth.setText("Month:");

        lblPayslip.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPayslip.setText("Payslip preview");

        tblDeptData.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 0), 4));
        tblDeptData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Payroll ID", "Employee ID", "Amount", "Date Paid"
            }
        ));
        jScrollPane1.setViewportView(tblDeptData);

        btnViewHistory.setForeground(new java.awt.Color(153, 0, 0));
        btnViewHistory.setText("View Salary History");
        btnViewHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewHistoryActionPerformed(evt);
            }
        });

        btnGeneratePaysheet.setForeground(new java.awt.Color(153, 0, 0));
        btnGeneratePaysheet.setText("Generate Paysheet");
        btnGeneratePaysheet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGeneratePaysheetActionPerformed(evt);
            }
        });

        btnHomePagePay.setBackground(new java.awt.Color(0, 102, 0));
        btnHomePagePay.setForeground(new java.awt.Color(255, 255, 255));
        btnHomePagePay.setText("HomePage");
        btnHomePagePay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomePagePayActionPerformed(evt);
            }
        });

        lblDatePaid.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDatePaid.setText("Date paid:");

        lblPayEmpID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPayEmpID.setText("Employee ID:");

        btnProcessSalary.setForeground(new java.awt.Color(153, 0, 0));
        btnProcessSalary.setText("Process Salary");
        btnProcessSalary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcessSalaryActionPerformed(evt);
            }
        });

        btnUpdateSalary.setForeground(new java.awt.Color(153, 0, 0));
        btnUpdateSalary.setText("Update Salary Info");
        btnUpdateSalary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateSalaryActionPerformed(evt);
            }
        });

        lblPayHistory1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPayHistory1.setText("Salary History");

        txtAreaPreview.setColumns(20);
        txtAreaPreview.setRows(5);
        jScrollPane2.setViewportView(txtAreaPreview);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(btnHomePagePay)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPayrolID)
                            .addComponent(lblAmount))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPayrolID)
                            .addComponent(txtAmount))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPayEmpID)
                            .addComponent(lblDatePaid))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPayEmpID, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                            .addComponent(txtDatePaid))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMonth)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnProcessSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                        .addComponent(btnGeneratePaysheet)
                        .addGap(52, 52, 52)
                        .addComponent(btnUpdateSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(btnViewHistory)
                        .addGap(72, 72, 72))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(353, 353, 353)
                .addComponent(lblPayslip)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(252, 252, 252))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(344, 344, 344)
                    .addComponent(lblPayHistory1)
                    .addContainerGap(363, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPayrolID)
                    .addComponent(lblMonth)
                    .addComponent(txtPayEmpID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPayEmpID)
                    .addComponent(txtPayrolID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAmount)
                    .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDatePaid)
                    .addComponent(txtDatePaid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGeneratePaysheet)
                    .addComponent(btnViewHistory)
                    .addComponent(btnUpdateSalary)
                    .addComponent(btnProcessSalary))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPayslip)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(btnHomePagePay)
                .addGap(22, 22, 22))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(200, 200, 200)
                    .addComponent(lblPayHistory1)
                    .addContainerGap(289, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 820, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 527, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(106, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnViewHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewHistoryActionPerformed

        String empId = txtPayEmpID.getText().trim();
        if (empId.isEmpty()) {
            showError("Validation Error", "Employee ID is required!");
            return;
        }
        loadPayrollHistory(empId);
    }//GEN-LAST:event_btnViewHistoryActionPerformed

    private void btnGeneratePaysheetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGeneratePaysheetActionPerformed
        generatePayslipPreview();
    }//GEN-LAST:event_btnGeneratePaysheetActionPerformed

    private void btnHomePagePayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomePagePayActionPerformed
        this.dispose();
        new MainDashboard().setVisible(true);
    }//GEN-LAST:event_btnHomePagePayActionPerformed

    private void btnProcessSalaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcessSalaryActionPerformed
        processSalary();
    }//GEN-LAST:event_btnProcessSalaryActionPerformed

    private void btnUpdateSalaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateSalaryActionPerformed
        updateSalaryInfo();
    }//GEN-LAST:event_btnUpdateSalaryActionPerformed

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
            java.util.logging.Logger.getLogger(PayrollManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PayrollManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PayrollManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PayrollManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PayrollManagement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAmount;
    private javax.swing.JLabel lblDatePaid;
    private javax.swing.JLabel lblMonth;
    private javax.swing.JLabel lblPayEmpID;
    private javax.swing.JLabel lblPayHistory1;
    private javax.swing.JLabel lblPayrolID;
    private javax.swing.JLabel lblPayslip;
    private javax.swing.JTable tblDeptData;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JTextArea txtAreaPreview;
    private javax.swing.JTextField txtDatePaid;
    private javax.swing.JTextField txtMonth;
    private javax.swing.JTextField txtPayEmpID;
    private javax.swing.JTextField txtPayrolID;
    // End of variables declaration//GEN-END:variables
}
