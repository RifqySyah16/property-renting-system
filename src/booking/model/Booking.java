package booking.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import property.model.Property;
import user.model.User;

public class Booking {
    private int id;
    private int propertyId;
    private int userId;
    private Date checkInDate;
    private Date checkOutDate;
    private Timestamp createdAt;

    public Booking(int id, int propertyId, int userId, Date checkInDate, Date checkOutDate, Timestamp createdAt) {
        this.id = id;
        this.propertyId = propertyId;
        this.userId = userId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.createdAt = createdAt;
    }

    public Booking(int propertyId, int userId, Date checkInDate, Date checkOutDate, Timestamp createdAt) {
        this.propertyId = propertyId;
        this.userId = userId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.createdAt = createdAt;
    }

    public int setId(int id) {
        return this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public int getUserId() {
        return userId;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public static Booking createBooking(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int propertyId = resultSet.getInt("property_id");
        int userId = resultSet.getInt("user_id");
        Date checkInDate = resultSet.getDate("check_in_date");
        Date checkOutDate = resultSet.getDate("check_out_date");
        Timestamp createdAt = resultSet.getTimestamp("created_at");

        return new Booking(id, propertyId, userId, checkInDate, checkOutDate, createdAt);
    }

    public static Booking createBooking(User user, Property property, Date checkInDate, Date checkOutDate,
            Timestamp createdAt) {
        return new Booking(property.getId(), user.getId(), checkInDate, checkOutDate, createdAt);
    }

    @Override
    public String toString() {
        return "Booking [id=" + id + ", propertyId=" + propertyId + ", userId=" + userId + ", checkInDate="
                + checkInDate + ", checkOutDate=" + checkOutDate + ", createdAt=" + createdAt + "]";
    }
}
