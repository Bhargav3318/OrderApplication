package com.orderManagementSystem.webservices.validators;

import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.orderManagementSystem.webservices.models.OrderRequest;

import org.springframework.beans.factory.annotation.Autowired;

@Component
public class Validator {

    private List<String> drinks;
    private List<String> sizes;

    @Autowired
    public Validator(@Value("#{'${drinks}'.split(',')}") List<String> drinks,
            @Value("#{'${sizes}'.split(',')}") List<String> sizes) {
        this.drinks = drinks;
        this.sizes = sizes;
    }

    public boolean isValid(OrderRequest orderRequest) {
        try {
            if (orderRequest == null) {
                return false;
            }

            List<String> drink = orderRequest.getDrink();
            List<String> size = orderRequest.getSize();
            String name = orderRequest.getName();

            if (drink == null || size == null || name == null || drinks.size() != sizes.size() || drinks.isEmpty() || sizes.isEmpty()) {
                return false;
            }

            for (int i = 0; i < drinks.size(); i++) {
                String d = drinks.get(i).toLowerCase().trim();
                String s = sizes.get(i).toLowerCase().trim();

                if (!drinks.contains(d)) {
                    return false;
                }

                if (!sizes.contains(s)) {
                    return false;
                }
            }

            if (!name.trim().matches("[a-zA-Z]+")) {
                return false;
            }

            System.out.println("Available drinks: " + drinks);
            System.out.println("Available sizes: " + sizes);
            System.out.println("Received drink: " + drink);
            System.out.println("Received size: " + size);

            return true;

        } catch (Exception e) {
            System.err.println("Validation error: " + e.getMessage());
            return false;
        }
    }
}