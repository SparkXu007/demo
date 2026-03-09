package com.example.demo.test.day4;

import java.math.BigDecimal;

public class Baoma extends Car{

    @Override
    public BigDecimal getMoney() {
        return super.getMoney();
    }

    @Override
    public Car setMoney(BigDecimal money) {
        return super.setMoney(money);
    }

    private String getType() {
        return "1111";
    }

    public String getPublicType() {
        return getType();
    }
}
