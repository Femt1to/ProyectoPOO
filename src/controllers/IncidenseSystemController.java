package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static models.empleadoDao.full_name_user;
import static models.empleadoDao.id_user;
import models.incidencias;
import models.incidenciasDao;
import views.pnlSystemView;
import views.frmPanelEmail;
import views.frmViewIncidense;

public class IncidenseSystemController implements ActionListener, MouseListener, KeyListener {

    //Encapsulamiento
    private incidencias incidense;
    private incidenciasDao incidenseDao;
    private pnlSystemView view;
    //Creamos un objeto de tipo DefaultTableModel para interactuar con la tabla Incidencia
    DefaultTableModel model = new DefaultTableModel();

    public IncidenseSystemController(incidencias incidense, incidenciasDao incidenseDao, pnlSystemView view) {
        this.incidense = incidense;
        this.incidenseDao = incidenseDao;
        this.view = view;
        //Boton modificar
        this.view.btn_update_incidense.addActionListener(this);
        //Boton enviar
        this.view.btn_Answer.addActionListener(this);
        //Boton ver
        this.view.btn_viewIncidense.addActionListener(this);
        //Boton eliminar
        this.view.btn_delete_incidense.addActionListener(this);
        //Boton cancelar
        this.view.btn_cancel_incidense.addActionListener(this);
        //Tabla de incidencias
        this.view.incidense_table.addMouseListener(this);
        this.view.txt_search_incidense.addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Boton de modificar
        if (e.getSource() == view.btn_update_incidense) {
            //Verificar si la id de la incidencia esta vacia
            if (view.txt_incidense_id.equals("")) {
                JOptionPane.showMessageDialog(null, "Selecciona una fila para continuar");
            } else {

                //Verificar que todos los campos no esten vacios
                if (view.cmb_estado.getSelectedItem().toString().equals("")
                        || view.txt_incidense_id.getText().equals("")
                        || view.txt_incidense_student.getText().equals("")
                        || view.txt_incidense_correo.getText().equals("")
                        || view.txt_incidense_idEmployee.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");

                } else {
                    //Modificar datos de la incidencia
                    incidense.setStatus(view.cmb_estado.getSelectedItem().toString());
                    incidense.setId_employee(Integer.parseInt(view.txt_incidense_idEmployee.getText().trim()));
                    incidense.setId_incidense(Integer.parseInt(view.txt_incidense_id.getText().trim()));
                    if (incidenseDao.updateIncidenseStatusQuery(incidense)) {
                        JOptionPane.showMessageDialog(null, "Datos de la incidencia modificados con exito");
                        incidenseDao.updateIncidenseEmployeeQuery(incidense);
                        cleanTable();
                        cleanFields();
                        listAllIncidense();
                    } else {
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error al modificar una incidencia");
                    }
                }
            }
            //Eliminar incidencia
        } else if (e.getSource() == view.btn_delete_incidense) {
            int row = view.incidense_table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Debes seleccionar una incidencia para eliminar");
            } else {
                int id = Integer.parseInt(view.incidense_table.getValueAt(row, 0).toString());
                int question = JOptionPane.showConfirmDialog(null, "¿En realidad quiere eliminar esta incidencia?");

                if (question == 0 && incidenseDao.deleteDeatilIncidenseQuery(id) != false) {
                    incidenseDao.deleteIncidenseQuery(id);
                    cleanFields();
                    cleanTable();
                    listAllIncidense();
                    JOptionPane.showMessageDialog(null, "Incidencia eliminada con exito");
                }
            }
            //Abrir el Mail 
        } else if (e.getSource() == view.btn_Answer) {
            frmPanelEmail panelEmail = new frmPanelEmail();
            panelEmail.setVisible(true);
            //Cancelar incidencia
        } else if (e.getSource() == view.btn_cancel_incidense) {
            cleanFields();
            //Ver incidencia
        } else if (e.getSource() == view.btn_viewIncidense) {
            frmViewIncidense viewIncidense = new frmViewIncidense();
            viewIncidense.setVisible(true);
            String reporte = incidenseDao.Reporte(Integer.parseInt(view.txt_incidense_id.getText().trim()));
            viewIncidense.txaReport.setText(reporte);
            viewIncidense.txaReport.getText();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == view.incidense_table) {
            //Capturar fila
            int row = view.incidense_table.rowAtPoint(e.getPoint());
            //Rellenar cajas de texto 
            view.txt_incidense_id.setText(view.incidense_table.getValueAt(row, 0).toString());
            view.txt_incidense_student.setText(view.incidense_table.getValueAt(row, 1).toString());
            view.txt_incidense_correo.setText(view.incidense_table.getValueAt(row, 2).toString());
            view.txt_incidense_idEmployee.setText(String.valueOf(id_user));
            view.txt_incidense_idEmployee.getText();
            view.txt_incidense_nameEmpleado.setText(full_name_user);
            view.txt_incidense_nameEmpleado.getText();
            view.cmb_estado.setSelectedItem(view.incidense_table.getValueAt(row, 3).toString());
            //Desabilitaremos algunas cajas de texto
            view.txt_incidense_id.setEditable(false);
            view.txt_incidense_student.setEditable(false);
            view.txt_incidense_correo.setEditable(false);
            view.txt_incidense_nameEmpleado.setEditable(false);
            view.txt_incidense_idEmployee.setEditable(false);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    //Limpiar cajas de texto
    public void cleanFields() {
        view.txt_incidense_id.setText("");
        view.txt_incidense_student.setText("");
        view.txt_incidense_correo.setText("");
        view.txt_incidense_idEmployee.setText("");
        view.txt_incidense_nameEmpleado.setText("");
        view.cmb_estado.setSelectedIndex(0);
    }

    //Limpiar tabla de incidencias
    public void cleanTable() {
        for (int i = 0; i < model.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;
        }
    }

    //Listar todas las incidencias
    public void listAllIncidense() {
        List<incidencias> list = incidenseDao.listIncidenseQuery(view.txt_search_incidense.getText());
        model = (DefaultTableModel) view.incidense_table.getModel();
        Object[] row = new Object[6];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getId_incidense();
            row[1] = list.get(i).getFullname_student();
            row[2] = list.get(i).getEmail();
            row[3] = list.get(i).getStatus();
            row[4] = list.get(i).getId_employee();
            row[5] = list.get(i).getCreated();
            model.addRow(row);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == view.txt_search_incidense) {
            cleanTable();
            listAllIncidense();
        }
    }

}
