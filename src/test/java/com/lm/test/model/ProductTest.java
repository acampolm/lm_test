package com.lm.test.model;

import junit.framework.TestCase;
import org.junit.Test;

public class ProductTest extends TestCase
{
    public static final String GOOD_ITEM_LINE = "1 product name at 12.34";
    public static final String GOOD_IMPORTED_ITEM_LINE = "1 imported product at 12.34";
    public static final String ONLY_BASIC_TAX_PRODUCT = "1 product name at 1";
    public static final String ONLY_IMPORTED_TAX_PRODUCT = "1 imported book at 1";
    public static final String ONLY_COMBINED_TAX_PRODUCT = "1 imported other at 1";
    public static final String MULTIPLE_COMBINED_TAX_PRODUCT = "2 imported other at 1";
    public static final String BAD_ITEM_LINE = "THIS IS WROOOOONG!";
    public static final String BAD_PRICE_LINE = "1 product at none";
    public static final String BAD_AMOUNT_LINE = "none product at 12.34";

    @Test
    public void testGoodLineParsing() {
        Product p = Product.generateProduct(GOOD_ITEM_LINE);

        assertEquals("Only 1 item was expected",1, p.getAmount());
        assertEquals("Product name should be 'product name' ","product name", p.getProductName());
        assertEquals("Price should be 12.34",12.34f, p.getPrice(), 0.001f);
        assertFalse("the product isn't imported", p.isImported());
    }

    @Test
    public void testImportedProduct() {
        Product p = Product.generateProduct(GOOD_IMPORTED_ITEM_LINE);

        assertEquals("Only 1 item was expected",1, p.getAmount());
        assertEquals("Product name should be 'product name' ","imported product", p.getProductName());
        assertEquals("Price should be 12.34",12.34f, p.getPrice(), 0.001f);
        assertTrue("the product is imported", p.isImported());
    }

    @Test
    public void testBadItemLine() {
        try {
            Product.generateProduct(BAD_ITEM_LINE);
            assertTrue("this shouldn't be executed", false);
        } catch (IllegalArgumentException ex) {
            assertEquals("Bad message received", "The product " + BAD_ITEM_LINE + " doesn't match the expected format", ex.getMessage());
        }
    }

    @Test
    public void testBadItemPrice() {
        try {
            Product.generateProduct(BAD_PRICE_LINE);
            assertTrue("this shouldn't be executed", false);
        } catch (IllegalArgumentException ex) {
            assertEquals("Bad message received", "The product " + BAD_PRICE_LINE + " doesn't match the expected format", ex.getMessage());
        }
    }

    @Test
    public void testBadItemAmount() {
        try {
            Product.generateProduct(BAD_AMOUNT_LINE);
            assertTrue("this shouldn't be executed", false);
        } catch (IllegalArgumentException ex) {
            assertEquals("Bad message received", "The product " + BAD_AMOUNT_LINE + " doesn't match the expected format", ex.getMessage());
        }
    }

    @Test
    public void testBasicTax() {
        Product p = Product.generateProduct(ONLY_BASIC_TAX_PRODUCT);

        assertEquals("Only 1 item was expected",1, p.getAmount());
        assertEquals("Product name should be 'product name' ","product name", p.getProductName());
        assertEquals("Price should be 1",1.00f, p.getPrice(), 0.001f);
        assertFalse("the product isn't imported", p.isImported());

        assertEquals("expected taxes for product 0.1", 0.1f, p.getTaxes());
        assertEquals("expected final cost for product 1.1", 1.1f, p.getFullPrice());
    }

    @Test
    public void testImportedTax() {
        Product p = Product.generateProduct(ONLY_IMPORTED_TAX_PRODUCT);

        assertEquals("Only 1 item was expected",1, p.getAmount());
        assertEquals("Product name should be 'product name' ","imported book", p.getProductName());
        assertEquals("Price should be 1",1.00f, p.getPrice(), 0.001f);
        assertTrue("the product is imported", p.isImported());

        assertEquals("expected taxes for product 0.05", 0.05f, p.getTaxes());
        assertEquals("expected final cost for product 1.05", 1.05f, p.getFullPrice());
    }

    @Test
    public void testCombinedTax() {
        Product p = Product.generateProduct(ONLY_COMBINED_TAX_PRODUCT);

        assertEquals("Only 1 item was expected",1, p.getAmount());
        assertEquals("Product name should be 'product name' ","imported other", p.getProductName());
        assertEquals("Price should be 1",1.00f, p.getPrice(), 0.001f);
        assertTrue("the product is imported", p.isImported());

        assertEquals("expected taxes for product 0.15", 0.15f, p.getTaxes());
        assertEquals("expected final cost for product 1.15", 1.15f, p.getFullPrice());
    }

    @Test
    public void testMultiProduct() {
        Product p = Product.generateProduct(MULTIPLE_COMBINED_TAX_PRODUCT);

        assertEquals("Only 2 item was expected",2, p.getAmount());
        assertEquals("Product name should be 'product name' ","imported other", p.getProductName());
        assertEquals("Price should be 1",1.00f, p.getPrice(), 0.001f);
        assertTrue("the product is imported", p.isImported());

        assertEquals("expected taxes for product 0.15", 0.15f, p.getTaxes());
        assertEquals("expected final cost for product 1.15", 1.15f, p.getFullPrice());
    }
}
