package zegel.farm_milagro.controlador;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zegel.farm_milagro.modelo.Categoria;
import zegel.farm_milagro.servicio.categoria.CategoriaServicio;
import java.util.List;
import static org.slf4j.LoggerFactory.getLogger;

@RestController
// http://localhost:8080/api/v1/categorias
@RequestMapping("api/v1/categorias")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://botica-milagros.vercel.app"
})

public class CategoriaControlador {
    private static final Logger logger = getLogger(CategoriaControlador.class);
    @Autowired
    private CategoriaServicio categoriaServicio;

    // ================================================================
    // GET /api/v1/categorias
    // Lista todas las categorías
    // ================================================================
    @GetMapping
    public ResponseEntity<List<Categoria>> obtenerCategorias() {
        List<Categoria> categorias = categoriaServicio.obtenerCategorias();
        return ResponseEntity.ok(categorias);
    }

    // ================================================================
    // GET /api/v1/categorias/{id}
    // Obtiene una categoría por su ID
    // ================================================================
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtenerCategoria(@PathVariable Integer id) {
        Categoria categoria = categoriaServicio.obtenerCategoriaPorId(id);
        if (categoria != null) {
            return ResponseEntity.ok(categoria);
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    // ================================================================
    // POST /api/v1/categorias
    // Crea una nueva categoría
    // ================================================================
    @PostMapping
    public ResponseEntity<Categoria> crear(@RequestBody Categoria categoria) {
        Categoria nuevaCategoria = categoriaServicio.crearCategoria(categoria);
        return new ResponseEntity<>(nuevaCategoria, HttpStatus.CREATED); // 201 Created
    }

    // ================================================================
    // PUT /api/v1/categorias/{id}
    // Actualiza una categoría existente.
    // ================================================================
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Categoria categoria) {
        Categoria actualizada = categoriaServicio.actualizarCategoria(id, categoria);

        if (actualizada == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("La categoría con ID " + id + " no existe.");
        }

        return ResponseEntity.ok(actualizada);
    }

    // ================================================================
    // DELETE /api/v1/categorias/{id}
    // Elimina una categoría por su ID
    // ================================================================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        Categoria categoria = categoriaServicio.obtenerCategoriaPorId(id);

        if (categoria == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("La categoría con ID " + id + " no existe.");
        }

        categoriaServicio.eliminarCategoriaPorId(id);
        return ResponseEntity.ok("La categoría con ID " + id + " ha sido eliminada exitosamente.");
    }
}
