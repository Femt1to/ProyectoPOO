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
import static models.empleadoDao.rol_user;
import models.estudiante;
import models.estudianteDao;
import views.pnlSystemView;

public class StudentController implements ActionListener, MouseListener, KeyListener{

    private estudiante student;
    private estudianteDao studentDao;
    private pnlSystemView view;
    DefaultTableModel model = new DefaultTableModel();
    String rol = rol_user;

    public StudentController(estudiante student, estudianteDao studentDao, pnlSystemView view) {
        this.student = student;
        this.studentDao = studentDao;
        this.view = view;
        //Boton registrar estudiante
        this.view.btn_register_student.addActionListener(this);
        //Boton modificar estudiante
        this.view.btn_update_student.addActionListener(this);
        //Boton eliminar estudiante
        this.view.btn_delete_student.addActionListener(this);
        //Boton cancelar
        this.view.btn_cancel_student.addActionListener(this);
        this.view.student_table.addMouseListener(this);
        this.view.txt_search_student.addKeyListener(this);
        this.view.jLabelStudent.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btn_register_student) {
            //Verificar si losc ampos estan vacios
            if (view.txt_student_id.getText().equals("")
                    || view.txt_student_fullname.getText().equals("")
                    || view.txt_student_username.getText().equals("")
                    || view.txt_student_telephone.getText().equals("")
                    || view.txt_student_email.getText().equals("")
                    || String.valueOf(view.txt_student_password.getPassword()).equals("")
                    || view.cmb_carrera.getSelectedItem().toString().equals("")) {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
            } else {
                //Realizar la insercion
                student.setId(Integer.parseInt(view.txt_student_id.getText().trim()));
                student.setFull_name(view.txt_student_fullname.getText().trim());
                student.setUsername(view.txt_student_username.getText().trim());
                student.setTelephone(view.txt_student_telephone.getText().trim());
                student.setEmail(view.txt_student_email.getText().trim());
                student.setPassword(String.valueOf(view.txt_student_password.getPassword()));
                student.setCarrera_universitaria(view.cmb_carrera.getSelectedItem().toString());
                if (studentDao.registerStudentQuery(student)) {
                    cleanTable();
                    listAllStudent();
                    JOptionPane.showMessageDialog(null, "Estudiante registrado con exito");
                } else {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al registrar un estudiante");
                }
            }
        } else if (e.getSource() == view.btn_update_student) {
            if (view.txt_student_id.equals("")) {
                JOptionPane.showMessageDialog(null, "Selecciona una fila para continuar");
            } else {
                if (view.txt_student_id.getText().equals("")
                        || view.txt_student_fullname.getText().equals("")
                        || view.txt_student_username.getText().equals("")
                        || view.cmb_carrera.getSelectedItem().toString().equals("")) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
                } else {
                    student.setId(Integer.parseInt(view.txt_student_id.getText().trim()));
                    student.setFull_name(view.txt_student_fullname.getText().trim());
                    student.setUsername(view.txt_student_username.getText().trim());
                    student.setTelephone(view.txt_student_telephone.getText().trim());
                    student.setEmail(view.txt_student_email.getText().trim());
                    student.setPassword(String.valueOf(view.txt_student_password.getPassword()));
                    student.setCarrera_universitaria(view.cmb_carrera.getSelectedItem().toString());
                }
                if (studentDao.updateStudentQuery(student)) {
                    cleanTable();
                    cleanFields();
                    listAllStudent();
                    JOptionPane.showMessageDialog(null, "Datos del estudiante modificados con exito");
                } else {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al registrar un estudiante");
                }
            }

        }else if (e.getSource() == view.btn_delete_student) {
            int row = view.student_table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Debes seleccionar un estudiante para eliminar");
            }else{
                int id = Integer.parseInt(view.student_table.getValueAt(row, 0).toString());
                int question = JOptionPane.showConfirmDialog(null, "¿En realidad quiere eliminar a este empleado?");
                
                if (question == 0 && studentDao.deleteStudentQuery(id) != false) {
                    cleanFields();
                    cleanTable();
                    view.btn_register_student.setEnabled(true);
                    listAllStudent();
                    JOptionPane.showMessageDialog(null, "Estudiante eliminado con exito");
                }
                
            }
        }else if (e.getSource() == view.btn_cancel_student) {
            cleanFields();
            view.btn_register_student.setEnabled(true);
            view.txt_student_id.setEditable(true);
        }
    }

    public void listAllStudent() {
        List<estudiante> list = studentDao.listStudentQuery(view.txt_search_student.getText());
        model = (DefaultTableModel) view.student_table.getModel();
        Object[] row = new Object[6];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getFull_name();
            row[2] = list.get(i).getUsername();
            row[3] = list.get(i).getTelephone();
            row[4] = list.get(i).getEmail();
            row[5] = list.get(i).getCarrera_universitaria();
            model.addRow(row);
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource()==view.student_table) {
            int row = view.student_table.rowAtPoint(e.getPoint());
            view.txt_student_id.setText(view.student_table.getValueAt(row, 0).toString());
            view.txt_student_fullname.setText(view.student_table.getValueAt(row, 1).toString());
            view.txt_student_username.setText(view.student_table.getValueAt(row, 2).toString());
            view.txt_student_telephone.setText(view.student_table.getValueAt(row, 3).toString());
            view.txt_student_email.setText(view.student_table.getValueAt(row, 4).toString());
            view.cmb_carrera.setSelectedItem(view.student_table.getValueAt(row, 5).toString());
            
            view.txt_student_id.setEditable(false);
            view.btn_register_employee.setEnabled(false);
        }else if (e.getSource() == view.jLabelStudent) {
            //Caso Administrador
            if (rol.equals("Administrador")) {
                view.jTabbedPane1.setSelectedIndex(2);
                cleanTable();
                cleanFields();
                listAllStudent();
            } else {
                //Caso auxiliar
                view.jTabbedPane1.setEnabledAt(2, false);
                view.jLabelStudent.setEnabled(false);
                JOptionPane.showMessageDialog(null, "No tiene privilegios de administrador");
            }
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

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    public void cleanFields(){
        view.txt_student_id.setText("");
        view.txt_student_fullname.setText("");
        view.txt_student_username.setText("");
        view.txt_student_telephone.setText("");
        view.txt_student_email.setText("");
        view.txt_student_password.setText("");
        view.cmb_carrera.setSelectedIndex(0);
    }
    
    public void cleanTable(){
        for (int i = 0; i < model.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;
        }
    }

}
