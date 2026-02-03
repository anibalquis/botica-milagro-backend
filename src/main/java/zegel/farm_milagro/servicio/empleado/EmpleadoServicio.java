package zegel.farm_milagro.servicio.empleado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zegel.farm_milagro.modelo.Empleado;
import zegel.farm_milagro.repositorio.EmpleadoRepositorio;
import zegel.farm_milagro.servicio.empleado.IEmpleadoServicio;

import java.util.List;

@Service
public class EmpleadoServicio implements IEmpleadoServicio {
    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;

    // ================================================================
    // Obtiene todos los empleados de la base de datos
    // ================================================================
    @Override
    public List<Empleado> obtenerEmpleados() {
        return this.empleadoRepositorio.findAll();
    }

    // ================================================================
    // Busca un empleado por su ID
    // ================================================================
    @Override
    public Empleado obtenerEmpleadoPorId(Integer id) {
        return this.empleadoRepositorio.findById(id).orElse(null);
    }

    // ================================================================
    // Crea y guarda un nuevo empleado
    // ================================================================
    @Override
    public Empleado crearEmpleado(Empleado empleado) {
        return this.empleadoRepositorio.save(empleado);
    }

    // ================================================================
    // Actualiza un empleado existente por su ID
    // ================================================================
    @Override
    public Empleado actualizarEmpleado(Integer id, Empleado empleado) {
        Empleado empleadoExistente = obtenerEmpleadoPorId(id);

        if (empleadoExistente == null) {
            return null;
        }

        // Validar que no cambie el DNI
        if (empleado.getDni() != null &&
                !empleado.getDni().equals(empleadoExistente.getDni())) {
            throw new IllegalArgumentException("No se permite cambiar el DNI del empleado.");
        }

        empleadoExistente.setNombres(empleado.getNombres());
        empleadoExistente.setApellidos(empleado.getApellidos());
        empleadoExistente.setTelefono(empleado.getTelefono());
        empleadoExistente.setCorreo(empleado.getCorreo());

        return this.empleadoRepositorio.save(empleadoExistente);
    }

    // ================================================================
    // Elimina un empleado por su ID
    // ================================================================
    @Override
    public void eliminarEmpleadoPorId(Integer id) {
        this.empleadoRepositorio.deleteById(id);
    }
}
