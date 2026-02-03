package zegel.farm_milagro.servicio.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zegel.farm_milagro.modelo.Cliente;
import zegel.farm_milagro.repositorio.ClienteRepositorio;
import zegel.farm_milagro.servicio.cliente.IClienteServicio;

import java.util.List;

@Service
public class ClienteServicio implements IClienteServicio {
    @Autowired
    private ClienteRepositorio clienteRepositorio;

    // ================================================================
    // Obtiene todos los clientes de la base de datos
    // ================================================================
    @Override
    public List<Cliente> obtenerClientes() {
        return this.clienteRepositorio.findAll();
    }

    // ================================================================
    // Busca un cliente por su ID
    // ================================================================
    @Override
    public Cliente obtenerClientePorId(Integer id) {
        return this.clienteRepositorio.findById(id).orElse(null);
    }

    // ================================================================
    // Crea y guarda un nuevo cliente
    // ================================================================
    @Override
    public Cliente crearCliente(Cliente cliente) {
        return this.clienteRepositorio.save(cliente);
    }

    // ================================================================
    // Actualiza un cliente existente por su ID
    // ================================================================
    @Override
    public Cliente actualizarCliente(Integer id, Cliente cliente) {
        Cliente clienteExistente = obtenerClientePorId(id);

        if (clienteExistente == null) {
            return null; // No se encontró el medicamento
        }

        // Validar que no intenten cambiar el DNI
        if (cliente.getDni() != null &&
                !cliente.getDni().equals(clienteExistente.getDni())) {
            throw new IllegalArgumentException("No se permite cambiar el DNI del cliente.");
        }

        // Actualizamos los campos del medicamento
        clienteExistente.setNombres(cliente.getNombres());
        clienteExistente.setApellidos(cliente.getApellidos());
        clienteExistente.setTelefono(cliente.getTelefono());
        clienteExistente.setCorreo(cliente.getCorreo());

        return this.clienteRepositorio.save(clienteExistente);
    }

    // ================================================================
    // Elimina un cliente por su ID
    // ================================================================
    @Override
    public void eliminarClientePorId(Integer id) { this.clienteRepositorio.deleteById(id); }
}
