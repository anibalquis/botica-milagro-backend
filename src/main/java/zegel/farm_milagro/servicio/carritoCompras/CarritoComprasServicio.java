package zegel.farm_milagro.servicio.carritoCompras;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zegel.farm_milagro.modelo.CarritoCompras;
import zegel.farm_milagro.modelo.Cliente;
import zegel.farm_milagro.modelo.Medicamento;
import zegel.farm_milagro.repositorio.CarritoComprasRepositorio;
import zegel.farm_milagro.repositorio.ClienteRepositorio;
import zegel.farm_milagro.repositorio.MedicamentoRepositorio;
import zegel.farm_milagro.servicio.carritoCompras.ICarritoComprasServicio;

import java.util.List;

@Service
public class CarritoComprasServicio implements ICarritoComprasServicio {
    @Autowired
    private CarritoComprasRepositorio carritoRepositorio;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private MedicamentoRepositorio medicamentoRepositorio;

    // ================================================================
    // Obtiene todos los carritos de la base de datos
    // ================================================================
    @Override
    public List<CarritoCompras> obtenerCarritos() {
        return carritoRepositorio.findAll();
    }

    // ================================================================
    // Busca un carrito por su ID
    // ================================================================
    @Override
    public CarritoCompras obtenerCarritoPorId(Integer id) {
        return carritoRepositorio.findById(id).orElse(null);
    }

    // ================================================================
    // Crea y guarda un nuevo carrito
    // ================================================================
    @Override
    public CarritoCompras crearCarrito(CarritoCompras carrito) {
        // Validar cliente
        Cliente cliente = clienteRepositorio.findById(carrito.getCliente().getId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

        // Validar medicamento
        Medicamento medicamento = medicamentoRepositorio.findById(carrito.getMedicamento().getIdMedicamento())
                .orElseThrow(() -> new IllegalArgumentException("Medicamento no encontrado"));

        carrito.setCliente(cliente);
        carrito.setMedicamento(medicamento);
        // Asignar precio del medicamento
        carrito.setPrecioUnitario(medicamento.getPrecioUnitario());
        carrito.setSubtotal(carrito.getCantidad() * carrito.getPrecioUnitario());

        return carritoRepositorio.save(carrito);
    }

    // ================================================================
    // Actualiza un carrito existente por su ID
    // ================================================================
    @Override
    public CarritoCompras actualizarCarrito(Integer id, CarritoCompras carrito) {
        CarritoCompras carritoExistente = obtenerCarritoPorId(id);

        if (carritoExistente == null) {
            return null;
        }

        // No se permite cambiar cliente
        if (carrito.getCliente() != null &&
                !carrito.getCliente().getId().equals(carritoExistente.getCliente().getId())) {
            throw new IllegalArgumentException("No se permite cambiar el cliente del carrito.");
        }

        // Validar medicamento
        Medicamento medicamento = medicamentoRepositorio.findById(carrito.getMedicamento().getIdMedicamento())
                .orElseThrow(() -> new IllegalArgumentException("Medicamento no encontrado"));

        // Actualizar datos
        carritoExistente.setMedicamento(medicamento);
        carritoExistente.setCantidad(carrito.getCantidad());
        carritoExistente.setPrecioUnitario(medicamento.getPrecioUnitario());
        // Recalcular subtotal
        carritoExistente.setSubtotal(carritoExistente.getCantidad() * carritoExistente.getPrecioUnitario());
        carritoExistente.setEstado(carrito.getEstado());

        return carritoRepositorio.save(carritoExistente);
    }

    // ================================================================
    // Elimina un carrito por su ID
    // ================================================================
    @Override
    public void eliminarCarritoPorId(Integer id) {
        carritoRepositorio.deleteById(id);
    }
}
