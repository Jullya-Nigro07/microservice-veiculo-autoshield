package udemy.micro.veiculo.adapters.output.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import udemy.micro.veiculo.adapters.output.entity.Veiculo;
import udemy.micro.veiculo.port.output.VeiculoOutputPort;

import java.util.Optional;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long>, VeiculoOutputPort {
    Optional<Veiculo> findVeiculoByChassi(String chassis);
    Veiculo save(Veiculo veiculo);
    Veiculo deleteVeiculoByChassi(String chassi);
}