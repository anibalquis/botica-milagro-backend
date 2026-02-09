package zegel.farm_milagro.controlador;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zegel.farm_milagro.modelo.Medicamento;
import zegel.farm_milagro.servicio.medicamento.MedicamentoServicio;
import java.util.List;
import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("api/v1/medicamentos")
@CrossOrigin(value = "http://localhost:5173")

public class MedicamentoControlador {
    private static final Logger logger = getLogger(MedicamentoControlador.class);
    @Autowired
    private MedicamentoServicio medicamentoServicio;

    // ================================================================
    // GET /api/v1/medicamentos
    // Lista todos los medicamentos
    // ================================================================
    @GetMapping
    public ResponseEntity<List<Medicamento>> obtenerMedicamentos() {
        List<Medicamento> medicamentos = medicamentoServicio.obtenerMedicamentos();
        return ResponseEntity.ok(medicamentos);
    }

    // ================================================================
    // GET /api/v1/medicamentos/{id}
    // Obtiene un medicamento por su ID
    // ================================================================
    @GetMapping("/{id}")
    public ResponseEntity<Medicamento> obtenerMedicamento(@PathVariable Integer id) {
        Medicamento medicamento = medicamentoServicio.obtenerMedicamentoPorId(id);
        if (medicamento != null) {
            return ResponseEntity.ok(medicamento);
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    // ================================================================
    // POST /api/v1/medicamentos
    // Crea un nuevo medicamento
    // ================================================================
    @PostMapping
    public ResponseEntity<Medicamento> crear(@RequestBody Medicamento medicamento) {
        Medicamento nuevoMedicamento = medicamentoServicio.crearMedicamento(medicamento);
        return new ResponseEntity<>(nuevoMedicamento, HttpStatus.CREATED);
    }

    // ================================================================
    // PUT /api/v1/medicamentos/{id}
    // Actualiza un medicamento existente
    // ================================================================
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Medicamento medicamento) {
        Medicamento actualizado = medicamentoServicio.actualizarMedicamento(id, medicamento);

        if (actualizado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El medicamento con ID " + id + " no existe.");
        }

        return ResponseEntity.ok(actualizado);
    }

    // ================================================================
    // DELETE /api/v1/medicamentos/{id}
    // Elimina un medicamento por su ID
    // ================================================================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        Medicamento medicamento = medicamentoServicio.obtenerMedicamentoPorId(id);

        if (medicamento == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El medicamento con ID " + id + " no existe.");
        }

        medicamentoServicio.eliminarMedicamentoPorId(id);
        return ResponseEntity.ok("El medicamento con ID " + id + " ha sido eliminado exitosamente.");
    }

}
