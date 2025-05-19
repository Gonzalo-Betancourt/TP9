package tp9jdbc.service;

import tp9jdbc.dao.PedidoDAOImpl;
import tp9jdbc.model.ItemPedido;
import tp9jdbc.model.Pedido;

public class PedidoServiceImpl {

    private PedidoDAOImpl dao = new PedidoDAOImpl();

    public void registrarPedido(Pedido pedido) {
        if (pedido.getItems().isEmpty()) {
            System.out.println("⚠ El pedido no puede estar vacío.");
            return;
        }

        try {
            pedido.recalcularTotal();
            dao.crearPedido(pedido);
        } catch (Exception e) {
            System.out.println("❌ No se pudo registrar el pedido.");
        }
    }
}
