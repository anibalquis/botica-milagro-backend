package zegel.farm_milagro.controlador;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zegel.farm_milagro.modelo.Reporte;
import zegel.farm_milagro.servicio.reporte.ReporteServicio;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("api/v1/reportes")
@CrossOrigin(value = "http://localhost:4200")

public class ReporteControlador {
    private static final Logger logger = getLogger(ReporteControlador.class);

    @Autowired
    private ReporteServicio reporteServicio;

    // ================================================================
    // GET /api/v1/reportes
    // Lista todos los reportes
    // ================================================================
    @GetMapping
    public ResponseEntity<List<Reporte>> obtenerReportes() {
        List<Reporte> reportes = reporteServicio.obtenerReportes();
        return ResponseEntity.ok(reportes);
    }

    // ================================================================
    // GET /api/v1/reportes/{id}
    // Obtiene un reporte por su ID
    // ================================================================
    @GetMapping("/{id}")
    public ResponseEntity<Reporte> obtenerReporte(@PathVariable Integer id) {
        Reporte reporte = reporteServicio.obtenerReportePorId(id);
        if (reporte != null) {
            return ResponseEntity.ok(reporte);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ================================================================
    // POST /api/v1/reportes
    // Crea un nuevo reporte
    // ================================================================
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Reporte reporte) {
        try {
            Reporte nuevoReporte = reporteServicio.crearReporte(reporte);
            return new ResponseEntity<>(nuevoReporte, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // ================================================================
    // PUT /api/v1/reportes/{id}
    // Actualiza un reporte existente
    // ================================================================
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Reporte reporte) {
        try {
            Reporte actualizado = reporteServicio.actualizarReporte(id, reporte);
            if (actualizado == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("El reporte con ID " + id + " no existe.");
            }
            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // ================================================================
    // DELETE /api/v1/reportes/{id}
    // Elimina un reporte por su ID
    // ================================================================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        Reporte reporte = reporteServicio.obtenerReportePorId(id);
        if (reporte == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El reporte con ID " + id + " no existe.");
        }
        reporteServicio.eliminarReportePorId(id);
        return ResponseEntity.ok("El reporte con ID " + id + " ha sido eliminado exitosamente.");
    }
}
