package com.org.UserService.Controller;

import com.org.UserService.Entity.CustomerDetails;
import com.org.UserService.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/getAllCustomer")
    public List<CustomerDetails> getAllCustomers(){
        return customerService.getAllCustomers();
    }
    @GetMapping("/getCustomer/{id}")
    public CustomerDetails getCustomerById(@PathVariable String id){
        return customerService.getCustomerById(id);
    }
    @PutMapping("/updateCustomer/{id}")
    public CustomerDetails updateCustomerById(@PathVariable String id, @RequestBody CustomerDetails customerDetails){
        return customerService.updateCustomerById(id, customerDetails);
    }
}
