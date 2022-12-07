package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import models.estudiante;
import models.estudianteDao;
import views.frmLoginView;
import views.frmregister_incidenseView;
import views.pnlLoginView;

public class LoginStudentController implements ActionListener {
//Atributos

    private estudiante user_student;
    private final estudianteDao studentDao;
    private final pnlLoginView login_view;

    public LoginStudentController(estudiante user_student, estudianteDao studentDao, pnlLoginView login_view) {
        this.user_student = user_student;
        this.studentDao = studentDao;
        this.login_view = login_view;
        //Boton ingresar
        this.login_view.btn_enterStudent.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = login_view.txt_studentName.getText().trim();
        String pass = String.valueOf(login_view.txt_studentPassword.getPassword());
        if (e.getSource() == login_view.btn_enterStudent) {
            if (!user.equals("") || !pass.equals("")) {
                user_student = studentDao.loginQuery(user, pass);

                if (user_student.getUsername() != null) {
                    frmregister_incidenseView incidense = new frmregister_incidenseView();
                    incidense.setVisible(true);
                    frmLoginView Jframe = (frmLoginView) SwingUtilities.getWindowAncestor(this.login_view);
                    Jframe.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecta");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Los campos estan vacios");
            }
        }
    }

}
