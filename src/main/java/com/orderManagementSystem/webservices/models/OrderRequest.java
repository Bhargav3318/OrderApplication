package com.orderManagementSystem.webservices.models;
import java.util.List;
import com.orderManagementSystem.dao.models.Order;

public class OrderRequest extends Order{

    private String status;
    private Long orderId;
    private Double totalAmount;
    private String errorDetails;
    private List<String> drink;
    private List<String> size;
    private String name;
    private String paymenttype;
    private String paymentstatus;

    
    public String getPaymentstatus() {
        return paymentstatus;
    }

    public void setPaymentstatus(String paymentstatus) {
        this.paymentstatus = paymentstatus;


    }
    public String getPaymenttype() {
        return paymenttype;
    }

    public void setPaymenttype(String paymenttype) {
        this.paymenttype = paymenttype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSize() {
        return size;
    }

    public void setSize(List<String> size) {
        this.size = size;
    }



    public List<String> getDrink() {
        return drink;
    }

    public void setDrink(List<String> drink) {
        this.drink = drink;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

   

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(String errorDetails) {
        this.errorDetails = errorDetails;
    }

}
