import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tp_9_program", "root", "admin")) {
            GestorCategorias gestor = new GestorCategorias(conn);
            
            if (!gestor.existeNombre("Electrónica")) {
                gestor.agregarCategoria("Electrónica", "Dispositivos tecnológicos");
            }

            List<Categoria> categorias = gestor.listarCategorias();
            categorias.forEach(c -> System.out.println(c.getNombre()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}