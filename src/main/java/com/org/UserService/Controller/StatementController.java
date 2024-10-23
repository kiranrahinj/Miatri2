package com.org.UserService.Controller;

import com.org.UserService.DTO.UserSpendingsResponse;
import com.org.UserService.Service.StatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.Date;

@RestController
@RequestMapping("/user/profit")
public class StatementController {
    @Autowired
    private StatementService statementService;

    @GetMapping("/day")
    public Double prfitAndLossForDay(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")Date date){
        return statementService.prfitAndLossForDay(date);
    }
    @GetMapping("/many")
    public Double profitAndLossForMonth(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")Date startOfMonth, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")Date endOfMonth){
        return statementService.profitAndLossForMonth(startOfMonth, endOfMonth);
    }

    @GetMapping("/spendings")
    public ResponseEntity<UserSpendingsResponse> totalSpendings(
            @RequestParam String name,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startOfMonth,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endOfMonth) {

        Double spends = statementService.paymentsPaid(name, startOfMonth, endOfMonth);
        Double received = statementService.amountRecievedTo(name, startOfMonth, endOfMonth);

        // Check if spends or received values are null (indicating no data found)
        if (spends == null || received == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        UserSpendingsResponse response = new UserSpendingsResponse(spends, received);
        return ResponseEntity.ok(response);
    }


}
