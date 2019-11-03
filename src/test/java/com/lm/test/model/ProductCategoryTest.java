package com.lm.test.model;

import junit.framework.TestCase;
import org.junit.Test;

public class ProductCategoryTest extends TestCase {

    @Test
    public void testExitentCateogry() {
        ProductCategory cat = ProductCategory.typeForProduct("book");
        assertEquals("A book category should be returned", cat, ProductCategory.BOOK);

        cat = ProductCategory.typeForProduct("chocolate");
        assertEquals("A food category should be returned", cat, ProductCategory.FOOD);

        cat = ProductCategory.typeForProduct("pill");
        assertEquals("A medical category should be returned", cat, ProductCategory.MEDICAL);
    }

    @Test
    public void testUnspecifiedCategory() {
        ProductCategory cat = ProductCategory.typeForProduct("perfume");
        assertEquals("Other category should be returned", cat, ProductCategory.OTHER);

        cat = ProductCategory.typeForProduct("other");
        assertEquals("Other category should be returned", cat, ProductCategory.OTHER);
    }
}
