package com.lm.test;

import com.lm.test.model.Product;

import java.util.ArrayList;
import java.util.List;

public class TaxGenerator {
    public static final String TAXES_LINE_FORMAT = "Sales Taxes: %.2f";
    public static final String TOTAL_LINE_FORMAT = "Total: %.2f";
    public static final String PRODUCT_LINE_FORMAT = "%d %s: %.2f";
    private List<Product> products;

    public TaxGenerator() {
        products = new ArrayList<>();
    }

    /**
     * Adds a product from input to the bill
     * @param productLine String Input from the client
     * @throws IllegalArgumentException if there is a problem parsing client's input
     */
    public void addProduct(String productLine) {
        Product product = Product.generateProduct(productLine);
        products.add(product);
    }

    /**
     * returns the number of products already processed
     * @return the amount of items in the list
     */
    public int getProcessedProducts() {
        return products.size();
    }

    /**
     * generates the bill strings to be printed
     * @return A list of strings with the bill info
     */
    public List<String> generateBill() {
        float totalTaxes = 0;
        float totalPrice = 0;
        List<String> bill = new ArrayList<>();
        for(Product p : products) {
            String product = String.format(PRODUCT_LINE_FORMAT, p.getAmount(), p.getProductName(), p.getFullPrice());
            bill.add(product);

            totalPrice += p.getAmount() * p.getFullPrice();
            totalTaxes += p.getAmount() * p.getTaxes();
        }

        String taxString = String.format(TAXES_LINE_FORMAT, totalTaxes);
        String finalCost = String.format(TOTAL_LINE_FORMAT, totalPrice);
        bill.add(taxString);
        bill.add(finalCost);

        return bill;
    }
}
