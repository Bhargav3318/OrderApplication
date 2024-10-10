
package com.orderManagementSystem.priceUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.orderManagementSystem.webservices.models.List;

import java.util.Map;
@Component
public class PriceCalculator {

    @Value("#{${drink.tea.prices}}")
    private Map<String, Double> teaPrices;

    @Value("#{${drink.coffee.prices}}")
    private Map<String, Double> coffeePrices;

    @Value("#{${drink.lemonade.prices}}")
    private Map<String, Double> lemonadePrices;

    public double calculateTotalPrice(java.util.List<String> list, java.util.List<String> list2) {
        double totalPrice = 0.0;
        
        for (int i = 0; i < list.size(); i++) {
            String drink = list.get(i).toLowerCase().trim();
            String size = list2.get(i).toLowerCase().trim();
            
            Map<String, Double> prices;
            if ("tea".equals(drink)) {
                prices = teaPrices;
            } else if ("coffee".equals(drink)) {
                prices = coffeePrices;
            } else if ("lemonade".equals(drink)) {
                prices = lemonadePrices;
            } else {
                throw new IllegalArgumentException("Unknown drink type: " + drink);
            }

            Double price = prices.get(size);
            if (price != null) {
                totalPrice += price;
            } else {
                throw new IllegalArgumentException("Unknown size for " + drink + ": " + size);
            }
        }
        
        return totalPrice;
    }


}
