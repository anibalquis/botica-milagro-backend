package zegel.farm_milagro.servicio.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zegel.farm_milagro.modelo.Categoria;
import zegel.farm_milagro.repositorio.CategoriaRepositorio;
import java.util.List;

@Service
public class CategoriaServicio implements ICategoriaServicio {
    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    // ================================================================
    // Obtiene todas las categorías de la base de datos
    // ================================================================
    @Override
    public List<Categoria> obtenerCategorias() {
        List<Categoria> categorias = this.categoriaRepositorio.findAll();
        return categorias;
    }

    // ================================================================
    // Busca una categoría por su ID
    // ================================================================
    @Override
    public Categoria obtenerCategoriaPorId(Integer idCategoria) {
        Categoria categoria = this.categoriaRepositorio.findById(idCategoria).orElse(null);
        return  categoria;
    }

    // ================================================================
    // Crea y guarda una nueva categoría
    // ================================================================
    @Override
    public Categoria crearCategoria(Categoria categoria) {
        return this.categoriaRepositorio.save(categoria);
    }

    // ================================================================
    // Actualiza una categoría existente por su ID
    // ================================================================
    @Override
    public Categoria actualizarCategoria(Integer id, Categoria categoria) {
        Categoria categoriaExistente = obtenerCategoriaPorId(id);

        if (categoriaExistente == null) {
            return null;
        }

        categoriaExistente.setNombreCategoria(categoria.getNombreCategoria());
        return this.categoriaRepositorio.save(categoriaExistente);
    }

    // ================================================================
    // Elimina una categoría por su ID
    // ================================================================
    @Override
    public void eliminarCategoriaPorId(Integer id) { this.categoriaRepositorio.deleteById(id); }
}
