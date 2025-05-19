package tp9jdbc.main;

import tp9jdbc.model.*;
import tp9jdbc.service.*;

public class Main {
    public static void main(String[] args) {
        CategoriaServiceImpl categoriaService = new CategoriaServiceImpl();
        ProductoServiceImpl productoService = new ProductoServiceImpl();
        PedidoServiceImpl pedidoService = new PedidoServiceImpl();

        // Crear categoría si no existe
        Categoria cat = categoriaService.leer(3);
        if (cat == null) {
            categoriaService.crear(new Categoria("Ropa", "Articulos de vestimenta"));
            cat = categoriaService.leer(3);
        }

        // Crear productos si no existen
        Producto p1 = new Producto("Remera", "Deportiva", 30.0, 50, cat);
        Producto p2 = new Producto("Gorro", "De lana", 10.0, 30, cat);
        productoService.crear(p1);
        productoService.crear(p2);

        // Armar pedido
        Pedido pedido = new Pedido();
        pedido.agregarItem(new ItemPedido(productoService.listar().get(0), 2));
        pedido.agregarItem(new ItemPedido(productoService.listar().get(1), 1));

        // Registrar pedido
        pedidoService.registrarPedido(pedido);
    }
}
