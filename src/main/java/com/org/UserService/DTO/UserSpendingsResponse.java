package com.org.UserService.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserSpendingsResponse {
    private Double spent;
    private Double received;

    public UserSpendingsResponse(Double spent, Double received) {
        this.spent = spent;
        this.received = received;
    }
}
