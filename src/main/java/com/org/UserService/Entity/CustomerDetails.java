package com.org.UserService.Entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collation = "customer_detail")
public class CustomerDetails {
    @Id
    private String cid;

    private String name;

    private String location;

    private Date date;

    private int totalAmount;

    private int receivedAmount;

    private int remainingAmount;

    private String amountRecievedTo;

}
