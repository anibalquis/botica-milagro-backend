package zegel.farm_milagro.servicio.reporte;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zegel.farm_milagro.modelo.Empleado;
import zegel.farm_milagro.modelo.Medicamento;
import zegel.farm_milagro.modelo.Reporte;
import zegel.farm_milagro.repositorio.EmpleadoRepositorio;
import zegel.farm_milagro.repositorio.MedicamentoRepositorio;
import zegel.farm_milagro.repositorio.ReporteRepositorio;
import zegel.farm_milagro.servicio.reporte.IReporteServicio;

import java.util.List;

@Service
public class ReporteServicio implements IReporteServicio {
    @Autowired
    private ReporteRepositorio reportesRepositorio;

    @Autowired
    private MedicamentoRepositorio medicamentoRepositorio;

    // ================================================================
    // Obtiene todos los reportes de la base de datos
    // ================================================================
    @Override
    public List<Reporte> obtenerReportes() {
        return reportesRepositorio.findAll();
    }

    // ================================================================
    // Busca un reporte por su ID
    // ================================================================
    @Override
    public Reporte obtenerReportePorId(Integer id) {
        return reportesRepositorio.findById(id).orElse(null);
    }

    // ================================================================
    // Crea y guarda un nuevo reporte
    // ================================================================
    @Override
    public Reporte crearReporte(Reporte reporte) {
        // Validar que el medicamento exista
        if (reporte.getMedicamento() == null || reporte.getMedicamento().getIdMedicamento() == null) {
            throw new IllegalArgumentException("Debe proporcionar un medicamento válido para el reporte.");
        }
        Medicamento medicamento = medicamentoRepositorio.findById(reporte.getMedicamento().getIdMedicamento())
                .orElseThrow(() -> new IllegalArgumentException("El Medicamento con ID " + reporte.getMedicamento().getIdMedicamento() + " no existe."));

        reporte.setMedicamento(medicamento);
        return reportesRepositorio.save(reporte);
    }

    // ================================================================
    // Actualiza un reporte existente por su ID
    // ================================================================
    @Override
    public Reporte actualizarReporte(Integer id, Reporte reporte) {
        Reporte reporteExistente = obtenerReportePorId(id);
        if (reporteExistente == null) return null;

        // Validar que el medicamento exista
        if (reporte.getMedicamento() == null || reporte.getMedicamento().getIdMedicamento() == null) {
            throw new IllegalArgumentException("Debe proporcionar un medicamento válido para el reporte.");
        }

        Medicamento medicamento = medicamentoRepositorio.findById(reporte.getMedicamento().getIdMedicamento())
                .orElseThrow(() -> new IllegalArgumentException("El medicamento con ID " + reporte.getMedicamento().getIdMedicamento() + " no existe."));

        reporteExistente.setTitulo(reporte.getTitulo());
        reporteExistente.setEstado(reporte.getEstado());
        reporteExistente.setDescripcion(reporte.getDescripcion());
        reporteExistente.setMedicamento(medicamento);

        return reportesRepositorio.save(reporteExistente);
    }

    // ================================================================
    // Elimina un reporte por su ID
    // ================================================================
    @Override
    public void eliminarReportePorId(Integer id) {
        reportesRepositorio.deleteById(id);
    }
}
