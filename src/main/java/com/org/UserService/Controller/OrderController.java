package com.org.UserService.Controller;

import com.org.UserService.Entity.Order;
import com.org.UserService.Service.OrderBookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/user/order")
public class OrderController {

    @Autowired
    OrderBookingService orderBookingService;

    @PostMapping("/addOrder")
    public Order addOrder(@RequestBody Order order, @AuthenticationPrincipal UserDetails userDetails) {
        return orderBookingService.addOrder(order, userDetails.getUsername());
    }

    @GetMapping("/getAllOrders")
    public List<Order> findAllOrders() {
        return orderBookingService.findAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable int id){
       return orderBookingService.getOrderById(id);
    }

    @PutMapping("/update_order/{id}")
    public Order updateOrders(@RequestBody Order order, @PathVariable int id, @AuthenticationPrincipal UserDetails userDetails){
        log.info(String.valueOf(id));
        log.info(order.toString());
        return orderBookingService.updateOrder(order,id,userDetails.getUsername());
    }

//    @GetMapping("/customerName")
//    public List<Order> findOrderByName(@RequestParam String name){
//        return orderBookingService.findOrderByName(name);
//    }
//
//    @GetMapping("/paymentRecieveTo")
//    public List<Order> findOrderPaymentRecievedTo(@RequestParam String name){
//        return orderBookingService.findOrderPaymentRecievedTo(name);
//    }
//
//    @GetMapping("/paymentStatus")
//    public List<Order> findOrderByPaymentStatus(@RequestParam String name){
//        return orderBookingService.findOrderPaymentStatus(name);
//    }
//
//    @GetMapping("/fillBy")
//    public List<Order> findOrderFillingBy(@RequestParam String name) {
//        return orderBookingService.findOrderFillingBy(name);
//    }
//
//    @GetMapping("/date")
//    public List<Order> findOrderByDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
//        return orderBookingService.findOrderByDate(date);
//    }

    @GetMapping("/dates")
    public List<Order> findOrderByTwoDates(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date1, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date2){
        return orderBookingService.findOrderByTwoDates(date1, date2);
    }

}
