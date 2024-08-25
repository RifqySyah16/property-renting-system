package booking.service;

import java.sql.Date;
import java.sql.Timestamp;

import booking.model.Booking;
import booking.repository.BookingRepository;
import property.model.Property;
import user.model.User;

public class BookingService {
    private BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking create(Booking booking, User user, Property property, Date checkInDate, Date checkOutDate,
            Timestamp createdAt) {

        boolean isAvailable = this.bookingRepository.isPropertyAvailable(property.getId(), checkInDate, checkOutDate);

        if (!isAvailable) {
            throw new PropertyIsNotAvailableException();
        }

        Booking newBooking = Booking.createBooking(user, property, checkInDate, checkOutDate, createdAt);

        int newId = this.bookingRepository.create(newBooking);
        newBooking.setId(newId);

        return newBooking;
    }

}
