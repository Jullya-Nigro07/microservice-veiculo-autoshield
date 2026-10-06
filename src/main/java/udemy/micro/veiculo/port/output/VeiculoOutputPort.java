package udemy.micro.veiculo.port.output;

import udemy.micro.veiculo.adapters.output.entity.Veiculo;

import java.util.Optional;

public interface VeiculoOutputPort {
    Optional<Veiculo> findVeiculoByChassi(String chassis);
    Veiculo save(Veiculo veiculo);
}
