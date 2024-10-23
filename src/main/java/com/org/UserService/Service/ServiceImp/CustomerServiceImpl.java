package com.org.UserService.Service.ServiceImp;

import com.org.UserService.Entity.CustomerDetails;
import com.org.UserService.Entity.Order;
import com.org.UserService.Repository.CustomerRepository;
import com.org.UserService.Repository.OrderBookingRepository;
import com.org.UserService.Service.CustomerService;
import org.hibernate.query.NativeQuery;
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
    public CustomerDetails getCustomerById(int id) {
        return customerRepository.findById(id).orElseThrow(()-> new RuntimeException("Customer not found"));
    }
    @Override
    public CustomerDetails updateCustomerById(int id, CustomerDetails customer) {

        customerRepository.findById(id).orElseThrow(()-> new RuntimeException("Customer not found"));

        Order order= orderBookingRepository.findById(id).orElseThrow(()-> new RuntimeException("OrderBooking not found"));
        order.setRecievedAmount(customer.getReceivedAmount());
        order.setRecievedTo(customer.getAmountRecievedTo());

        orderBookingRepository.save(order);

        int remainig_amount= customer.getTotalAmount()-customer.getReceivedAmount();
        remainig_amount= Math.max(remainig_amount, 0);

        customer.setId(id);
        customer.setRemainingAmount(remainig_amount);
        customer.setDate(order.getDate());
        customer.setLocation(order.getLocation());
        customerRepository.save(customer);

        return customer;
    }
}

