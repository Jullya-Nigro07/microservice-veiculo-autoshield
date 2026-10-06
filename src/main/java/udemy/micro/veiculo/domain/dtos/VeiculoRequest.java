package udemy.micro.veiculo.domain.dtos;

import jakarta.validation.constraints.*;

public record VeiculoRequest(@NotBlank String placa,
                             @NotBlank String chassi,
                             @NotBlank String marca,
                             @NotBlank String modelo,
                             @Positive  @Min(1800) @Max(2026) Integer ano,
                             @NotBlank String cor,
                             @NotNull Integer quilometragem
                             ) {
}
