package tp9jdbc;

import tp9jdbc.model.Categoria;
import tp9jdbc.model.Producto;
import tp9jdbc.service.CategoriaServiceImpl;
import tp9jdbc.service.ProductoServiceImpl;

public class Main {
    public static void main(String[] args) {
        CategoriaServiceImpl categoriaService = new CategoriaServiceImpl();
        ProductoServiceImpl productoService = new ProductoServiceImpl();

        // Usar categoría existente o crear una
        Categoria cat = categoriaService.leer(1);
        if (cat == null) {
            categoriaService.crear(new Categoria("Tecnología", "Todo lo digital"));
            cat = categoriaService.leer(1);
        }

        // Crear producto
        Producto p = new Producto("Notebook Lenovo", "Gama media", 800.0, 10, cat);
        productoService.crear(p);

        // Listar productos
        productoService.listar().forEach(prod -> {
            System.out.println(prod.getNombre() + " - " + prod.getCategoria().getNombre());
        });
    }
}
