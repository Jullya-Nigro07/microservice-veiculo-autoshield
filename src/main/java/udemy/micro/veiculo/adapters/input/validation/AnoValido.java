package udemy.micro.veiculo.adapters.input.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AnoValidoValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface AnoValido {
    String message() default "O ano deve estar entre 1800 e o ano atual";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
