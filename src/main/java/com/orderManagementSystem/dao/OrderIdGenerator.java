package com.orderManagementSystem.dao;

public class OrderIdGenerator {

    private static Long idSeed=1000L;

    public static Long generateNewOrderId(){
        
        return ++idSeed;
    }

}
