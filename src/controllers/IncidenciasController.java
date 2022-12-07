package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import static models.estudianteDao.carrera_student;
import static models.estudianteDao.email_student;
import static models.estudianteDao.full_name_student;
import static models.estudianteDao.id_student;
import static models.estudianteDao.username_student;
import models.incidencias;
import models.incidenciasDao;
import views.frmregister_incidenseView;
import views.pnlregister_incidenseView;

public class IncidenciasController implements ActionListener {
    //Encapsulamiento
    private incidencias incidense;
    private incidenciasDao incidenseDao;
    private pnlregister_incidenseView incidenseView;
    int id_incidense = 0;

    public IncidenciasController(incidencias incidense, incidenciasDao incidenseDao, pnlregister_incidenseView incidenseView) {
        this.incidense = incidense;
        this.incidenseDao = incidenseDao;
        this.incidenseView = incidenseView;
        //Boton registrar incidencia
        this.incidenseView.btnEnviar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Boton registrar incidencia
        if (e.getSource() == incidenseView.btnEnviar) {
            //Verificar que los campos no esten vacios
            if (incidenseView.txaIncidencia.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Es obligatorio que describas tu incidencia");
            } else {
                //Realizar la insercion
                incidense.setDescription(incidenseView.txaIncidencia.getText().trim());
                incidense.setId_student(id_student);
                if (incidenseDao.registerIncidenseQuery(incidense)) {
                    JOptionPane.showMessageDialog(null, "Incidencia registrado con exito");
                    incidense.setId_incidense(incidenseDao.idIncidense(id_student));
                    incidenseDao.registerIncidenseIdQuery(incidense);
                    frmregister_incidenseView Jframe = (frmregister_incidenseView) SwingUtilities.getWindowAncestor(this.incidenseView);
                    Jframe.dispose();

                } else {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al registrar la incidencia");
                }
            }
        }
    }
    //Rellena cajas de texto de la vista de Incidencias
    public void fillFields() {
        incidenseView.txtStudent_id.setText(username_student);
        incidenseView.txtStudent_id.getText();
        incidenseView.txtStudent_id.setEditable(false);
        incidenseView.txtCorreoStudent.setText(email_student);
        incidenseView.txtCorreoStudent.getText();
        incidenseView.txtCorreoStudent.setEditable(false);
        incidenseView.txtCarreraUniversitaria.setText(carrera_student);
        incidenseView.txtCarreraUniversitaria.getText();
        incidenseView.txtCarreraUniversitaria.setEditable(false);
        incidenseView.txtNombre.setText(full_name_student);
        incidenseView.txtNombre.getText();
        incidenseView.txtNombre.setEditable(false);
    }
}

