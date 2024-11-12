package com.org.UserService.Service.ServiceImp;

import com.org.UserService.Entity.CustomerDetails;
import com.org.UserService.Entity.Expenses;
import com.org.UserService.Entity.Order;
import com.org.UserService.Repository.CustomerRepository;
import com.org.UserService.Repository.OrderBookingRepository;
import com.org.UserService.Service.OrderBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderBookingServiceImpl implements OrderBookingService {
    @Autowired
    private OrderBookingRepository orderBookingRepository;

    @Autowired
    private CustomerRepository customerRepository;

//    @Override
//    public List<Order> findOrderByName(String name) {
//        return orderBookingRepository.findByName(name)
//                .orElseThrow(() -> new RuntimeException("Order is not found by name: " + name));
//    }
//
//    @Override
//    public List<Order> findOrderPaymentRecievedTo(String name) {
//        return orderBookingRepository.findByRecievedTo(name)
//                .orElseThrow(() -> new RuntimeException("Order is not found by name: " + name));
//    }
//
//    @Override
//    public List<Order> findOrderPaymentStatus(String name) {
//        return orderBookingRepository.findByPaymentStatus(name)
//                .orElseThrow(() -> new RuntimeException("Order is not found by name: " + name));
//    }
//
//    @Override
//    public List<Order> findOrderFillingBy(String name) {
//        return orderBookingRepository.findByFillingBy(name)
//                .orElseThrow(() -> new RuntimeException("Order is not found by name: " + name));
//    }
//
//    @Override
//    public List<Order> findOrderByDate(Date date) {
//        return orderBookingRepository.findByDate(date)
//                .orElseThrow(() -> new RuntimeException("Order is not found by Date: " + date));
//    }

    @Override
    public List<Order> findOrderByTwoDates(Date date1, Date date2) {
        return orderBookingRepository.findByDates(date1, date2)
                .orElseThrow(() -> new RuntimeException("Order is not found by dates: " + date1 + " " + date2));
    }

    @Override
    public Order addOrder(Order order, String userName) {

        int remainig_amount = order.getRate() - order.getRecievedAmount();
        remainig_amount = remainig_amount > 0 ? remainig_amount : 0;

        String id= UUID.randomUUID().toString();
        order.setOid(id);
        customerRepository.save(CustomerDetails.builder().cid(id)
                .name(order.getName()).date(order.getDate())
                .amountRecievedTo(order.getRecievedTo())
                .totalAmount(order.getRate())
                .receivedAmount(order.getRecievedAmount())
                .location(order.getLocation())
                .remainingAmount(remainig_amount)
                .build());

        order.setOrderCreatedBy(userName);
        return orderBookingRepository.save(order);
    }

    @Override
    public List<Order> findAllOrders() {
        List<Order> list= orderBookingRepository.findAll();
        Collections.reverse(list);
        return list;
    }

    @Override
    public Order updateOrder(Order order, String id, String userName) {
        Order oldorder = orderBookingRepository.findById("id").orElseThrow(() -> new RuntimeException("Order is not found by id: " + id));
        order.setOid(id);
        order.setOrderCreatedBy(userName);
        orderBookingRepository.save(order);

        int remainig_amount = order.getRate() - order.getRecievedAmount();
        remainig_amount = remainig_amount > 0 ? remainig_amount : 0;

        CustomerDetails customerDetails = customerRepository.findByCid(id).orElseThrow(() -> new RuntimeException("Customer is not found by id: " + id));
        customerRepository.save(CustomerDetails.builder().cid(order.getOid())
                .name(order.getName()).date(order.getDate())
                .amountRecievedTo(order.getRecievedTo())
                .totalAmount(order.getRate())
                .receivedAmount(order.getRecievedAmount())
                .location(order.getLocation())
                .remainingAmount(remainig_amount)
                .build());

        return order;
    }

    @Override
    public void deleteorder(String id) {
        orderBookingRepository.deleteById(id);
    }

    @Override
    public Order getOrderById(String id) {
        return orderBookingRepository.findByOid(id).orElseThrow(() -> new RuntimeException("Order is not found by id: " + id));
    }

}
