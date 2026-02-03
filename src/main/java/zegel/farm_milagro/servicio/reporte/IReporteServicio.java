package zegel.farm_milagro.servicio.reporte;

import zegel.farm_milagro.modelo.Reporte;
import java.util.List;

public interface IReporteServicio {
    List<Reporte> obtenerReportes();
    Reporte obtenerReportePorId(Integer id);
    Reporte crearReporte(Reporte reporte);
    Reporte actualizarReporte(Integer id, Reporte reporte);
    void eliminarReportePorId(Integer id);
}
