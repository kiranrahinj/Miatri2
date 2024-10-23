package com.org.UserService.Service;

import com.org.UserService.Entity.CustomerDetails;

import java.util.List;

public interface CustomerService {
    public List<CustomerDetails> getAllCustomers();

    public CustomerDetails getCustomerById(int id);

    public CustomerDetails updateCustomerById(int id, CustomerDetails customerDetails);
}
