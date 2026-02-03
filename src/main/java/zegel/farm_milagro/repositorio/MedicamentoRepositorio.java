package zegel.farm_milagro.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import zegel.farm_milagro.modelo.Medicamento;

public interface MedicamentoRepositorio extends JpaRepository<Medicamento,Integer> {}
