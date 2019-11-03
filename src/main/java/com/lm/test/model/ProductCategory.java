package com.lm.test.model;

public enum ProductCategory {
    BOOK(0, "book"),
    FOOD(0,  "chocolate"),
    MEDICAL(0,  "pill"),
    OTHER(0.1f,  null);

    private float taxRate;
    private String word;

    ProductCategory(float rate, String word) {
        this.taxRate = rate;
        this.word = word;
    }

    /**
     * Returns the tax rate for the product category
     * @return the tax rate
     */
    public float getTaxRate() {
        return taxRate;
    }

    /**
     * Gets the corresponding category for the product by parsing the product name. (for a bigger input sample, we may need to use a dictionary instead of just a word)
     * @param productName the product name parsed from the client input
     * @return the corresponding category for the product. If none matches OTHER will be returned
     */
    public static ProductCategory typeForProduct(String productName) {
        String[] words = productName.split(" ");

        for (String word : words) {
            for (ProductCategory pt : values()) {
                if (pt.word != null && word.toLowerCase().contains(pt.word)) {
                    return pt;
                }
            }
        }

        return ProductCategory.OTHER;
    }
}