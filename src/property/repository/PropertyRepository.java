package property.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import property.model.Property;

public class PropertyRepository {
    private Connection connection;

    public PropertyRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Property> getAll() {
        List<Property> propertys = new ArrayList<>();

        try {
            String sql = "SELECT * FROM property";

            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String location = resultSet.getString("location");
                String description = resultSet.getString("description");
                Property property = new Property(id, name, location, description);
                propertys.add(property);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return propertys;
    }

    public Optional<Property> findBy(String location) {

        String sql = "SELECT * FROM property WHERE location = ?";

        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, location);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String locName = resultSet.getString("location");
                String description = resultSet.getString("description");

                return Optional.of(new Property(name, locName, description));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();

    }

    public Optional<Property> findBy(int id) {

        String sql = "SELECT * FROM property WHERE id = ?";

        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String location = resultSet.getString("location");
                String description = resultSet.getString("description");

                return Optional.of(new Property(name, location, description));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();

    }
}
