package com.orderManagementSystem.services;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderManagementSystem.constants.OrderStatus;
import com.orderManagementSystem.dao.OrderDAO;
import com.orderManagementSystem.dao.exceptions.OrderSQLExecption;
import com.orderManagementSystem.dao.models.Order;
import com.orderManagementSystem.priceUtils.PriceCalculator;
import com.orderManagementSystem.webservices.models.OrderRequest;
import com.orderManagementSystem.webservices.models.OrderResponse;
import com.orderManagementSystem.webservices.validators.PaymentValidator;
import com.orderManagementSystem.webservices.validators.Validator;

@Service
public class OrderService {

    @Autowired
    private Validator validator;
    
    @Autowired
    private PaymentValidator paymentValidator;

    @Autowired
    private PriceCalculator priceCalculator;

    // Inject OrderDAO interface, which will use OrderDAORemoteDBImpl
    @Autowired
    private OrderDAO orderDao;

    private Long id = 1000L;

    // Method to create a new order
    public OrderResponse createOrder(OrderRequest request) throws OrderSQLExecption {
        Order order = new Order();
        OrderResponse orderResponse;
        
        if (validator.isValid(request)) {
            order.setStatus(OrderStatus.CREATED);
            order.setDrink(request.getDrink());
            order.setSize(request.getSize());
            order.setName(request.getName());
            order.setOrderId(generateOrderId());

            this.saveUsingDao(order);
        } else {
            order.setStatus(OrderStatus.DETAILS_ERROR);
        }

        orderResponse = createOrderResponse(order);
        return orderResponse;
    }

    public OrderResponse getOrder(Long orderId) {
        Order order = orderDao.getOrderById(orderId);

        if (order == null) {
            order.setStatus(OrderStatus.NO_ORDER_FOUND);
            order.setErrorDetails("No order found with id: " + orderId);
        }
        
        // Update order in the database after finding
        try {
            orderDao.updateOrder(order);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return createOrderResponse(order);
    }

    public OrderResponse paymentUpdateRequest(Long orderId, OrderRequest payment) {
        Order order = orderDao.getOrderById(orderId);

        if (!paymentValidator.isvalidpaymenttype(payment.getPaymenttype())) {
            order.setPaymentstatus(OrderStatus.PAYMENT_PENDING);
            order.setPaymenttype(OrderStatus.CHOOSE_A_DIFFERENT_TYPE);
        } else {
            order.setPaymenttype(payment.getPaymenttype());
            order.setPaymentstatus(OrderStatus.PAYMENT_PROCESSED);
            order.setStatus(OrderStatus.PREPARATION);
        }

        // Update the order in the database
        try {
            orderDao.updateOrder(order);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return createOrderResponse(order);
    }

    public OrderResponse updateOrderStatus(Long orderId, OrderRequest orderRequest) {
        Order order = orderDao.getOrderById(orderId);
        order.setStatus(orderRequest.getStatus());

        // Update the order in the database
        try {
            orderDao.updateOrder(order);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return createOrderResponse(order);
    }

    // Helper method to save order using DAO
    private void saveUsingDao(Order order) throws OrderSQLExecption {
        try {
            orderDao.insertOrder(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Generate new order ID
    private Long generateOrderId() {
        id++;
        return id;
    }

    // Helper method to convert Order to OrderResponse
    private OrderResponse createOrderResponse(Order order) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setStatus(order.getStatus());
        orderResponse.setOrderId(order.getOrderId());
        orderResponse.setDrink(order.getDrink());
        orderResponse.setSize(order.getSize());
        orderResponse.setName(order.getName());
        orderResponse.setTotalAmount(priceCalculator.calculateTotalPrice(order.getDrink(), order.getSize()));
        orderResponse.setPaymenttype(order.getPaymenttype());
        orderResponse.setPaymentstatus(order.getPaymentstatus());
        return orderResponse;
    }

    public OrderResponse orderstat(Long orderId, OrderRequest order) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'orderstat'");
    }
}
