package booking.service;

public class PropertyIsNotAvailableException extends RuntimeException {

    public PropertyIsNotAvailableException() {
        super("Property is not available for the selected dates.");
    }

    public PropertyIsNotAvailableException(String message) {
        super(message);
    }
}
