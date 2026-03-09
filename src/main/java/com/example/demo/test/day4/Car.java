package com.example.demo.test.day4;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class Car {

    private BigDecimal money;

    private String getName() {
        return "car";
    }

    public String getPublicName() {
        return "publicName";
    }
}
