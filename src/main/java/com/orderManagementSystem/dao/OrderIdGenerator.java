package com.orderManagementSystem.dao;

import java.util.UUID;

public class OrderIdGenerator {

    private static Long idSeed=1000L;

    public static Long generateNewOrderId(){
        
        
        return ++idSeed;
    }

    public static void generateUUIDBasedOrderID(){

        String uuid = UUID.randomUUID().toString();


    }
}
