package com.org.UserService.Service.ServiceImp;

import com.org.UserService.Entity.CustomerDetails;
import com.org.UserService.Entity.Expenses;
import com.org.UserService.Entity.Order;
import com.org.UserService.Repository.CustomerRepository;
import com.org.UserService.Repository.ExpenseRepository;
import com.org.UserService.Repository.OrderBookingRepository;
import com.org.UserService.Service.StatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StatementServiceImpl implements StatementService {
    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private OrderBookingRepository orderBookingRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private Double helper(List<Order> orders, List<Expenses> expense) {
        double sumOfRates=orders.stream().mapToDouble(Order::getRate).sum();
        double sumOforderFilling=orders.stream().mapToDouble(Order::getFilling).sum();

        double dayProfit=sumOfRates-sumOforderFilling;

        double dayExpense = expense.stream().mapToDouble(e -> e.getExpense() + e.getDisel()).sum();

        return dayProfit - dayExpense;
    }

    @Override
    public Double prfitAndLossForDay(Date date){
        List<Order> orders=orderBookingRepository.findByDate(date).orElseThrow(()-> new RuntimeException("Order Not foung with date :"+date));
        List<Expenses> expense=expenseRepository.findByDate(date).orElseThrow(()-> new RuntimeException("Expense Not foung with date :"+date));

        return helper(orders, expense);
    }

    @Override
    public Double profitAndLossForMonth(Date startOfMonth, Date endOfMonth) {

        List<Order> orders = orderBookingRepository.findByDates(startOfMonth, endOfMonth)
                .orElseThrow(() -> new RuntimeException("No Orders found in the date range"));

        List<Expenses> expenses = expenseRepository.findByDates(startOfMonth, endOfMonth)
                .orElseThrow(() -> new RuntimeException("No Expenses found in the date range"));

        return helper(orders, expenses);
    }


    @Override
    public Double amountRecievedTo(String name, Date startOfMonth, Date endOfMonth){
        List<CustomerDetails> customerDetail=customerRepository.findByAmountRecievedToAndDateBetween(name, startOfMonth, endOfMonth).orElseThrow();
        return customerDetail.stream().mapToDouble(CustomerDetails::getReceivedAmount).sum();
    }

    @Override
    public Double paymentsPaid(String name, Date startOfMonth, Date endOfMonth){
        List<Order> orders=orderBookingRepository.findByFillingByAndDateBetween(name, startOfMonth, endOfMonth).orElseThrow();
        List<Expenses> disel=expenseRepository.findByDiselPaidByAndDateBetween(name, startOfMonth, endOfMonth).orElseThrow();
        List<Expenses> expense=expenseRepository.findByExpensePaidByAndDateBetween(name, startOfMonth, endOfMonth).orElseThrow();

        double orderFill_payment= orders.stream().mapToDouble(Order::getFilling).sum();
        double disel_payment=disel.stream().mapToDouble(Expenses::getDisel).sum();
        double expense_payment=expense.stream().mapToDouble(Expenses::getExpense).sum();

        return orderFill_payment + disel_payment + expense_payment ;
    }
}
