package com.org.UserService.Service;

import java.util.Date;

public interface StatementService {
    Double prfitAndLossForDay(Date date);

    Double profitAndLossForMonth(Date startOfMonth, Date endOfMonth);

    Double amountRecievedTo(String name, Date startOfMonth, Date endOfMonth);

    Double paymentsPaid(String name, Date startOfMonth, Date endOfMonth);
}
