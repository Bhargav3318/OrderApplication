
package com.orderManagementSystem.priceUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class PriceCalculator {

    @Value("#{${drink.tea.prices}}")
    private Map<String, Double> teaPrices;

    @Value("#{${drink.coffee.prices}}")
    private Map<String, Double> coffeePrices;

    @Value("#{${drink.lemonade.prices}}")
    private Map<String, Double> lemonadePrices;

    public double calculatePrice(String drink, String size) {
        Map<String, Double> prices;
        if ("tea".equalsIgnoreCase(drink)) {
            prices = teaPrices;
        } else if ("coffee".equalsIgnoreCase(drink)) {
            prices = coffeePrices;
        } else if ("lemonade".equalsIgnoreCase(drink)) {
            prices = lemonadePrices;
        } else {
            throw new IllegalArgumentException("Unknown drink type: " + drink);
        }

        Double price = prices.get(size.toLowerCase());
        return price;
    }
}