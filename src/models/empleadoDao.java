package models;

import java.sql.Connection; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class empleadoDao{
    ConnectionMySQL cn = new ConnectionMySQL();
    Connection conn; 
    PreparedStatement pst;
    ResultSet rs; 

    //Variables para enviar datos entre interfaces
    public static int id_user = 0;
    public static String full_name_user = "";
    public static String username_user = "";
    public static String address_user = "";
    public static String telephone_user = "";
    public static String email_user = "";
    public static String rol_user = "";
    
    public empleado loginQuery(String user, String password) {
        String query = "SELECT*FROM employee WHERE username = ? AND password = ?";
        empleado us = new empleado();
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setString(1, user);
            pst.setString(2, password);
            rs = pst.executeQuery();
            if (rs.next()) {
                us.setId(rs.getInt("id"));
                id_user = us.getId();
                us.setFull_name(rs.getString("full_name"));
                full_name_user = us.getFull_name();
                us.setUsername(rs.getString("username"));
                username_user = us.getUsername();
                us.setAddress(rs.getString("address"));
                address_user = us.getAddress();
                us.setTelephone(rs.getString("telephone"));
                telephone_user = us.getTelephone();
                us.setEmail(rs.getString("email"));
                email_user = us.getEmail();
                us.setRol(rs.getString("roluser"));
                rol_user = us.getRol();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener al usuario");
        }
        return us;
    }

    //Metodo para registrar usuario
    public boolean registerUserQuery(empleado employee) {
        String query = "INSERT INTO employee (id,full_name, username, address, telephone,"
                + "email, password, created, updated,roluser) VALUES (?,?,?,?,?,?,?,?,?,?)";
        Timestamp datetime = new Timestamp(new Date().getTime());
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setInt(1, employee.getId());
            pst.setString(2, employee.getFull_name());
            pst.setString(3, employee.getUsername());
            pst.setString(4, employee.getAddress());
            pst.setString(5, employee.getTelephone());
            pst.setString(6, employee.getEmail());
            pst.setString(7, employee.getPassword());
            pst.setTimestamp(8, datetime);
            pst.setTimestamp(9, datetime);
            pst.setString(10, employee.getRol());
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar al usuario: " + e);
        }
        return false;
    }

    //Metodo para listar empleado
    public List listUserQuery(String value) {
        List<empleado> list_employees = new ArrayList();
        String query = "SELECT*FROM employee ORDER BY roluser ASC";
        String query_search_employee = "SELECT*FROM employee WHERE id LIKE '%" + value + "%'";
        try {
            conn = cn.getConnection();
            if (value.equalsIgnoreCase("")) {
                pst = conn.prepareStatement(query);
                rs = pst.executeQuery();
            } else {
                pst = conn.prepareStatement(query_search_employee);
                rs = pst.executeQuery();
            }
            while (rs.next()) {
                empleado employee = new empleado();
                employee.setId(rs.getInt("id"));
                employee.setFull_name(rs.getString("full_name"));
                employee.setUsername(rs.getString("username"));
                employee.setAddress(rs.getString("address"));
                employee.setTelephone(rs.getString("telephone"));
                employee.setEmail(rs.getString("email"));
                employee.setRol(rs.getString("roluser"));
                list_employees.add(employee);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return list_employees;
    }

    //Metodo para modificar empleado
    public boolean updateUserQuery(empleado employee) {
        String query = "UPDATE employee SET full_name = ?, username = ?, address = ?, telephone = ?, email = ?, roluser = ?, updated = ?"
                + "WHERE id = ?";
        Timestamp datetime = new Timestamp(new Date().getTime());
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setString(1, employee.getFull_name());
            pst.setString(2, employee.getUsername());
            pst.setString(3, employee.getAddress());
            pst.setString(4, employee.getTelephone());
            pst.setString(5, employee.getEmail());
            pst.setString(6, employee.getRol());
            pst.setTimestamp(7, datetime);
            pst.setInt(8, employee.getId());
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar los datos del empleado " + e);
            return false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al modificar los datos del empleado " + e);
            return false;
        }
    }

    //Metodo para eliminar empleado
    public boolean deleteUserQuery(int id) {
        String query = "DELETE FROM employee WHERE id = " + id;
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No puede eliminar un empleado que tenga relacion con otra tabla " + e);
            return false;
        }
    }

    //Cambiar la contraseña
    public boolean updateEmployeePassword(empleado employee) {
        String query = "UPDATE employee SET password = ? WHERE username = '" + username_user + "'";
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setString(1, employee.getPassword());
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al modificar la contraseña " + e);
            return false;
        }

    }
}

