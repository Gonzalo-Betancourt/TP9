package tp9jdbc.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static final String URL = "jdbc:mysql://localhost:3306/tp_9_program";
    private static final String USER = "root";
    private static final String PASS = "admin";

    public static Connection obtenerConexion() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos");
            throw new RuntimeException(e);
        }
    }
}
