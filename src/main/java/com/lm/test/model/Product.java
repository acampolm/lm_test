package com.lm.test.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Product {
    private static final String PRODUCT_FORMAT = "([0-9]+) ([a-zA-Z ]+) at ([0-9.]+)";
    private static final float IMPORTED_TAX = 0.05f;

    private int amount;
    private float price;
    private String productName;
    private boolean isImported;
    private ProductCategory category;
    private static Pattern pattern;

    /**
     * Generates a new product by parsing the client input
     * @param productLine The line from the client
     * @return A fully built product
     */
    public static Product generateProduct(String productLine) {
        if (pattern == null) {
            pattern = Pattern.compile(PRODUCT_FORMAT);
        }

        Matcher matcher = pattern.matcher(productLine);
        if (matcher.find()) {
            try {
                int amount = Integer.parseInt(matcher.group(1));
                float price = Float.parseFloat(matcher.group(3));

                return new Product(amount, matcher.group(2), price);
            } catch (NumberFormatException ex) {
                throw new IllegalArgumentException("The product " + productLine + " doesn't match the expected format", ex);
            }
        }

        throw new IllegalArgumentException("The product " + productLine + " doesn't match the expected format");
    }

    public Product(int amount, String productName, float price) {
        this.amount = amount;
        this.price = price;
        this.productName = productName;
        isImported = productName.toLowerCase().contains("imported");

        category = ProductCategory.typeForProduct(productName);
    }

    /**
     * get how many products are bought
     * @return the amount of products of that type
     */
    public int getAmount() {
        return amount;
    }

    /**
     * gets the product name
     * @return the product name
     */
    public String getProductName() {
        return productName;
    }

    /**
     * gets the product price (after applying taxes)
     * @return  the final price of the product
     */
    public float getFullPrice() {
        return price + getTaxes();
    }

    /**
     * gets the item price (without taxes)
     * @return the item price
     */
    public float getPrice() {
        return price;
    }

    /**
     * gets if the product is imported
     * @return if the product is imported
     */
    public boolean isImported() {
        return isImported;
    }

    /**
     * the taxes applied to a single unit the product
     * @return the taxes applied
     */
    public float getTaxes() {
        float taxes = roundTax(price * category.getTaxRate());
        if (isImported) {
            taxes += roundTax(price * IMPORTED_TAX);
        }

        return taxes;
    }

    private float roundTax(float taxAmount) {
        int taxRounded = (int) (taxAmount * 100);
        int taxDifference = (taxRounded % 5);
        taxRounded = (taxDifference > 0) ? (taxRounded + (5 - taxDifference)) : taxRounded;

        return (float) taxRounded / 100.0f;
    }
}
