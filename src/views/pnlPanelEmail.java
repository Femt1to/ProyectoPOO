package views;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import models.incidencias;
import models.incidenciasDao;

public class pnlPanelEmail extends javax.swing.JPanel {

    private static String emailFrom = "saeutppruebajava23@gmail.com";
    private static String passwordFrom = "garmogglrgkrxxbs";
    private String emailTo;
    private String subject;
    private String content;
    private Properties mProperties;
    private Session mSession;
    private MimeMessage mCorreo;
    incidencias incidense;
    incidenciasDao incidenseDao;

    public pnlPanelEmail() {
        initComponents();
        setSize(565, 580);
        mProperties = new Properties();
    }
    private void createEmail() {
        emailTo = txtDestiny.getText().trim();
        subject = txtAsunto.getText().trim();
        content = txtMensaje.getText().trim();
        // Simple mail transfer protocol
        mProperties.put("mail.smtp.host", "smtp.gmail.com");
        mProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        mProperties.setProperty("mail.smtp.starttls.enable", "true");
        mProperties.setProperty("mail.smtp.port", "587");
        mProperties.setProperty("mail.smtp.user", emailFrom);
        mProperties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        mProperties.setProperty("mail.smtp.auth", "true");

        mSession = Session.getDefaultInstance(mProperties);

        try {
            mCorreo = new MimeMessage(mSession);
            mCorreo.setFrom(new InternetAddress(emailFrom));
            mCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            mCorreo.setSubject(subject);
            mCorreo.setText(content, "ISO-8859-1", "html");

        } catch (AddressException ex) {
            Logger.getLogger(pnlPanelEmail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(pnlPanelEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sendEmail() {
        try {
            Transport mTransport = mSession.getTransport("smtp");
            mTransport.connect(emailFrom, passwordFrom);
            mTransport.sendMessage(mCorreo, mCorreo.getRecipients(Message.RecipientType.TO));
            mTransport.close();
            
            JOptionPane.showMessageDialog(null, "Correo enviado");
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(pnlPanelEmail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(pnlPanelEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        label_name_carrera = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMensaje = new javax.swing.JTextArea();
        jPanel9 = new javax.swing.JPanel();
        btnEnviarMail = new javax.swing.JButton();
        jLabel53 = new javax.swing.JLabel();
        txtAsunto = new javax.swing.JTextField();
        txtDestiny = new javax.swing.JTextField();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(205, 0, 44));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Servicio de Atención al Estudiante - SAE");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 500, 50));
        jPanel4.add(label_name_carrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, 140, 20));

        add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 570, 60));

        jPanel8.setBackground(new java.awt.Color(204, 204, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel52.setText("Asunto:");
        jPanel8.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 90, -1));

        txtMensaje.setColumns(20);
        txtMensaje.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMensaje.setLineWrap(true);
        txtMensaje.setRows(5);
        txtMensaje.setWrapStyleWord(true);
        txtMensaje.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mensaje", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jScrollPane1.setViewportView(txtMensaje);

        jPanel8.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 530, 270));

        jPanel9.setBackground(new java.awt.Color(205, 0, 44));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEnviarMail.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEnviarMail.setText("Enviar");
        btnEnviarMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarMailActionPerformed(evt);
            }
        });
        jPanel9.add(btnEnviarMail, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 180, 40));

        jPanel8.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 430, 580, 70));

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel53.setText("Destinatario:");
        jPanel8.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, -1));

        txtAsunto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel8.add(txtAsunto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 380, 40));

        txtDestiny.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDestiny.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDestinyActionPerformed(evt);
            }
        });
        jPanel8.add(txtDestiny, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 380, 40));

        add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 570, 490));
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnviarMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarMailActionPerformed
        // TODO add your handling code here:
        createEmail();
        sendEmail();
    }//GEN-LAST:event_btnEnviarMailActionPerformed

    private void txtDestinyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDestinyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDestinyActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnEnviarMail;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_name_carrera;
    public javax.swing.JTextField txtAsunto;
    public javax.swing.JTextField txtDestiny;
    public javax.swing.JTextArea txtMensaje;
    // End of variables declaration//GEN-END:variables
}
