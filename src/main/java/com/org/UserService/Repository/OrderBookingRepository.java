package com.org.UserService.Repository;

import com.org.UserService.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrderBookingRepository extends JpaRepository<Order, Integer> {

    Optional<List<Order>> findByName(String name);

    Optional<List<Order>> findByRecievedTo(String name);

    Optional<List<Order>> findByPaymentStatus(String name);

    Optional<List<Order>> findByFillingBy(String name);

    Optional<List<Order>> findByDate(Date date);

    @Query("from Order where date BETWEEN ?1 AND ?2")
    Optional<List<Order>> findByDates(Date date1, Date date2);

    Optional<List<Order>> findByFillingByAndDateBetween(String name,Date date1,Date date2);

}
