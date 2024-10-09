package com.orderManagementSystem.webservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.orderManagementSystem.constants.OrderOperations;
import com.orderManagementSystem.dao.exceptions.OrderSQLExecption;
import com.orderManagementSystem.services.OrderService;
import com.orderManagementSystem.webservices.models.OrderRequest;
import com.orderManagementSystem.webservices.models.OrderResponse;
import com.orderManagementSystem.webservices.validators.OrderSequenceEnforcer;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderSequenceEnforcer protector;

    @PostMapping("/")
    public OrderResponse createOrder(@RequestBody OrderRequest orderRequest) {
        if (protector.isValidOperationOnOrder(null, OrderOperations.CREATE_ORDER)) {
            try {
                return orderService.createOrder(orderRequest);
            } catch (OrderSQLExecption e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return generateOperationNotAllowedOnRequestError();
    }

    @GetMapping("/{orderId}")
    public OrderResponse retrieveOrder(@PathVariable("orderId") Long orderId) {
        if (protector.isValidOperationOnOrder(orderService.getOrder(orderId), OrderOperations.RETRIEVE_ORDER)) {
            return orderService.getOrder(orderId);
        }
        return generateOperationNotAllowedOnRequestError();
    }

    @PutMapping("/{orderId}/payment")
    public OrderResponse updatePayment(@PathVariable("orderId") Long orderId, @RequestBody OrderRequest orderRequest) {
        if (protector.isValidOperationOnOrder(orderService.getOrder(orderId), OrderOperations.ADD_PAYMENT_DETAILS)) {
            return orderService.paymentUpdateRequest(orderId, orderRequest);
        }
        return generateOperationNotAllowedOnRequestError();
    }

    @PutMapping("/{orderId}/preparation")
    public OrderResponse updateStatus(@PathVariable("orderId") Long orderId, @RequestBody OrderRequest order) {
        if (protector.isValidOperationOnOrder(orderService.getOrder(orderId), OrderOperations.PREPARATION)) {
            return orderService.orderstat(orderId, order);
        }
        return generateOperationNotAllowedOnRequestError();
    }

    private OrderResponse generateOperationNotAllowedOnRequestError() {
        OrderResponse errorResponse = new OrderResponse();
        errorResponse.setErrorDetails("INVALID_OPERATION_ON_ORDER");
        return errorResponse;
    }
}
