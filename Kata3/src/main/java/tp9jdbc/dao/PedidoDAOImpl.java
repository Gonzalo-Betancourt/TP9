package tp9jdbc.dao;

import tp9jdbc.config.ConexionDB;
import tp9jdbc.model.ItemPedido;
import tp9jdbc.model.Pedido;

import java.sql.*;
import java.util.List;

public class PedidoDAOImpl {

    private Connection conexion;

    public PedidoDAOImpl() {
        this.conexion = ConexionDB.obtenerConexion();
    }

    public void crearPedido(Pedido pedido) throws SQLException {
        String sqlPedido = "INSERT INTO pedidos (fecha, total) VALUES (?, ?)";
        String sqlItem = "INSERT INTO items_pedido (pedido_id, producto_id, cantidad, subtotal) VALUES (?, ?, ?, ?)";
        String sqlStock = "UPDATE productos SET cantidad = cantidad - ? WHERE id = ?";

        try {
            conexion.setAutoCommit(false);

            // Insertar pedido
            PreparedStatement stmtPedido = conexion.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS);
            stmtPedido.setDate(1, Date.valueOf(pedido.getFecha()));
            stmtPedido.setDouble(2, pedido.getTotal());
            stmtPedido.executeUpdate();

            ResultSet rs = stmtPedido.getGeneratedKeys();
            if (!rs.next()) throw new SQLException("No se pudo obtener el ID del pedido.");
            int idPedido = rs.getInt(1);

            // Insertar ítems y actualizar stock
            for (ItemPedido item : pedido.getItems()) {
                if (item.getCantidad() <= 0) throw new SQLException("Cantidad inválida.");
                if (item.getCantidad() > item.getProducto().getCantidad())
                    throw new SQLException("Stock insuficiente para " + item.getProducto().getNombre());

                // Insertar ítem
                PreparedStatement stmtItem = conexion.prepareStatement(sqlItem);
                stmtItem.setInt(1, idPedido);
                stmtItem.setInt(2, item.getProducto().getId());
                stmtItem.setInt(3, item.getCantidad());
                stmtItem.setDouble(4, item.getSubtotal());
                stmtItem.executeUpdate();

                // Descontar stock
                PreparedStatement stmtStock = conexion.prepareStatement(sqlStock);
                stmtStock.setInt(1, item.getCantidad());
                stmtStock.setInt(2, item.getProducto().getId());
                stmtStock.executeUpdate();
            }

            conexion.commit();
            System.out.println("✅ Pedido registrado correctamente.");

        } catch (SQLException e) {
            conexion.rollback();
            System.out.println("❌ Error: " + e.getMessage());
            throw e;
        } finally {
            conexion.setAutoCommit(true);
        }
    }
}
