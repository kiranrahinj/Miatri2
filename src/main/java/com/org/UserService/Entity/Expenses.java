package com.org.UserService.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collation = "expense")
public class Expenses {

    @Id
    private String id;

    private Date date;

    private int disel;

    private String diselPaidBy;

    private int expense;

    private String expensePaidBy;

    private String remark;

    private String expenseCreatedBy;

}
