package zegel.farm_milagro.servicio.cliente;

import zegel.farm_milagro.modelo.Cliente;

import java.util.List;

public interface IClienteServicio {
    public List<Cliente> obtenerClientes();
    public Cliente obtenerClientePorId(Integer id);
    public Cliente crearCliente(Cliente cliente);
    public Cliente actualizarCliente(Integer id, Cliente cliente);
    public void eliminarClientePorId(Integer id);
}
