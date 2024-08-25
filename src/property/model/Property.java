package property.model;

public class Property {
    private int id;
    private String name;
    private String location;
    private String description;

    public Property(int id, String name, String location, String description) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
    }

    public Property(String name, String location, String description) {
        this.name = name;
        this.location = location;
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Property [id=" + id + ", name=" + name + ", location=" + location + ", description=" + description
                + "]";
    }

}
