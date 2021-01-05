package service;

import model.Category;

public class CategoryCreator {
    public static final String TESTDATA_CATEGORY_NAME = "testdata.category.name";
    public static final String TESTDATA_CATEGORY_SORTING = "testdata.category.sorting";

    public Category withSorting(){
        return  new Category(TestDataReader.getTestData(TESTDATA_CATEGORY_NAME),
                TestDataReader.getTestData(TESTDATA_CATEGORY_SORTING));
    }

    public Category withNoSorting(){
        return  new Category(TestDataReader.getTestData(TESTDATA_CATEGORY_NAME));
    }
}
