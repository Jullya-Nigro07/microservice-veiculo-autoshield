package udemy.micro.veiculo.domain.dtos;

import lombok.Builder;
import udemy.micro.veiculo.adapters.input.validation.AnoValido;

@Builder
public record VeiculoUpdateRequest(String placa,
                                   String chassi,
                                   String marca,
                                   String modelo,
                                   @AnoValido Integer ano,
                                   String cor,
                                   Integer quilometragem) {
}
