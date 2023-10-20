package ru.liga.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.entities.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    @Transactional(readOnly = true)
    @Query("select user from Customer user where user.phone = :phone")
    Customer findCustomerByPhone(@Param("phone") String phone);
}
