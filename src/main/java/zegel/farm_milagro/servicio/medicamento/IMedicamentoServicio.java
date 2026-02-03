package zegel.farm_milagro.servicio.medicamento;

import zegel.farm_milagro.modelo.Medicamento;
import java.util.List;

public interface IMedicamentoServicio {
    public List<Medicamento> obtenerMedicamentos();
    public Medicamento obtenerMedicamentoPorId(Integer idMedicamento);
    public Medicamento crearMedicamento(Medicamento medicamento);
    public Medicamento actualizarMedicamento(Integer id, Medicamento medicamento);
    public void eliminarMedicamentoPorId(Integer id);
}
