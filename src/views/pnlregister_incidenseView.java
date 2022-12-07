package views;

import controllers.IncidenciasController;
import javax.swing.SwingUtilities;
import models.incidencias;
import models.incidenciasDao;

public class pnlregister_incidenseView extends javax.swing.JPanel {
    //incidencia
    incidencias incidense = new incidencias();
    incidenciasDao incidenseDao = new incidenciasDao();
    public pnlregister_incidenseView() {
        initComponents();
        setSize(568, 568);
        //Controlador de incidencias
        IncidenciasController incidense_account = new IncidenciasController(incidense, incidenseDao, this);
        //Rellena las cajas de texto
        incidense_account.fillFields();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        label_name_carrera = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaIncidencia = new javax.swing.JTextArea();
        btnArchivo = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnEnviar = new javax.swing.JButton();
        btn_logout = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        txtCorreoStudent = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCarreraUniversitaria = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtStudent_id = new javax.swing.JTextField();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(205, 0, 44));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Servicio de Atención al Estudiante - SAE");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 500, 50));
        jPanel1.add(label_name_carrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, 140, 20));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 570, 60));

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel46.setText("!Hola!, bienvenido al SAE UTP.  Por favor registra su incidencia en la caja de texto.");
        jPanel2.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 530, -1));

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel47.setText("Archivos adjuntos");
        jPanel2.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 130, -1));

        txaIncidencia.setColumns(20);
        txaIncidencia.setLineWrap(true);
        txaIncidencia.setRows(5);
        txaIncidencia.setWrapStyleWord(true);
        txaIncidencia.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle de la incidencia", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jScrollPane1.setViewportView(txaIncidencia);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 530, 220));

        btnArchivo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnArchivo.setText("Agregue un archivo");
        jPanel2.add(btnArchivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, 180, 40));

        jPanel3.setBackground(new java.awt.Color(205, 0, 44));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEnviar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEnviar.setText("Enviar");
        jPanel3.add(btnEnviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 180, 40));

        btn_logout.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_logout.setText("Salir");
        btn_logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_logoutActionPerformed(evt);
            }
        });
        jPanel3.add(btn_logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, -1, 30));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 570, 100));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 570, 460));

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtCorreoStudent.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel4.add(txtCorreoStudent, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 170, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Correo:");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Carrera Univeritaria:");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 40, -1, 20));

        txtCarreraUniversitaria.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel4.add(txtCarreraUniversitaria, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, 170, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Codigo:");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Nombres:");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, -1, -1));

        txtNombre.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel4.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 210, -1));

        txtStudent_id.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel4.add(txtStudent_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 170, -1));

        add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 570, 80));
    }// </editor-fold>//GEN-END:initComponents

    private void btn_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_logoutActionPerformed
        // TODO add your handling code here:
        if (evt.getSource() == btn_logout) {
            frmregister_incidenseView Jframe = (frmregister_incidenseView) SwingUtilities.getWindowAncestor(this);
            Jframe.dispose();
            frmLoginView login = new frmLoginView();
            login.setVisible(true);
        }
    }//GEN-LAST:event_btn_logoutActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnArchivo;
    public javax.swing.JButton btnEnviar;
    public javax.swing.JButton btn_logout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_name_carrera;
    public javax.swing.JTextArea txaIncidencia;
    public javax.swing.JTextField txtCarreraUniversitaria;
    public javax.swing.JTextField txtCorreoStudent;
    public javax.swing.JTextField txtNombre;
    public javax.swing.JTextField txtStudent_id;
    // End of variables declaration//GEN-END:variables
}
