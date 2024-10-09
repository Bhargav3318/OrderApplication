package com.orderManagementSystem.dao.localData;

import org.springframework.stereotype.Component;

import com.orderManagementSystem.dao.OrderDAO;
import com.orderManagementSystem.dao.exceptions.OrderSQLExecption;
import com.orderManagementSystem.dao.models.Order;


public class OrderDaoLocalMapImpl implements OrderDAO {

    @Override
    public void insertOrder(Order order) throws OrderSQLExecption {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertOrder'");
    }

    @Override
    public Order getOrderById(Long orderId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOrderById'");
    }

    @Override
    public void updateOrder(Order order) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateOrder'");
    }

}
