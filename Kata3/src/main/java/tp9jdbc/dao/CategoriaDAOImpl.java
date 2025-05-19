package tp9jdbc.dao;

import tp9jdbc.config.ConexionDB;
import tp9jdbc.model.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAOImpl implements GenericDAO<Categoria> {

    private Connection conexion;

    public CategoriaDAOImpl() {
        this.conexion = ConexionDB.obtenerConexion();
    }

    @Override
    public void crear(Categoria c) {
        String sql = "INSERT INTO categorias (nombre, descripcion) VALUES (?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, c.getNombre());
            stmt.setString(2, c.getDescripcion());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Categoria leer(int id) {
        Categoria categoria = null;
        String sql = "SELECT * FROM categorias WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                categoria = new Categoria(rs.getInt("id"), rs.getString("nombre"), rs.getString("descripcion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoria;
    }

    @Override
    public void actualizar(Categoria c) {
        String sql = "UPDATE categorias SET nombre = ?, descripcion = ? WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, c.getNombre());
            stmt.setString(2, c.getDescripcion());
            stmt.setInt(3, c.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM categorias WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Categoria> listar() {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categorias";
        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                categorias.add(new Categoria(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;
    }

    public boolean existeNombre(String nombre) {
        String sql = "SELECT COUNT(*) FROM categorias WHERE nombre = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
