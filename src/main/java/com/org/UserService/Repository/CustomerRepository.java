package com.org.UserService.Repository;

import com.org.UserService.Entity.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerDetails,Integer> {

    Optional<List<CustomerDetails>> findByAmountRecievedToAndDateBetween(String amountRecievedTo, Date from, Date to);
}
