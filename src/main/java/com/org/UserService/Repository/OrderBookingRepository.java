package com.org.UserService.Repository;

import com.org.UserService.Entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrderBookingRepository extends MongoRepository<Order, String> {
    Optional<Order>findByOid(String oid);
    Optional<List<Order>> findByName(String name);

    Optional<List<Order>> findByRecievedTo(String name);

    Optional<List<Order>> findByPaymentStatus(String name);

    Optional<List<Order>> findByFillingBy(String name);

    Optional<List<Order>> findByDate(Date date);

    // MongoDB syntax for a date range query
    @Query("{ 'date': { $gte: ?0, $lte: ?1 } }")
    Optional<List<Order>> findByDates(Date date1, Date date2);

    // Automatically derived query for filtering by fillingBy and date range
    Optional<List<Order>> findByFillingByAndDateBetween(String name, Date date1, Date date2);
}
