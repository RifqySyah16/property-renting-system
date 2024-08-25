import java.sql.Connection;

import booking.repository.BookingRepository;
import booking.service.BookingService;
import property.repository.PropertyRepository;
import property.service.PropertyService;
import user.repository.UserRepository;
import user.service.UserService;

public class App {
    public static void main(String[] args) throws Exception {
        Connection connection = DB.connect();
        UserRepository userRepository = new UserRepository(connection);
        UserService userService = new UserService(userRepository);
        PropertyRepository propertyRepository = new PropertyRepository(connection);
        PropertyService propertyService = new PropertyService(propertyRepository);
        BookingRepository bookingRepository = new BookingRepository(connection);
        BookingService bookingService = new BookingService(bookingRepository);

        InputHandler inputHandler = new InputHandler(propertyService, bookingService, userService);
        inputHandler.run();
    }
}
