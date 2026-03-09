package com.example.demo.test.day4;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {

//        Car baoma = new Baoma().setMoney(new BigDecimal(5));
//        BigDecimal money = baoma.getMoney();
//        baoma.getPublicName();
////        baoma.getPublicType(); 报错，父类对象指向子类，不能调用子类特有的方法
//
//        Set<String> set = new HashSet<>();
//        set.add("1");
//        set.add("2");
//        for (String s : set) {
//            System.out.println(s);
//        }
//
//        Map<String,String> map = new HashMap<>();
//        map.put("tom", "111"); // 方法没有Synchronized修饰
//        Hashtable<Object, Object> hashtable = new Hashtable<>();
//        hashtable.put("tom", "111"); // 方法有Synchronized修饰

        List<Car> list = new ArrayList<>();
        list.add(new Car().setMoney(new BigDecimal(1)));
        list.add(new Car().setMoney(new BigDecimal(2)));
        list.add(new Car().setMoney(new BigDecimal(3)));
        list.add(new Car().setMoney(new BigDecimal(4)));
        list.add(new Car().setMoney(new BigDecimal(5)));

        List<Car> collect = list.stream()
                .filter(car -> car.getMoney().compareTo(new BigDecimal(1)) > 0)
                .filter(car -> car.getMoney().compareTo(new BigDecimal(2)) > 0).collect(Collectors.toList());
    }
}
