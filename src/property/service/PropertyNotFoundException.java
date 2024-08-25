package property.service;

public class PropertyNotFoundException extends RuntimeException {

    public PropertyNotFoundException() {
        super("Property Not Found");
    }

    public PropertyNotFoundException(String message) {
        super(message);
    }

}
