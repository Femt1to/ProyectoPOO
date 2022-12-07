package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMySQL {

    //Definimos las variables con los datos de conexion
    private String database_name = "saeincidense";
    private String user = "jk9s9526sv2a1aqa9dva";
    private String password = "pscale_pw_CrrbJ6FaXkl2nLl8O5kWQo7PapQATig9izV0mEIGbiM";
    private String url = "jdbc:mysql://us-east.connect.psdb.cloud:3306/" + database_name;

    Connection conn = null;

    //Metodo para conectar java con mySQL
    public Connection getConnection() {
        try {
            //obtener valor del Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Obtener la conexion
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            System.out.println("Ha ocurrido un ClassNotFoundException " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Ha ocurrido un SQLExcpetion " + e.getMessage());
        }
        return conn;
    }
}
