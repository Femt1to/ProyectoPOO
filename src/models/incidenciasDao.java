package models;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class incidenciasDao {

    ConnectionMySQL cn = new ConnectionMySQL();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;

    //Registrar incidencia
    public boolean registerIncidenseQuery(incidencias incidense) {
        String query = "INSERT INTO incidense(description,created, "
                + "student_id,status) VALUES(?,?,?,?)";
        Timestamp datetime = new Timestamp(new Date().getTime());
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setString(1, incidense.getDescription());
            pst.setTimestamp(2, datetime);
            pst.setInt(3, incidense.getId_student());
            pst.setString(4, "Inactiva");
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar los datos de la incidencia " + e);
            return false;
        }
    }

    //Registrar detalles de incidencia
    public boolean registerIncidenseIdQuery(incidencias incidense) {
        String query = "INSERT INTO incidense_details(incidense_id) VALUES(?)";
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setInt(1, incidense.getId_incidense());
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar los detalles de la incidencia " + e);
            return false;
        }
    }

    //Obtener id de incidencia  
    public int idIncidense(int id_student) {
        int id = 0;
        String query = "SELECT MAX(id) AS id FROM incidense WHERE student_id = ?";
        incidencias incidense = new incidencias();
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setInt(1, id_student);
            rs = pst.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return id;
    }

    //Listar incidencias
    public List listIncidenseQuery(String value) {
        List<incidencias> list_incidense = new ArrayList();
        String query = """
                       SELECT inc.id,st.email,st.full_name,inc.status,ind.employee_id,inc.created 
                       FROM incidense inc INNER JOIN student st ON inc.student_id = st.id 
                       INNER JOIN incidense_details ind ON inc.id = ind.incidense_id ORDER BY inc.id ASC""";
        String query_search_incidense = "SELECT inc.id,st.email,st.full_name,inc.status,ind.employee_id,inc.created "
                + "FROM incidense inc INNER JOIN student st ON inc.student_id = st.id INNER JOIN incidense_details "
                + "ind ON inc.id = ind.incidense_id WHERE inc.id LIKE '%" + value + "%'";
        try {
            conn = cn.getConnection();
            if (value.equalsIgnoreCase("")) {
                pst = conn.prepareStatement(query);
                rs = pst.executeQuery();
            } else {
                pst = conn.prepareStatement(query_search_incidense);
                rs = pst.executeQuery();
            }
            while (rs.next()) {
                incidencias incidense = new incidencias();
                incidense.setId_incidense(rs.getInt("id"));
                incidense.setEmail(rs.getString("email"));
                incidense.setFullname_student(rs.getString("full_name"));
                incidense.setStatus(rs.getString("status"));
                incidense.setId_employee(rs.getInt("employee_id"));
                incidense.setCreated(rs.getString("created"));
                list_incidense.add(incidense);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return list_incidense;
    }

    //Modificar estado de las incidencias
    public boolean updateIncidenseStatusQuery(incidencias incidense) {
        String query = "UPDATE incidense SET status = ? WHERE id = ?";
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setString(1, incidense.getStatus());
            pst.setInt(2, incidense.getId_incidense());
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al modificar la incidencia " + e);
            return false;
        }
    }

    //Modificar empleado de la incidencia
    public boolean updateIncidenseEmployeeQuery(incidencias incidense) {
        String query = "UPDATE incidense_details SET employee_id = ? WHERE incidense_id = ?";
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setInt(1, incidense.getId_employee());
            pst.setInt(2, incidense.getId_incidense());
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al modificar la incidencia " + e);
            return false;
        }
    }

    //Buscar email del estudiante en funcion a la incidencia
    public String emailStudent(int id_student) {
        String email = null;
        String query = "SELECT st.email FROM student st INNER JOIN incidense inc ON inc.student_id = st.id WHERE inc.student_id = ?";
        incidencias incidense = new incidencias();
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setInt(1, id_student);
            rs = pst.executeQuery();
            if (rs.next()) {
                email = rs.getString("email");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return email;
    }

    //Eliminar detalle de incidencia
    public boolean deleteDeatilIncidenseQuery(int id) {
        String query_Incidensedetail = "DELETE FROM incidense_details WHERE incidense_id = " + id;
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query_Incidensedetail);
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se puede eliminar la incidencia del estudiante" + e);
            return false;
        }

    }

    //Eliminar incidencia 
    public boolean deleteIncidenseQuery(int id) {
        String query = "DELETE FROM incidense WHERE id = " + id;
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se puede eliminar la incidencia del estudiante" + e);
            return false;
        }

    }
    //Metodo para ver reporte
    public String Reporte(int id_incidencia) {
        String report = null;
        String query = "SELECT st.*,inc.id,inc.status,inc.description,ind.employee_id,inc.created\n"
                + "FROM incidense inc INNER JOIN student st ON inc.student_id = st.id INNER JOIN incidense_details \n"
                + "ind ON inc.id = ind.incidense_id WHERE inc.id = ?";
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setInt(1, id_incidencia);
            rs = pst.executeQuery();
            while (rs.next()) {
                incidencias incidence = new incidencias();
                incidence.setId_incidense(rs.getInt("id"));
                incidence.setFullname_student(rs.getString("full_name"));
                incidence.setEmail(rs.getString("email"));
                incidence.setCarrera_student(rs.getString("carrera_Universitaria"));
                incidence.setCreated(rs.getString("created"));
                incidence.setDescription(rs.getString("Description"));
                report = "Nombre: "+incidence.getFullname_student()+"\nCorreo: "+incidence.getEmail()+"\nCarrera "
                        + "Universitaria: "+incidence.getCarrera_student()+
                        "\n\n----------------------------INCIDENCIA----------------------------\n\n id:       "+incidence.getId_incidense()+
                        "\n Creación: "+incidence.getCreated()+"\n\n Descripcion de la incidencia: \n"+incidence.getDescription();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return report;
    }

}

