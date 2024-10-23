package com.org.UserService.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "order_booking")
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String name;

    private String location;

    private String status="Pending";

    @Temporal(TemporalType.DATE)
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
