package ru.liga.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.liga.entities.Courier;
import ru.liga.entities.CourierStatus;
import java.util.List;
import java.util.Optional;

@Repository
public interface CourierRepository extends CrudRepository<Courier, Integer> {

    Optional<Courier> findCourierByPhone(String phone);

    List<Courier> findCouriersByStatus(CourierStatus status);
}
