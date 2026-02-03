package zegel.farm_milagro.servicio.carritoCompras;

import zegel.farm_milagro.modelo.CarritoCompras;
import java.util.List;

public interface ICarritoComprasServicio {
    // Todos los métodos son públicos por defecto
    List<CarritoCompras> obtenerCarritos();
    CarritoCompras obtenerCarritoPorId(Integer id);
    CarritoCompras crearCarrito(CarritoCompras carrito);
    CarritoCompras actualizarCarrito(Integer id, CarritoCompras carrito);
    void eliminarCarritoPorId(Integer id);
}
