package service;

import model.Product;

public class ProductCreator {
    public static final String TESTDATA_PRODUCT_NAME="testdata.product.%s.name";
    public static final String TESTDATA_PRODUCT_URL="testdata.product.%s.url";
    public static final String TESTDATA_PRODUCT_QUANTITY="testdata.product.%s.quantity";

    public static Product withConfigQuantity(String productId){
        String productName = String.format(TESTDATA_PRODUCT_NAME, productId);
        String productUrl = String.format(TESTDATA_PRODUCT_URL, productId);
        String productQuantity = String.format(TESTDATA_PRODUCT_QUANTITY, productId);
        return new Product(TestDataReader.getTestData(productUrl), TestDataReader.getTestData(productName),
                Integer.parseInt(TestDataReader.getTestData(productQuantity)));
    }

    public static Product withSingleQuantity(String productId){
        String productName = String.format(TESTDATA_PRODUCT_NAME, productId);
        String productUrl = String.format(TESTDATA_PRODUCT_URL, productId);
        return new Product(TestDataReader.getTestData(productUrl), TestDataReader.getTestData(productName), 1);
    }
}
