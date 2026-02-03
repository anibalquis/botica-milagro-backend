package zegel.farm_milagro.servicio.categoria;

import zegel.farm_milagro.modelo.Categoria;

import java.util.List;

public interface ICategoriaServicio {
    public List<Categoria> obtenerCategorias();
    public Categoria obtenerCategoriaPorId(Integer id);
    public Categoria crearCategoria(Categoria categoria);
    public Categoria actualizarCategoria(Integer id, Categoria categoria);
    public void eliminarCategoriaPorId(Integer id);
}
