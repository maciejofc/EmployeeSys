package pl.maciejowsky.employeemanagement.dao.exception;

public class ResourceAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = 2L;

    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
}
