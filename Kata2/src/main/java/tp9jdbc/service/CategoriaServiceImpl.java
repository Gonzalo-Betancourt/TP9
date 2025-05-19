package tp9jdbc.service;

import tp9jdbc.dao.CategoriaDAOImpl;
import tp9jdbc.model.Categoria;

import java.util.List;

public class CategoriaServiceImpl implements GenericService<Categoria> {

    private CategoriaDAOImpl dao = new CategoriaDAOImpl();

    @Override
    public void crear(Categoria c) {
        if (c.getNombre() == null || c.getNombre().isBlank()) {
            System.out.println("⚠ El nombre no puede estar vacío.");
            return;
        }

        if (dao.existeNombre(c.getNombre())) {
            System.out.println("⚠ Ya existe una categoría con ese nombre.");
            return;
        }

        dao.crear(c);
        System.out.println("✅ Categoría creada correctamente.");
    }

    @Override
    public Categoria leer(int id) {
        return dao.leer(id);
    }

    @Override
    public void actualizar(Categoria c) {
        dao.actualizar(c);
    }

    @Override
    public void eliminar(int id) {
        dao.eliminar(id);
    }

    @Override
    public List<Categoria> listar() {
        return dao.listar();
    }
}
