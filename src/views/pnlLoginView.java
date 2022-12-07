package views;

import controllers.LoginEmployeeController;
import controllers.LoginStudentController;
import models.empleado;
import models.empleadoDao;
import models.estudiante;
import models.estudianteDao;

public class pnlLoginView extends javax.swing.JPanel {

    empleado user_employee = new empleado();
    empleadoDao user_employeedao = new empleadoDao();
    estudiante user_student = new estudiante();
    estudianteDao user_studentdao = new estudianteDao();

    public pnlLoginView() {
        initComponents();
        LoginEmployeeController user_login = new LoginEmployeeController(user_employee, user_employeedao, this);
        LoginStudentController student_user = new LoginStudentController(user_student, user_studentdao, this);
        setSize(930, 415);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        btn_enter = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txt_password = new javax.swing.JPasswordField();
        txt_username = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txt_studentName = new javax.swing.JTextField();
        txt_studentPassword = new javax.swing.JPasswordField();
        btn_enterStudent = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/log_utp.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(205, 0, 44));
        jLabel1.setText("INICIAR SESIÓN");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, -1, 50));

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_enter.setBackground(new java.awt.Color(205, 0, 44));
        btn_enter.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btn_enter.setForeground(new java.awt.Color(255, 255, 255));
        btn_enter.setText("Ingresar");
        jPanel2.add(btn_enter, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 210, 40));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(43, 32, 17));
        jLabel2.setText("Contraseña:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 110, -1));

        txt_password.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel2.add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 210, 35));

        txt_username.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_usernameActionPerformed(evt);
            }
        });
        jPanel2.add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 210, 35));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(43, 32, 17));
        jLabel3.setText("Usuario:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 80, -1));

        jTabbedPane1.addTab("Empleados", jPanel2);

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_studentName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_studentName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_studentNameActionPerformed(evt);
            }
        });
        jPanel3.add(txt_studentName, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 210, 35));

        txt_studentPassword.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel3.add(txt_studentPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 210, 35));

        btn_enterStudent.setBackground(new java.awt.Color(205, 0, 44));
        btn_enterStudent.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btn_enterStudent.setForeground(new java.awt.Color(255, 255, 255));
        btn_enterStudent.setText("Ingresar");
        jPanel3.add(btn_enterStudent, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 210, 40));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(43, 32, 17));
        jLabel4.setText("Contraseña:");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 110, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(43, 32, 17));
        jLabel6.setText("Usuario: ");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 80, -1));

        jTabbedPane1.addTab("Estudiantes", jPanel3);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 110, 500, 310));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 490, 420));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/SAE_utp.jpg"))); // NOI18N
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 420));
    }// </editor-fold>//GEN-END:initComponents

    private void txt_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_usernameActionPerformed

    private void txt_studentNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_studentNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_studentNameActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn_enter;
    public javax.swing.JButton btn_enterStudent;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTabbedPane jTabbedPane1;
    public javax.swing.JPasswordField txt_password;
    public javax.swing.JTextField txt_studentName;
    public javax.swing.JPasswordField txt_studentPassword;
    public javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables
}
