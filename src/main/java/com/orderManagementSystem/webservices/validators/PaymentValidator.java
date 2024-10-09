package com.orderManagementSystem.webservices.validators;

import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;


@Component
public class PaymentValidator {
    private static List<String> paymenttypes;

    @Autowired
    public PaymentValidator( @Value("#{'${paymenttypes}'.split(',')}") List<String> paymenttypes) {
        PaymentValidator.paymenttypes=paymenttypes;
    }

    public boolean isvalidpaymenttype(String paymenttype) {
        try {
            if (paymenttype == null) {
                return false;
            }

            paymenttype = paymenttype.toLowerCase().trim();

            
            return paymenttypes.contains(paymenttype);
                   
        } catch (Exception e) {
            System.err.println("Validation error: " + e.getMessage());
            return false;
        }
    }

    }


    


