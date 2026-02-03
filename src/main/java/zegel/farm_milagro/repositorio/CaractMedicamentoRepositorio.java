package zegel.farm_milagro.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import zegel.farm_milagro.modelo.CaracteristicaMedicamento;

public interface CaractMedicamentoRepositorio extends JpaRepository<CaracteristicaMedicamento,Integer> {}
