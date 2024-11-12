package com.org.UserService.Entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@ToString
@Document(collation = "order_booking")
public class Order {
    @Id
    private String oid;

    private String name;

    private String location;

    private String status="Pending";

    private Date date;

    private String materialType;

    private int rate;

    private String paymentStatus="UnPaid";

    private int recievedAmount;

    private String recievedTo="None";

    private int filling;

    private String fillingBy;

    private String orderCreatedBy;

}
