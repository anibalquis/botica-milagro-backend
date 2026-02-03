package zegel.farm_milagro.servicio.empleado;

import zegel.farm_milagro.modelo.Empleado;
import java.util.List;

public interface IEmpleadoServicio {
    public List<Empleado> obtenerEmpleados();
    public Empleado obtenerEmpleadoPorId(Integer id);
    public Empleado crearEmpleado(Empleado empleado);
    public Empleado actualizarEmpleado(Integer id, Empleado empleado);
    public void eliminarEmpleadoPorId(Integer id);
}
