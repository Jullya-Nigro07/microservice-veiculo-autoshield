package udemy.micro.veiculo.port.input;

import udemy.micro.veiculo.domain.dtos.VeiculoRequest;
import udemy.micro.veiculo.domain.dtos.VeiculoResponse;
import udemy.micro.veiculo.domain.dtos.VeiculoUpdateRequest;

public interface VeiculoInputPort {
    VeiculoResponse registerVeiculo(VeiculoRequest veiculoRequest);
    VeiculoResponse searchVeiculo(String chassiVeiculo);
    VeiculoResponse updateVeiculo(VeiculoUpdateRequest dados, String chassi);
    VeiculoResponse deleteVeiculo(String chassi);
}
