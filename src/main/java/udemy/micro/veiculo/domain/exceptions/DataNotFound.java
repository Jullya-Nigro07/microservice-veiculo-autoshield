package udemy.micro.veiculo.domain.exceptions;

public class DataNotFound extends RuntimeException {
    public DataNotFound(String message) {
        super(message);
    }
}
