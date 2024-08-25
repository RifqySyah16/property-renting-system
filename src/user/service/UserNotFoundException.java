package user.service;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("User ID Not Found");
    }

    public UserNotFoundException(String message) {
        super(message);
    }

}
