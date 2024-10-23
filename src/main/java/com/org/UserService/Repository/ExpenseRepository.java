package com.org.UserService.Repository;

import com.org.UserService.Entity.Expenses;
import com.org.UserService.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expenses,Integer> {
    Optional<List<Expenses>> findByDate(Date date);

    @Query("from Expenses where date BETWEEN ?1 AND ?2")
    Optional<List<Expenses>> findByDates(Date date1, Date date2);

    Optional<List<Expenses>> findByExpensePaidBy(String name);

    Optional<List<Expenses>> findByDiselPaidBy(String name);

    Optional<List<Expenses>> findByDiselPaidByAndDateBetween(String name, Date date1, Date date2);

    Optional<List<Expenses>> findByExpensePaidByAndDateBetween(String name, Date date1, Date date2);
}
