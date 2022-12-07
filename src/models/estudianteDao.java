package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class estudianteDao{
    ConnectionMySQL cn = new ConnectionMySQL();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;

    //Variables para enviar datos entre interfaces
    public static int id_student = 0;
    public static String full_name_student = "";
    public static String username_student = "";
    public static String email_student = "";
    public static String telephone_student = "";
    public static String carrera_student = "";

    public estudiante loginQuery(String user, String password) {
        String query = "SELECT*FROM student WHERE username = ? AND password = ?";
        estudiante student = new estudiante();
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setString(1, user);
            pst.setString(2, password);
            rs = pst.executeQuery();
            if (rs.next()) {
                student.setId(rs.getInt("id"));
                id_student = student.getId();
                student.setFull_name(rs.getString("full_name"));
                full_name_student = student.getFull_name();
                student.setUsername(rs.getString("username"));
                username_student = student.getUsername();
                student.setEmail(rs.getString("email"));
                email_student = student.getEmail();
                student.setTelephone(rs.getString("telephone"));
                telephone_student = student.getTelephone();
                student.setCarrera_universitaria(rs.getString("carrera_Universitaria"));
                carrera_student = student.getCarrera_universitaria();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener al estudiante");
        }
        return student;
    }

    public boolean registerStudentQuery(estudiante student) {
        String query = "INSERT INTO student (id,full_name,username,password,email"
                + ",telephone,carrera_Universitaria) VALUES (?,?,?,?,?,?,?)";
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setInt(1, student.getId());
            pst.setString(2, student.getFull_name());
            pst.setString(3, student.getUsername());
            pst.setString(4, student.getPassword());
            pst.setString(5, student.getEmail());
            pst.setString(6, student.getTelephone());
            pst.setString(7, student.getCarrera_universitaria());
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar al estudiante: " + e);
        }
        return false;

    }

    public List listStudentQuery(String value) {
        List<estudiante> list_student = new ArrayList();
        String query = "SELECT*FROM student ORDER BY carrera_Universitaria ASC";
        String query_search_student = "SELECT*FROM student WHERE id LIKE '%" + value + "%'";
        try {
            conn = cn.getConnection();
            if (value.equalsIgnoreCase("")) {
                pst = conn.prepareStatement(query);
                rs = pst.executeQuery();
            } else {
                pst = conn.prepareStatement(query_search_student);
                rs = pst.executeQuery();
            }
            while (rs.next()) {
                estudiante student = new estudiante();
                student.setId(rs.getInt("Id"));
                student.setFull_name(rs.getString("full_name"));
                student.setUsername(rs.getString("username"));
                student.setPassword(rs.getString("password"));
                student.setEmail(rs.getString("email"));
                student.setTelephone(rs.getString("telephone"));
                student.setCarrera_universitaria(rs.getString("carrera_Universitaria"));
                list_student.add(student);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return list_student;
    }

    public boolean updateStudentQuery(estudiante student) {
        String query = "UPDATE student SET full_name = ?, username = ?, telephone =?"
                + ",email = ?, password = ?, carrera_Universitaria = ? WHERE id = ?";
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setString(1, student.getFull_name());
            pst.setString(2, student.getUsername());
            pst.setString(3, student.getTelephone());
            pst.setString(4, student.getEmail());
            pst.setString(5, student.getPassword());
            pst.setString(6, student.getCarrera_universitaria());
            pst.setInt(7, student.getId());
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar los datos del estudiante " + e);
            return false;
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al modificar los datos del estudiante " + e);
            return false;
        }
        
    }

    public boolean deleteStudentQuery(int id) {
        String query = "DELETE FROM student WHERE id = "+ id;
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se puede eliminar un estudiante que tenga relacion con otra tabla " + e);
            return false;
        }
    }
}
