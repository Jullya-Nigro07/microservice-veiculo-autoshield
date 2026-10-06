package udemy.micro.veiculo.domain.dtos;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record VeiculoResponse(@NotBlank String placa,
                              @NotBlank String modelo,
                              @Positive @Min(1000) @Max(2026) Integer ano,
                              @NotBlank String cor) {
}
