package com.org.UserService.Service.ServiceImp;

import com.org.UserService.Entity.CustomerDetails;
import com.org.UserService.Entity.Order;
import com.org.UserService.Repository.CustomerRepository;
import com.org.UserService.Repository.OrderBookingRepository;
import com.org.UserService.Service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderBookingRepository orderBookingRepository;

    @Override
    public List<CustomerDetails> getAllCustomers() {
        List<CustomerDetails> list= customerRepository.findAll();
        Collections.reverse(list);
        return list;
    }
    @Override
    public CustomerDetails getCustomerById(String id) {
        return customerRepository.findByCid(id).orElseThrow(()-> new RuntimeException("Customer not found"));

    }
    @Override
    public CustomerDetails updateCustomerById(String id, CustomerDetails customer) {

        customerRepository.findByCid(id).orElseThrow(()-> new RuntimeException("Customer not found"));

        Order order= orderBookingRepository.findById("").orElseThrow(()-> new RuntimeException("OrderBooking not found"));
        order.setRecievedAmount(customer.getReceivedAmount());
        order.setRecievedTo(customer.getAmountRecievedTo());

        orderBookingRepository.save(order);

        int remainig_amount= customer.getTotalAmount()-customer.getReceivedAmount();
        remainig_amount= Math.max(remainig_amount, 0);

        customer.setCid(id);
        customer.setRemainingAmount(remainig_amount);
        customer.setDate(order.getDate());
        customer.setLocation(order.getLocation());
        customerRepository.save(customer);

        return customer;
    }
}

