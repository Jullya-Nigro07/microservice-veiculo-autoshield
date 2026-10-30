package udemy.micro.veiculo.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataNotFound.class)
    public ResponseEntity<Map<String, Object>> handleDataNotFound(
            DataNotFound exception) {

        Map<String, Object> body = new HashMap<>();

        body.put("status", 404);
        body.put("error", "Not Found");
        body.put("message", exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(body);
    }

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicateException(
            DuplicateException exception) {

        Map<String, Object> body = new HashMap<>();

        body.put("status", 404);
        body.put("error", "Not Found");
        body.put("message", exception.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(body);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String, Object>> handleValidation(
//            MethodArgumentNotValidException ex) {
//
//        Map<String, Object> response = new LinkedHashMap<>();
//        response.put("status", HttpStatus.BAD_REQUEST.value());
//        response.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
//
//        List<Map<String, String>> messages = ex.getBindingResult()
//                .getFieldErrors()
//                .stream()
//                .map(fieldError -> {
//                    Map<String, String> error = new LinkedHashMap<>();
//                    error.put("field", fieldError.getField());
//                    error.put("message", fieldError.getDefaultMessage());
//                    return error;
//                })
//                .toList();
//
//        response.put("message", messages);
//        return ResponseEntity.badRequest().body(response);
//    }
}