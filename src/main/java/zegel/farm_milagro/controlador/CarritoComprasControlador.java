package zegel.farm_milagro.controlador;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zegel.farm_milagro.modelo.CarritoCompras;
import zegel.farm_milagro.servicio.carritoCompras.CarritoComprasServicio;
import java.util.List;
import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("api/v1/carritos")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://botica-milagros.vercel.app"
})

public class CarritoComprasControlador {
    private static final Logger logger = getLogger(CarritoComprasControlador.class);

    @Autowired
    private CarritoComprasServicio carritoServicio;

    // ================================================================
    // GET /api/v1/carritos
    // Lista todos los carritos
    // ================================================================
    @GetMapping
    public ResponseEntity<List<CarritoCompras>> obtenerCarritos() {
        return ResponseEntity.ok(carritoServicio.obtenerCarritos());
    }

    // ================================================================
    // GET /api/v1/carritos/{id}
    // Obtiene un carrito por su ID
    // ================================================================
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCarrito(@PathVariable Integer id) {
        CarritoCompras carrito = carritoServicio.obtenerCarritoPorId(id);
        if (carrito != null) {
            return ResponseEntity.ok(carrito);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Carrito con ID " + id + " no encontrado.");
    }

    // ================================================================
    // POST /api/v1/carritos
    // Crea un nuevo carrito
    // ================================================================
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody CarritoCompras carrito) {
        try {
            return new ResponseEntity<>(carritoServicio.crearCarrito(carrito), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // ================================================================
    // PUT /api/v1/carritos/{id}
    // Actualiza un carrito existente
    // ================================================================
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody CarritoCompras carrito) {
        try {
            CarritoCompras actualizado = carritoServicio.actualizarCarrito(id, carrito);
            if (actualizado == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Carrito con ID " + id + " no encontrado.");
            }
            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // ================================================================
    // DELETE /api/v1/carritos/{id}
    // Elimina un carrito por su ID
    // ================================================================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        CarritoCompras carrito = carritoServicio.obtenerCarritoPorId(id);
        if (carrito == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Carrito con ID " + id + " no encontrado.");
        }
        carritoServicio.eliminarCarritoPorId(id);
        return ResponseEntity.ok("Carrito con ID " + id + " eliminado correctamente.");
    }
}
