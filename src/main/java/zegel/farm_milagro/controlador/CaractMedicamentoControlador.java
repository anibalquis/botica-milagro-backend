package zegel.farm_milagro.controlador;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zegel.farm_milagro.modelo.CaracteristicaMedicamento;
import zegel.farm_milagro.servicio.caractMedicamento.CaractMedicamentoServicio;
import java.util.List;
import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("api/v1/caracteristicas")
@CrossOrigin(value = "http://localhost:4200")

public class CaractMedicamentoControlador {
    private static final Logger logger = getLogger(CaractMedicamentoControlador.class);
    @Autowired
    private CaractMedicamentoServicio caractMedicamentoServicio;

    // ================================================================
    // GET /api/v1/caracteristicas
    // Lista todas las características
    // ================================================================
    @GetMapping
    public ResponseEntity<List<CaracteristicaMedicamento>> obtenerCaracteristicas() {
        List<CaracteristicaMedicamento> caracteristicas = caractMedicamentoServicio.obtenerCaracteristicas();
        return ResponseEntity.ok(caracteristicas);
    }

    // ================================================================
    // GET /api/v1/caracteristicas/{id}
    // Obtiene una característica por su ID
    // ================================================================
    @GetMapping("/{id}")
    public ResponseEntity<CaracteristicaMedicamento> obtenerCaracteristica(@PathVariable Integer id) {
        CaracteristicaMedicamento caracteristica = caractMedicamentoServicio.obtenerCaracteristicaPorId(id);
        if (caracteristica != null) {
            return ResponseEntity.ok(caracteristica);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ================================================================
    // POST /api/v1/caracteristicas
    // Crea una nueva característica
    // ================================================================
    @PostMapping
    public ResponseEntity<CaracteristicaMedicamento> crear(@RequestBody CaracteristicaMedicamento caracteristica) {
        CaracteristicaMedicamento nueva = caractMedicamentoServicio.crearCaracteristica(caracteristica);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }

    // ================================================================
    // PUT /api/v1/caracteristicas/{id}
    // Actualiza una característica existente
    // ================================================================
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody CaracteristicaMedicamento caracteristica) {
        CaracteristicaMedicamento actualizada = caractMedicamentoServicio.actualizarCaracteristica(id, caracteristica);

        if (actualizada == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("La característica con ID " + id + " no existe.");
        }

        return ResponseEntity.ok(actualizada);
    }

    // ================================================================
    // DELETE /api/v1/caracteristicas/{id}
    // Elimina una característica por su ID
    // ================================================================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        CaracteristicaMedicamento caracteristica = caractMedicamentoServicio.obtenerCaracteristicaPorId(id);

        if (caracteristica == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("La característica con ID " + id + " no existe.");
        }

        caractMedicamentoServicio.eliminarCaracteristicaPorId(id);
        return ResponseEntity.ok("La característica con ID " + id + " ha sido eliminada exitosamente.");
    }
}
