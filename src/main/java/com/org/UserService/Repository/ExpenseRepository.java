package com.org.UserService.Repository;

import com.org.UserService.Entity.Expenses;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends MongoRepository<Expenses, String> {

    Optional<List<Expenses>> findByDate(Date date);

    // Use MongoDB query syntax for a date range query
    @Query("{ 'date': { $gte: ?0, $lte: ?1 } }")
    Optional<List<Expenses>> findByDates(Date date1, Date date2);

    Optional<List<Expenses>> findByExpensePaidBy(String name);

    Optional<List<Expenses>> findByDiselPaidBy(String name);

    // Method names to automatically create the queries for date range
    Optional<List<Expenses>> findByDiselPaidByAndDateBetween(String name, Date date1, Date date2);

    Optional<List<Expenses>> findByExpensePaidByAndDateBetween(String name, Date date1, Date date2);
}
