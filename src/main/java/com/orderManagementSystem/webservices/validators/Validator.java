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

            String drink = orderRequest.getDrink();
            String size = orderRequest.getSize();
            String name = orderRequest.getName();

            if (drink == null || size == null || name == null) {
                return false;
            }

            drink = drink.toLowerCase().trim();
            size = size.toLowerCase().trim();
            name = name.trim();

            System.out.println("Available drinks: " + drinks);
            System.out.println("Available sizes: " + sizes);
            System.out.println("Received drink: " + drink);
            System.out.println("Received size: " + size);

            return drinks.contains(drink) && sizes.contains(size) && name.matches("[a-zA-Z]+");

        } catch (Exception e) {
            System.err.println("Validation error: " + e.getMessage());
            return false;
        }
    }
}