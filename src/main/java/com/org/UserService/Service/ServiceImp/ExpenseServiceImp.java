package com.org.UserService.Service.ServiceImp;

import com.org.UserService.Entity.Expenses;
import com.org.UserService.Repository.ExpenseRepository;
import com.org.UserService.Service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ExpenseServiceImp implements ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

//    @Override
//    public List<Expenses> findExpenseByDate(Date date) {
//        return expenseRepository.findByDate(date)
//                .orElseThrow(() -> new RuntimeException("Expenses is not found by Date: " + date));
//    }
//
//    @Override
//    public List<Expenses> findExpenseByTwoDates(Date date1, Date date2) {
//        return expenseRepository.findByDates(date1,date2)
//                .orElseThrow(() -> new RuntimeException("Expenses is not found by dates: " + date1 +" "+date2));
//    }

    @Override
    public Expenses addExpense(Expenses expenses, String userName) {
        expenses.setExpenseCreatedBy(userName);
        return expenseRepository.save(expenses);
    }

//    @Override
//    public List<Expenses> findExpensePaidBy(String name) {
//        return expenseRepository.findByExpensePaidBy(name)
//                .orElseThrow(() -> new RuntimeException("Expense is not found by name: " + name));
//    }
//
//    @Override
//    public List<Expenses> findDiselPaidBy(String name) {
//        return expenseRepository.findByDiselPaidBy(name)
//                .orElseThrow(() -> new RuntimeException("Expense is not found by name: " + name));
//    }

    @Override
    public List<Expenses> findAllExpense() {
        return expenseRepository.findAll().reversed();
    }
}
