package com.example.demo.test.day1;

public class Test1 {


    public static void main(String[] args) {

        testThreadStart();
    }

    public static void test() {

        int size = 3;
        Long userId = 10L;
        String userName = "xuKang";

        int hashCode = userName.hashCode();
        System.out.println("userName hashCode:" + hashCode);
        // 取后3位
        int nameGen = hashCode & 7;
        System.out.println("userName hashCode 后3位 :" + nameGen);

        long newId = userId << 3 | nameGen;
        System.out.println("新的id:" + newId);

        // 将新的ID按照8取模
    }

    public static void testThreadStart() {
        Thread thread = new Thread();
        thread.start();
    }
}
