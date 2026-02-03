package zegel.farm_milagro.servicio.caractMedicamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zegel.farm_milagro.modelo.CaracteristicaMedicamento;
import zegel.farm_milagro.repositorio.CaractMedicamentoRepositorio;
import zegel.farm_milagro.servicio.medicamento.MedicamentoServicio;
import java.util.List;

@Service
public class CaractMedicamentoServicio implements ICaractMedicamentoServicio {
    @Autowired
    private CaractMedicamentoRepositorio caractMedicamentoRepositorio;

    @Autowired
    private MedicamentoServicio medicamentoServicio;

    // ================================================================
    // Obtiene todas las características de la base de datos
    // ================================================================
    @Override
    public List<CaracteristicaMedicamento> obtenerCaracteristicas() {
        return this.caractMedicamentoRepositorio.findAll();
    }

    // ================================================================
    // Busca una característica por su ID
    // ================================================================
    @Override
    public CaracteristicaMedicamento obtenerCaracteristicaPorId(Integer id) {
        return this.caractMedicamentoRepositorio.findById(id).orElse(null);
    }

    // ================================================================
    // Crea y guarda una característica
    // ================================================================
    @Override
    public CaracteristicaMedicamento crearCaracteristica(CaracteristicaMedicamento caracteristica) {
        return this.caractMedicamentoRepositorio.save(caracteristica);
    }

    // ================================================================
    // Actualiza una característica existente por su ID
    // ================================================================
    @Override
    public CaracteristicaMedicamento actualizarCaracteristica(Integer id, CaracteristicaMedicamento caracteristica) {
        CaracteristicaMedicamento existente = obtenerCaracteristicaPorId(id);

        if (existente == null) {
            return null;
        }

        existente.setDescripcion(caracteristica.getDescripcion());
        existente.setMedicamento(caracteristica.getMedicamento());

        return this.caractMedicamentoRepositorio.save(existente);
    }

    // ================================================================
    // Elimina una característica por su ID
    // ================================================================
    @Override
    public void eliminarCaracteristicaPorId(Integer id) { this.caractMedicamentoRepositorio.deleteById(id); }
}
