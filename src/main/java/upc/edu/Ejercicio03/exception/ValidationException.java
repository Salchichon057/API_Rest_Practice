package upc.edu.Ejercicio03.exception;

public class ValidationException extends RuntimeException{
    public ValidationException() {
        super();
    }
    public ValidationException(String message) {
        super(message);
    }
}
