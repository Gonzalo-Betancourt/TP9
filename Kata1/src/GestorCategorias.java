import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestorCategorias {
    private Connection conexion;

    public GestorCategorias(Connection conexion) {
        this.conexion = conexion;
    }

    public void agregarCategoria(String nombre, String descripcion) throws SQLException {
        String sql = "INSERT INTO categorias(nombre, descripcion) VALUES (?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, descripcion);
            stmt.executeUpdate();
        }
    }

    public Categoria mostrarCategoria(int id) throws SQLException {
        String sql = "SELECT * FROM categorias WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Categoria(rs.getInt("id"), rs.getString("nombre"), rs.getString("descripcion"));
            } else {
                return null;
            }
        }
    }

    public List<Categoria> listarCategorias() throws SQLException {
        String sql = "SELECT * FROM categorias";
        List<Categoria> lista = new ArrayList<>();
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Categoria(rs.getInt("id"), rs.getString("nombre"), rs.getString("descripcion")));
            }
        }
        return lista;
    }

    public void actualizarCategoria(int id, String nombre, String descripcion) throws SQLException {
        String sql = "UPDATE categorias SET nombre = ?, descripcion = ? WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, descripcion);
            stmt.setInt(3, id);
            stmt.executeUpdate();
        }
    }

    public void eliminarCategoria(int id) throws SQLException {
        String sql = "DELETE FROM categorias WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public boolean existeNombre(String nombre) throws SQLException {
        String sql = "SELECT COUNT(*) FROM categorias WHERE nombre = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
        }
        return false;
    }
}
