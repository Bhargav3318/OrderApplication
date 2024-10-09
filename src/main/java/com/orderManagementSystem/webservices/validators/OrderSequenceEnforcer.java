
package com.orderManagementSystem.webservices.validators;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.orderManagementSystem.constants.OrderOperations;
import com.orderManagementSystem.constants.OrderStatus;
import com.orderManagementSystem.webservices.models.OrderResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Component
public class OrderSequenceEnforcer {

    //key is the operationBeingPerformed ; value is the list of allowed statuses to perform that operation
    Map<String,List<String>> allowedOperationsForEachStatus;

    public OrderSequenceEnforcer(){

        this.allowedOperationsForEachStatus = new HashMap<>();

        List<String> createOrderList = new ArrayList<>();
        createOrderList.add(null);
        allowedOperationsForEachStatus.put(OrderOperations.CREATE_ORDER,createOrderList);
        List<String> paymentDetailsList = new ArrayList<>();
        paymentDetailsList.add(OrderStatus.CREATED);
        allowedOperationsForEachStatus.put(OrderOperations.ADD_PAYMENT_DETAILS, paymentDetailsList);
        List<String> retrieveOrderList = Arrays.asList(OrderStatus.CREATED,OrderStatus.DELIVERED,OrderStatus.PAYMENT_PROCESSED,OrderStatus.PREPARATION,OrderStatus.READY);
        allowedOperationsForEachStatus.put(OrderOperations.RETRIEVE_ORDER, retrieveOrderList);
        List<String> updateOrderList = Arrays.asList(OrderStatus.CREATED,OrderStatus.PAYMENT_PROCESSED,OrderStatus.PREPARATION,OrderStatus.READY);
        allowedOperationsForEachStatus.put(OrderOperations.PREPARATION, updateOrderList);
    }

    @SuppressWarnings("null")
    public boolean isValidOperationOnOrder(OrderResponse order, String operationBeingPerformed){

        if(order==null && (operationBeingPerformed.equals(OrderOperations.CREATE_ORDER)||
            operationBeingPerformed.equals(OrderOperations.RETRIEVE_ORDER))){
            return true;
        }

        if(allowedOperationsForEachStatus.get(operationBeingPerformed).contains(order.getStatus())){
            return true;
        }
            
       return false;


    }
}
