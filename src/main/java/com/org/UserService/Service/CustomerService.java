package com.org.UserService.Service;

import com.org.UserService.Entity.CustomerDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    public List<CustomerDetails> getAllCustomers();

    public CustomerDetails getCustomerById(String id);

    public CustomerDetails updateCustomerById(String id, CustomerDetails customerDetails);
}
