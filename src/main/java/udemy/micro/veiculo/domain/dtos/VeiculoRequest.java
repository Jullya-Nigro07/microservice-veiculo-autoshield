package udemy.micro.veiculo.domain.dtos;

import jakarta.validation.constraints.*;
import lombok.Builder;
import udemy.micro.veiculo.adapters.input.validation.AnoValido;

@Builder
public record VeiculoRequest(@NotBlank String placa,
                             @NotBlank String chassi,
                             @NotBlank String marca,
                             @NotBlank String modelo,
                             @NotNull @AnoValido Integer ano,
                             @NotBlank String cor,
                             @NotNull Integer quilometragem
                             ) {
}
