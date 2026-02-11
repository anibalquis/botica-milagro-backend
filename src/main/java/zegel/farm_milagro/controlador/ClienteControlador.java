package zegel.farm_milagro.controlador;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zegel.farm_milagro.modelo.Cliente;
import zegel.farm_milagro.servicio.cliente.ClienteServicio;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("api/v1/clientes")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://botica-milagros.vercel.app"
})

public class ClienteControlador {
    private static final Logger logger = getLogger(ClienteControlador.class);
    @Autowired
    private ClienteServicio clienteServicio;

    // ================================================================
    // GET /api/v1/clientes
    // Lista todos los clientes
    // ================================================================
    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerClientes() {
        List<Cliente> clientes = clienteServicio.obtenerClientes();
        return ResponseEntity.ok(clientes);
    }

    // ================================================================
    // GET /api/v1/clientes/{id}
    // Obtiene un cliente por su ID
    // ================================================================
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerCliente(@PathVariable Integer id) {
        Cliente cliente = clienteServicio.obtenerClientePorId(id);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    // ================================================================
    // POST /api/v1/clientes
    // Crea un nuevo cliente
    // ================================================================
    @PostMapping
    public ResponseEntity<Cliente> crear(@RequestBody Cliente cliente) {
        Cliente nuevoCliente = clienteServicio.crearCliente(cliente);
        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }

    // ================================================================
    // PUT /api/v1/clientes/{id}
    // Actualiza un cliente existente
    // ================================================================
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Cliente cliente) {
        try {
            Cliente actualizado = clienteServicio.actualizarCliente(id, cliente);

            if (actualizado == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("El cliente con ID " + id + " no existe.");
            }

            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    // ================================================================
    // DELETE /api/v1/clientes/{id}
    // Elimina un cliente por su ID
    // ================================================================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        Cliente cliente = clienteServicio.obtenerClientePorId(id);

        if (cliente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El cliente con ID " + id + " no existe.");
        }

        clienteServicio.eliminarClientePorId(id);
        return ResponseEntity.ok("El cliente con ID " + id + " ha sido eliminado exitosamente.");
    }

}
