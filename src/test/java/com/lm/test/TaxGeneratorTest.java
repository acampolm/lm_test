package com.lm.test;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TaxGeneratorTest extends TestCase {
    public static final String ANY_PRODUCT = "1 product at 1";
    public static final String BAD_PRODUCT = "this is wrooong!!";

    private TaxGenerator generator;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        generator = new TaxGenerator();
    }

    @Test
    public void testAddProduct() {
        generator.addProduct(ANY_PRODUCT);
        assertEquals(generator.getProcessedProducts(), 1);
    }

    @Test
    public void testBadItemLine() {
        try {
            generator.addProduct(BAD_PRODUCT);
            assertTrue("this shouldn't be executed", false);
        } catch (IllegalArgumentException ex) {
            assertEquals("Bad message received", "The product " + BAD_PRODUCT + " doesn't match the expected format", ex.getMessage());
        }
    }

    @Test
    public void testOutput() {
        generator.addProduct(ANY_PRODUCT);
        List<String> expectation = new ArrayList<>();
        expectation.add(String.format(TaxGenerator.PRODUCT_LINE_FORMAT, 1, "product", 1.1f));
        expectation.add(String.format(TaxGenerator.TAXES_LINE_FORMAT, 0.1f));
        expectation.add(String.format(TaxGenerator.TOTAL_LINE_FORMAT, 1.1f));

        List<String> bill = generator.generateBill();

        assertEquals(expectation.size(), bill.size());

        for (int i = 0; i < bill.size(); ++i) {
            assertEquals(expectation.get(i), bill.get(i));
        }
    }
}
