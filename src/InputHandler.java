import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import booking.model.Booking;
import booking.service.BookingService;
import property.model.Property;
import property.service.PropertyService;
import user.model.User;
import user.service.UserService;

public class InputHandler {
    private PropertyService propertyService;
    private BookingService bookingService;
    private UserService userService;
    private Scanner scanner = new Scanner(System.in);

    public InputHandler(PropertyService propertyService, BookingService bookingService, UserService userService) {
        this.propertyService = propertyService;
        this.bookingService = bookingService;
        this.userService = userService;
    }

    public void run() {
        System.out.println("\r\n" + //
                "   1. View All Property\r\n" + //
                "   2. Book Property\r\n" + //
                "   3. Find Property by Location\r\n" + //
                "   0. Exit");
        System.out.print("Choose Menu : ");
        int option = this.scanner.nextInt();

        while (option != 0) {
            switch (option) {
                case 1:
                    this.viewAllPropertys();

                    break;
                case 2:
                    this.bookProperty();

                    break;
                case 3:
                    this.findProperty();

                    break;
                default:
                    break;
            }
        }

    }

    private void findProperty() {
        System.out.print("Input Property Location : ");
        String location = this.scanner.next();

        Property property = this.propertyService.findBy(location);
        System.out.println(property);
    }

    private void bookProperty() {
        System.out.print("Input Your ID : ");
        int userId = this.scanner.nextInt();

        User user = this.userService.findBy(userId);

        System.out.print("Input The Property ID : ");
        int propertyId = this.scanner.nextInt();

        Property property = this.propertyService.findBy(propertyId);

        System.out.print("Input Check In Date : ");
        String checkInDateStr = this.scanner.next();

        System.out.print("Input Check In Date : ");
        String checkOutDateStr = this.scanner.next();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date checkInDate = null;
        Date checkOutDate = null;

        try {
            checkInDate = new Date(dateFormat.parse(checkInDateStr).getTime());
            checkOutDate = new Date(dateFormat.parse(checkOutDateStr).getTime());
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
            return;
        }

        Timestamp createdAt = new Timestamp(System.currentTimeMillis());

        Booking newBooking = new Booking(property.getId(), user.getId(), checkInDate, checkOutDate, createdAt);

        Booking savedBooking = this.bookingService.create(newBooking, user, property, checkInDate, checkOutDate,
                createdAt);

        System.out.println("Property Successfully Booked" + savedBooking);
    }

    private void viewAllPropertys() {
        List<Property> properties = this.propertyService.getAll();

        for (Property property : properties) {
            System.out.println(property);
        }

    }

}
