package udemy.micro.veiculo.domain.dtos;

import lombok.Builder;

@Builder
public record VeiculoResponse(String placa, String chassi, String modelo, String marca, Integer ano) {
}
