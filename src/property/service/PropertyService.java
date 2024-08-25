package property.service;

import java.util.List;

import property.model.Property;
import property.repository.PropertyRepository;

public class PropertyService {
    private PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public List<Property> getAll() {
        return this.propertyRepository.getAll();
    }

    public Property findBy(String location) {
        return this.propertyRepository.findBy(location).orElseThrow(PropertyNotFoundException::new);
    }

    public Property findBy(int id) {
        return this.propertyRepository.findBy(id).orElseThrow(PropertyNotFoundException::new);
    }

}
