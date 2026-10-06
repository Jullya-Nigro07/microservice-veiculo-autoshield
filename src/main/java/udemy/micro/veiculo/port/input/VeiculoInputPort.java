package udemy.micro.veiculo.port.input;

import udemy.micro.veiculo.domain.dtos.VeiculoRequest;
import udemy.micro.veiculo.domain.dtos.VeiculoResponse;

public interface VeiculoInputPort {
    VeiculoResponse registerVeiculo(VeiculoRequest veiculoRequest);
    VeiculoResponse searchVeiculo(String chassiVeiculo);
}
