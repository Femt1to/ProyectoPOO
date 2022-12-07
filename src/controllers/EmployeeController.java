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
import models.empleado;
import models.empleadoDao;
import static models.empleadoDao.id_user;
import static models.empleadoDao.rol_user;
import views.pnlSystemView;

public class EmployeeController implements ActionListener, MouseListener, KeyListener {

    private empleado employee;
    private empleadoDao employeeDao;
    private pnlSystemView view;
    String rol = rol_user;
    DefaultTableModel model = new DefaultTableModel();

    //Constructor
    public EmployeeController(empleado employee, empleadoDao employeeDao, pnlSystemView view) {
        this.employee = employee;
        this.employeeDao = employeeDao;
        this.view = view;

        //Boton registrar empleado
        this.view.btn_register_employee.addActionListener(this);
        //Boton de modificar al empleado
        this.view.btn_update_employee.addActionListener(this);
        //Boton de eliminar empleado
        this.view.btn_delete_employee.addActionListener(this);
        //Boton de cancelar
        this.view.btn_cancel_employee.addActionListener(this);
        //Boton de cambiar la contraseña
        this.view.btn_modify_data.addActionListener(this);
        //Interactua con jlabel
        this.view.jLabelUsuarios.addMouseListener(this);
        //Interactuar con la caja de busqueda
        this.view.txt_search_employee.addKeyListener(this);
        //Interactuar con la tabla
        this.view.employee_table.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Boton de Registrar
        if (e.getSource() == view.btn_register_employee) {
            //Verificar si los campos estan vacios
            if (view.txt_employee_id.getText().equals("")
                    || view.txt_employee_fullname.getText().equals("")
                    || view.txt_employee_username.getText().equals("")
                    || view.txt_employee_address.getText().equals("")
                    || view.txt_employee_telephone.getText().equals("")
                    || view.txt_employee_email.getText().equals("")
                    || String.valueOf(view.txt_employee_password.getPassword()).equals("")
                    || view.cmb_rol.getSelectedItem().toString().equals("")) {

                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
            } else {
                //Realizar la insercion 
                employee.setId(Integer.parseInt(view.txt_employee_id.getText().trim()));
                employee.setFull_name(view.txt_employee_fullname.getText().trim());
                employee.setUsername(view.txt_employee_username.getText().trim());
                employee.setAddress(view.txt_employee_address.getText().trim());
                employee.setTelephone(view.txt_employee_telephone.getText().trim());
                employee.setEmail(view.txt_employee_email.getText().trim());
                employee.setPassword(String.valueOf(view.txt_employee_password.getPassword()));
                employee.setRol(view.cmb_rol.getSelectedItem().toString());
                if (employeeDao.registerUserQuery(employee)) {
                    cleanTable();
                    listAllEmployees();
                    JOptionPane.showMessageDialog(null, "Empleado registrado con exito");
                } else {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al registrar un empleado");
                }
            }

            //Boton de Modificar
        } else if (e.getSource() == view.btn_update_employee) {
            //Verificar si la id del empleado esta vacio
            if (view.txt_employee_id.equals("")) {
                JOptionPane.showMessageDialog(null, "Selecciona una fila para continuar");
            } else {
                //  Verificar que todos los campos no esten vacios
                if (view.txt_employee_id.getText().equals("")
                        || view.txt_employee_fullname.getText().equals("")
                        || view.txt_employee_username.getText().equals("")
                        || view.cmb_rol.getSelectedItem().toString().equals("")) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");

                } else {
                    //Modificar datos del usuario
                    employee.setId(Integer.parseInt(view.txt_employee_id.getText().trim()));
                    employee.setFull_name(view.txt_employee_fullname.getText().trim());
                    employee.setUsername(view.txt_employee_username.getText().trim());
                    employee.setAddress(view.txt_employee_address.getText().trim());
                    employee.setTelephone(view.txt_employee_telephone.getText().trim());
                    employee.setEmail(view.txt_employee_email.getText().trim());
                    employee.setPassword(String.valueOf(view.txt_employee_password.getPassword()));
                    employee.setRol(view.cmb_rol.getSelectedItem().toString());
                    if (employeeDao.updateUserQuery(employee)) {
                        cleanTable();
                        cleanFields();
                        listAllEmployees();
                        JOptionPane.showMessageDialog(null, "Datos del empleado modificados con exito");
                    } else {
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error al registrar un empleado");
                    }
                }
            }

            //Boton de Eliminar
        } else if (e.getSource() == view.btn_delete_employee) {
            //Almacena numero de fila
            int row = view.employee_table.getSelectedRow();
            //No eliminar si no almacena numero de fila
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Debes seleccionar un empleado para eliminar");
            } else if (view.employee_table.getValueAt(row, 0).equals(id_user)) {
                JOptionPane.showMessageDialog(null, "No puede eliminar el usuario autenticado");
            } else {
                //Elimina usuario
                int id = Integer.parseInt(view.employee_table.getValueAt(row, 0).toString());
                int question = JOptionPane.showConfirmDialog(null, "¿En realidad quieres eliminar a este empleado");
                if (question == 0 && employeeDao.deleteUserQuery(id) != false) {
                    cleanFields();
                    cleanTable();
                    view.btn_register_employee.setEnabled(true);
                    view.txt_employee_password.setEnabled(true);
                    listAllEmployees();
                    JOptionPane.showMessageDialog(null, "Empleado eliminado con exito");
                }
            }

            //Boton cancelar
        } else if (e.getSource() == view.btn_cancel_employee) {
            cleanFields();
            view.btn_register_employee.setEnabled(true);
            view.txt_employee_password.setEnabled(true);
            view.txt_employee_id.setEnabled(true);

            //Boton cambiar contraseña
        } else if (e.getSource() == view.btn_modify_data) {
            String password = String.valueOf(view.txt_password_modify.getPassword());
            String confirm_password = String.valueOf(view.txt_password_modify_confirm.getPassword());
            //Verificar que las cajas de texto esten vacias
            if (!password.equals("") && !confirm_password.equals("")) {
                //Verificar que las contraseñas sean iguales
                if (password.equals(confirm_password)) {
                    employee.setPassword(String.valueOf(view.txt_password_modify.getPassword()));
                    //Cambio de contraseña
                    if (employeeDao.updateEmployeePassword(employee) != false) {
                        JOptionPane.showMessageDialog(null, "Contraseña modificada con exito");
                    } else {
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error al modificar la contraseña ");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
                }
            }
        }

    }

    //Metodo para listar todos los empleados
    public void listAllEmployees() {
        if (rol.equals("Administrador")) {
            //Creamos un objeto "list" de la interfaz List
            List<empleado> list = employeeDao.listUserQuery(view.txt_search_employee.getText());
            model = (DefaultTableModel) view.employee_table.getModel();
            //Creamos la variable "row" de tipo Object 
            Object[] row = new Object[7];//Definimos la cantidad de columnas de la tabla
            //Recorrido de "row"
            for (int i = 0; i < list.size(); i++) {
                row[0] = list.get(i).getId();
                row[1] = list.get(i).getFull_name();
                row[2] = list.get(i).getUsername();
                row[3] = list.get(i).getAddress();
                row[4] = list.get(i).getTelephone();
                row[5] = list.get(i).getEmail();
                row[6] = list.get(i).getRol();
                model.addRow(row);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {  //Metodo que se maneja cuando presiona y suelta el mouse
        if (e.getSource() == view.employee_table) {
            //Capturar fila
            int row = view.employee_table.rowAtPoint(e.getPoint());
            //Rellenar cajas de texto  
            view.txt_employee_id.setText(view.employee_table.getValueAt(row, 0).toString());
            view.txt_employee_fullname.setText(view.employee_table.getValueAt(row, 1).toString());
            view.txt_employee_username.setText(view.employee_table.getValueAt(row, 2).toString());
            view.txt_employee_address.setText(view.employee_table.getValueAt(row, 3).toString());
            view.txt_employee_telephone.setText(view.employee_table.getValueAt(row, 4).toString());
            view.txt_employee_email.setText(view.employee_table.getValueAt(row, 5).toString());
            view.cmb_rol.setSelectedItem(view.employee_table.getValueAt(row, 6).toString());
            //Desabilita cajas de texto 
            view.txt_employee_id.setEditable(false);
            view.txt_employee_password.setEnabled(false);
            view.btn_register_employee.setEnabled(false);
        } else if (e.getSource() == view.jLabelUsuarios) {
            //Caso Administrador
            if (rol.equals("Administrador")) {
                view.jTabbedPane1.setSelectedIndex(1);
                cleanTable();
                cleanFields();
                listAllEmployees();
            } else {
                //Caso auxiliar
                view.jTabbedPane1.setEnabledAt(1, false);
                view.jLabelUsuarios.setEnabled(false);
                JOptionPane.showMessageDialog(null, "No tiene privilegios de administrador");
            }
        }
    }

    //Metodo que se maneja cuando presiona el mouse
    @Override
    public void mousePressed(MouseEvent e) {

    }

    //Metodo que se maneja cuando suelta el mouse
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    //Metodo que se maneja cuando el mouse ingresa al componente
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    //Metodo que se maneja cuando el mouse sale del componente
    @Override
    public void mouseExited(MouseEvent e) {

    }

    //Metodos abstractos de KeyListener: interfaz que se ocupa de los cambios en el estado de las teclas de nuestro teclado. 
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == view.txt_search_employee) {
            cleanTable();
            listAllEmployees();
        }
    }

    public void cleanFields() {
        view.txt_employee_id.setText("");
        view.txt_employee_id.setEditable(true);
        view.txt_employee_fullname.setText("");
        view.txt_employee_username.setText("");
        view.txt_employee_address.setText("");
        view.txt_employee_telephone.setText("");
        view.txt_employee_email.setText("");
        view.txt_employee_password.setText("");
        view.cmb_rol.setSelectedIndex(0);
    }

    //Metodo para limpiar tabla
    public void cleanTable() {
        for (int i = 0; i < model.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;
        }
    }
}
