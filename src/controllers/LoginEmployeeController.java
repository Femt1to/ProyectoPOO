package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import models.empleado;
import models.empleadoDao;
import views.frmLoginView;
import views.pnlLoginView;
import views.frmSystemView;

public class LoginEmployeeController implements ActionListener {

    //Atributos
    private empleado user_employee;
    private final empleadoDao employeeDao;
    private final pnlLoginView login_view;
    

    public LoginEmployeeController(empleado user_employee, empleadoDao employeeDao, pnlLoginView login_view) {
        this.user_employee = user_employee;
        this.employeeDao = employeeDao;
        this.login_view = login_view;
        //Boton de ingresar
        this.login_view.btn_enter.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = login_view.txt_username.getText().trim();
        String pass = String.valueOf(login_view.txt_password.getPassword());

        if (e.getSource() == login_view.btn_enter) {
            if (!user.equals("") || !pass.equals("")) {
                user_employee = employeeDao.loginQuery(user, pass);
                if (user_employee.getUsername() != null) {
                    if (user_employee.getRol().equals("Administrador")) {
                        frmSystemView admin = new frmSystemView();
                        admin.setVisible(true);
                    } else {
                        frmSystemView aux = new frmSystemView();
                        aux.setVisible(true);
                    }
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
