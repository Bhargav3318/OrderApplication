package com.orderManagementSystem.dao;

import java.sql.SQLException;

import com.orderManagementSystem.dao.exceptions.OrderSQLExecption;
import com.orderManagementSystem.dao.models.Order;

public interface OrderDAO {

  
    /** CREATE insert order into the orders table
     * @throws SQLException 
     * 
    */
    public void insertOrder(Order order) throws OrderSQLExecption, SQLException;

    /**
     * RETRIEVE Order by orderId
     *  */

    public Order getOrderById(Long orderId);

    
     /**
      * UPDATE Order by orderId and values of fields that are changing.
     * @throws SQLException 
      */
    public void updateOrder(Order order) throws SQLException; 

      
} 