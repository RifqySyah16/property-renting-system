package booking.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import booking.model.Booking;

public class BookingRepository {
    private Connection connection;

    public BookingRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Booking> getAll() {
        List<Booking> bookings = new ArrayList<>();

        String sql = "SELECT * FROM booking";

        try {
            Statement statement = this.connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Booking booking = Booking.createBooking(resultSet);

                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookings;
    }

    public int create(Booking booking) {
        String sql = "INSERT INTO booking (property_id, user_id, check_in_date, check_out_date, created_at) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);

            statement.setInt(1, booking.getPropertyId());
            statement.setInt(2, booking.getUserId());
            statement.setDate(3, booking.getCheckInDate());
            statement.setDate(4, booking.getCheckOutDate());
            statement.setTimestamp(5, booking.getCreatedAt());

            int insertedRows = statement.executeUpdate();

            if (insertedRows > 0) {
                ResultSet resultSet = statement.getGeneratedKeys();

                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public boolean isPropertyAvailable(int propertyId, Date checkInDate, Date checkOutDate) {
        String sql = "SELECT COUNT(*) FROM booking WHERE property_id = ? AND " +
                "(check_in_date < ? AND check_out_date > ?)";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, propertyId);
            preparedStatement.setDate(2, checkOutDate);
            preparedStatement.setDate(3, checkInDate);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count == 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
