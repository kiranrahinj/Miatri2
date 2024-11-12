package com.org.UserService.Service;

import com.org.UserService.Entity.Order;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.List;

public interface OrderBookingService {

    List<Order> findOrderByTwoDates(Date date1, Date date2);

    Order addOrder(Order order, String userName);

    List<Order> findAllOrders();

    Order updateOrder(Order order, String id, String userName);

    void deleteorder(String id);

    Order getOrderById(String id);
}
