package zegel.farm_milagro.servicio.caractMedicamento;

import zegel.farm_milagro.modelo.CaracteristicaMedicamento;
import java.util.List;

public interface ICaractMedicamentoServicio {
    public List<CaracteristicaMedicamento> obtenerCaracteristicas();
    public CaracteristicaMedicamento obtenerCaracteristicaPorId(Integer id);
    public CaracteristicaMedicamento crearCaracteristica(CaracteristicaMedicamento caracteristica);
    public CaracteristicaMedicamento actualizarCaracteristica(Integer id, CaracteristicaMedicamento caracteristica);
    public void eliminarCaracteristicaPorId(Integer idCaracteristica);
}
