package com.org.UserService.Controller;

import com.org.UserService.Entity.Expenses;
import com.org.UserService.Service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user/expsense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

//    @GetMapping("/date")
//    public List<Expenses> findExpenseByDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
//        return expenseService.findExpenseByDate(date);
//    }
//    @GetMapping("/dates")
//    public List<Expenses> findExpenseByTwoDates(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date1, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date2){
//        return expenseService.findExpenseByTwoDates(date1,date2);
//    }

    @PostMapping("/addExpense")
    public Expenses addExpense(@RequestBody Expenses expenses, @AuthenticationPrincipal UserDetails userDetails){
        return expenseService.addExpense(expenses, userDetails.getUsername());
    }

//    @GetMapping("/expensePaidBy")
//    public List<Expenses> findExpensePaidBy(@RequestParam String name){
//        return expenseService.findExpensePaidBy(name);
//    }
//
//    @GetMapping("/diselPaidBy")
//    public List<Expenses> findDiselPaidBy(@RequestParam String name){
//        return expenseService.findDiselPaidBy(name);
//    }

    @GetMapping("/getAllExpense")
    public List<Expenses> findAllExpense(){
        return expenseService.findAllExpense();
    }

}
