package ru.liga.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.liga.entities.Courier;
import java.util.Optional;

@Repository
public interface CourierRepository extends CrudRepository<Courier, Integer> {

    Optional<Courier> findCourierByPhone(String phone);
}
