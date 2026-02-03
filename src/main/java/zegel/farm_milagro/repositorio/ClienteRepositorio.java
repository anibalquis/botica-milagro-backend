package zegel.farm_milagro.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import zegel.farm_milagro.modelo.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {}
