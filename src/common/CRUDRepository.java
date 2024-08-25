package common;

import java.util.List;
import java.util.Optional;

public interface CRUDRepository<T> {
    List<T> getAll();

    int add(T t);

    Optional<T> findById(int id);
}
