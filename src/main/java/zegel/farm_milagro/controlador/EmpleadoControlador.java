package zegel.farm_milagro.controlador;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zegel.farm_milagro.modelo.Empleado;
import zegel.farm_milagro.servicio.empleado.EmpleadoServicio;
import java.util.List;
import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("api/v1/empleados")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://botica-milagros.vercel.app"
})

public class EmpleadoControlador {
    private static final Logger logger = getLogger(EmpleadoControlador.class);

    @Autowired
    private EmpleadoServicio empleadoServicio;

    // ================================================================
    // GET /api/v1/empleados
    // Lista todos los empleados
    // ================================================================
    @GetMapping
    public ResponseEntity<List<Empleado>> obtenerEmpleados() {
        List<Empleado> empleados = empleadoServicio.obtenerEmpleados();
        return ResponseEntity.ok(empleados);
    }

    // ================================================================
    // GET /api/v1/empleados/{id}
    // Obtiene un empleado por su ID
    // ================================================================
    @GetMapping("/{id}")
    public ResponseEntity<Empleado> obtenerEmpleado(@PathVariable Integer id) {
        Empleado empleado = empleadoServicio.obtenerEmpleadoPorId(id);
        if (empleado != null) {
            return ResponseEntity.ok(empleado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ================================================================
    // POST /api/v1/empleados
    // Crea un nuevo empleado
    // ================================================================
    @PostMapping
    public ResponseEntity<Empleado> crear(@RequestBody Empleado empleado) {
        Empleado nuevoEmpleado = empleadoServicio.crearEmpleado(empleado);
        return new ResponseEntity<>(nuevoEmpleado, HttpStatus.CREATED);
    }

    // ================================================================
    // PUT /api/v1/empleados/{id}
    // Actualiza un empleado existente
    // ================================================================
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Empleado empleado) {
        try {
            Empleado actualizado = empleadoServicio.actualizarEmpleado(id, empleado);

            if (actualizado == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("El empleado con ID " + id + " no existe.");
            }

            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    // ================================================================
    // DELETE /api/v1/empleados/{id}
    // Elimina un empleado por su ID
    // ================================================================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        Empleado empleado = empleadoServicio.obtenerEmpleadoPorId(id);

        if (empleado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El empleado con ID " + id + " no existe.");
        }

        empleadoServicio.eliminarEmpleadoPorId(id);
        return ResponseEntity.ok("El empleado con ID " + id + " ha sido eliminado exitosamente.");
    }
}
