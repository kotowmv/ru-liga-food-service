package ru.liga.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.entities.Courier;

@Repository
public interface CourierRepository extends CrudRepository<Courier, Integer> {

    @Transactional(readOnly = true)
    @Query("select courier from Courier courier where courier.phone = :phone")
    Courier findCourierByPhone(@Param("phone") String phone);
}
