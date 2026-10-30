package udemy.micro.veiculo.adapters.input.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Year;

public class AnoValidoValidator implements ConstraintValidator<AnoValido, Integer> {

    @Override
    public boolean isValid(Integer ano, ConstraintValidatorContext context) {
        if (ano == null) {
            return true;
        }
        return ano >= 1800 && ano <= Year.now().getValue();
    }
}

