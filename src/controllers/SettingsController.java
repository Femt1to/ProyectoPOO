package controllers;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static models.empleadoDao.address_user;
import static models.empleadoDao.email_user;
import static models.empleadoDao.full_name_user;
import static models.empleadoDao.id_user;
import static models.empleadoDao.telephone_user;
import views.pnlSystemView;

public class SettingsController implements MouseListener {

    private pnlSystemView view;
    //Constructor

    public SettingsController(pnlSystemView view) {
        this.view = view;
        this.view.jLabelUsuarios.addMouseListener(this);
        this.view.jLabelConfiguracion.addMouseListener(this);
        this.view.jLabelIncidense.addMouseListener(this);
        this.view.jLabelStudent.addMouseListener(this);
        Profile();
    }

    //Asignar el perfil del usuario
    public void Profile() {
        this.view.txt_id_profile.setText("" + id_user);
        this.view.txt_name_profile.setText(full_name_user);
        this.view.txt_phone_profile.setText(telephone_user);
        this.view.txt_address_profile.setText(address_user);
        this.view.txt_email_profile.setText(email_user);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == view.jLabelIncidense) {
            view.jPanelIncidense.setBackground(new Color(204,204,255));
            view.jLabelIncidense.setForeground(new Color(0,0,0));
        } else if (e.getSource() == view.jLabelUsuarios) {
            view.jPanelUsers.setBackground(new Color(204,204,255));
            view.jLabelUsuarios.setForeground(new Color(0,0,0));
        } else if (e.getSource() == view.jLabelStudent) {
            view.jPanelStudent.setBackground(new Color(204,204,255));
            view.jLabelStudent.setForeground(new Color(0,0,0));
        } else if (e.getSource() == view.jLabelConfiguracion) {
            view.jPanelSettings.setBackground(new Color(204,204,255));
            view.jLabelConfiguracion.setForeground(new Color(0,0,0));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == view.jLabelIncidense) {
            view.jPanelIncidense.setBackground(new Color(205,0,44));
            view.jLabelIncidense.setForeground(new Color(255,255,255));
        } else if (e.getSource() == view.jLabelUsuarios) {
            view.jPanelUsers.setBackground(new Color(205,0,44));
            view.jLabelUsuarios.setForeground(new Color(255,255,255));
        } else if (e.getSource() == view.jLabelStudent) {
            view.jPanelStudent.setBackground(new Color(205,0,44));
            view.jLabelStudent.setForeground(new Color(255,255,255));
        } else if (e.getSource() == view.jLabelConfiguracion) {
            view.jPanelSettings.setBackground(new Color(205,0,44));
            view.jLabelConfiguracion.setForeground(new Color(255,255,255));
        }
    }
}
