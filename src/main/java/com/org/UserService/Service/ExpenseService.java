package com.org.UserService.Service;

import com.org.UserService.Entity.Expenses;

import java.util.Date;
import java.util.List;

public interface ExpenseService {


    Expenses addExpense(Expenses expenses, String userName);


    List<Expenses> findAllExpense();
}
