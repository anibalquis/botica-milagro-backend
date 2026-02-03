package zegel.farm_milagro.servicio.medicamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zegel.farm_milagro.modelo.Medicamento;
import zegel.farm_milagro.repositorio.MedicamentoRepositorio;

import java.util.List;

@Service
public class MedicamentoServicio implements IMedicamentoServicio {
    @Autowired
    private MedicamentoRepositorio medicamentoRepositorio;

    // ================================================================
    // Obtiene todos los medicamentos de la base de datos
    // ================================================================
    @Override
    public List<Medicamento> obtenerMedicamentos() {
        return this.medicamentoRepositorio.findAll();
    }

    // ================================================================
    // Busca una medicamento por su ID
    // ================================================================
    @Override
    public Medicamento obtenerMedicamentoPorId(Integer id) {
        return this.medicamentoRepositorio.findById(id).orElse(null);
    }

    // ================================================================
    // Crea y guarda un nuevo medicamento
    // ================================================================
    @Override
    public Medicamento crearMedicamento(Medicamento medicamento) {
        return this.medicamentoRepositorio.save(medicamento);
    }

    // ================================================================
    // Actualiza un medicamento existente por su ID
    // ================================================================
    @Override
    public Medicamento actualizarMedicamento(Integer id, Medicamento medicamento) {
        Medicamento medicamentoExistente = obtenerMedicamentoPorId(id);

        if (medicamentoExistente == null) {
            return null; // No se encontró el medicamento
        }

        // Actualizamos los campos del medicamento
        medicamentoExistente.setNombreComercial(medicamento.getNombreComercial());
        medicamentoExistente.setNombreGenerico(medicamento.getNombreGenerico());
        medicamentoExistente.setLaboratorio(medicamento.getLaboratorio());
        medicamentoExistente.setPrecioUnitario(medicamento.getPrecioUnitario());
        medicamentoExistente.setStockActual(medicamento.getStockActual());
        medicamentoExistente.setUrlImg(medicamento.getUrlImg());
        medicamentoExistente.setModoUso(medicamento.getModoUso());
        medicamentoExistente.setCategoria(medicamento.getCategoria());

        return this.medicamentoRepositorio.save(medicamentoExistente);
    }

    // ================================================================
    // Elimina un medicamento por su ID
    // ================================================================
    @Override
    public void eliminarMedicamentoPorId(Integer id) { this.medicamentoRepositorio.deleteById(id); }
}
