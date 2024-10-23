package com.org.UserService.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "customer_detail")
public class CustomerDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String location;

    @Temporal(TemporalType.DATE)
    private Date date;

    private int totalAmount;

    private int receivedAmount;

    private int remainingAmount;

    private String amountRecievedTo;

}
