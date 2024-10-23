package com.org.UserService.Service.ServiceImp;

import com.org.UserService.Entity.CustomerDetails;
import com.org.UserService.Entity.Order;
import com.org.UserService.Repository.CustomerRepository;
import com.org.UserService.Repository.OrderBookingRepository;
import com.org.UserService.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderBookingRepository orderBookingRepository;

    @Override
    public List<CustomerDetails> getAllCustomers() {
        return customerRepository.findAll().reversed();
    }
    @Override
    public CustomerDetails getCustomerById(int id) {
        return customerRepository.findById(id).orElseThrow(()-> new RuntimeException("Customer not found"));
    }
    @Override
    public CustomerDetails updateCustomerById(int id, CustomerDetails customerDetails) {

        customerRepository.findById(id).orElseThrow(()-> new RuntimeException("Customer not found"));

        int remainig_amount= customerDetails.getTotalAmount()-customerDetails.getReceivedAmount();
        remainig_amount=remainig_amount >0 ?remainig_amount:0;

        customerDetails.setId(id);
        customerDetails.setRemainingAmount(remainig_amount);
        customerRepository.save(customerDetails);

        Order order= orderBookingRepository.findById(id).orElseThrow(()-> new RuntimeException("OrderBooking not found"));
        order.setRecievedAmount(customerDetails.getReceivedAmount());
        order.setRecievedTo(customerDetails.getAmountRecievedTo());

        orderBookingRepository.save(order);

        return customerDetails;
    }
}

