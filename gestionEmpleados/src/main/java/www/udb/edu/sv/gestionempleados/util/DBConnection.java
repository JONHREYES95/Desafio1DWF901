package www.udb.edu.sv.gestionempleados.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/GestionEmpleados?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "qwerty";
    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";

    static {
        try {
            // Registrar el driver JDBC
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al cargar el driver JDBC", e);
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al establecer la conexión con la base de datos", e);
        }
        return connection;
    }
}
