package tp9jdbc;

import tp9jdbc.model.Categoria;
import tp9jdbc.service.CategoriaServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CategoriaServiceImpl service = new CategoriaServiceImpl();

        // Crear una categoría nueva
        service.crear(new Categoria("Electrodomésticos", "Aparatos para el hogar"));

        // Listar todas las categorías
        List<Categoria> categorias = service.listar();
        System.out.println("📋 Categorías:");
        for (Categoria c : categorias) {
            System.out.println("- " + c.getId() + ": " + c.getNombre() + " - " + c.getDescripcion());
        }

        // Leer una categoría
        Categoria encontrada = service.leer(1);
        if (encontrada != null) {
            System.out.println("🔍 Encontrada: " + encontrada.getNombre());
        }

        // Actualizar
        if (encontrada != null) {
            encontrada.setDescripcion("Actualizada");
            service.actualizar(encontrada);
        }

        // Eliminar (opcional)
        // service.eliminar(3);
    }
}
