package tp9jdbc.service;

import tp9jdbc.dao.ProductoDAOImpl;
import tp9jdbc.model.Producto;

import java.util.List;

public class ProductoServiceImpl {

    private ProductoDAOImpl dao = new ProductoDAOImpl();

    public void crear(Producto p) {
        if (p.getNombre() == null || p.getNombre().isBlank()) {
            System.out.println("⚠ Nombre obligatorio.");
            return;
        }

        if (!dao.existeCategoria(p.getCategoria().getId())) {
            System.out.println("⚠ La categoría asignada al producto no existe.");
            return;
        }

        if (p.getPrecio() <= 0) {
            System.out.println("⚠ Precio debe ser mayor a cero.");
            return;
        }

        if (p.getCantidad() <= 0) {
            System.out.println("⚠ Cantidad debe ser mayor a cero.");
            return;
        }

        if (!dao.existeCategoria(p.getCategoria().getId())) {
            System.out.println("⚠ Categoría inexistente.");
            return;
        }

        dao.crear(p);
        System.out.println("✅ Producto creado.");
    }

    public List<Producto> listar() {
        return dao.listar();
    }

    public List<Producto> listarPorCategoria(int idCategoria) {
        return dao.listarPorCategoria(idCategoria);
    }
}
