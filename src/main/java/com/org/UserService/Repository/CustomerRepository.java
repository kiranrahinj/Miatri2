package com.org.UserService.Repository;

import com.org.UserService.Entity.CustomerDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends MongoRepository<CustomerDetails,Integer> {

    Optional<List<CustomerDetails>> findByAmountRecievedToAndDateBetween(String amountRecievedTo, Date from, Date to);

    Optional<CustomerDetails> findByCid(String cid);
}
