package com.codecool.shop.service;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ProductServiceTest {
    public ProductDaoMem productDao;
    public ProductCategoryDaoMem productCategoryDao;
    public SupplierDao productSupplierDao;
    public ProductService ps;
    Supplier amazon = new Supplier("Amazon", "Digital content and services");
    Supplier lenovo = new Supplier("Lenovo", "Computers");
    Supplier apple = new Supplier("Apple", "Cellphones");

    ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
    ProductCategory drones = new ProductCategory("Drones", "Hardware", "Drones.");
    ProductCategory cellphone = new ProductCategory("Cellphones", "Hardware", "Mobile phones.");

    @Before
    public void setUp() {
        productDao = Mockito.mock(ProductDaoMem.class);
        productCategoryDao = Mockito.mock(ProductCategoryDaoMem.class);
        productSupplierDao = Mockito.mock(SupplierDaoMem.class);
        ps = new ProductService(productDao, productCategoryDao, productSupplierDao);
    }

    @Test
    public void getProductCategory() {
        ProductCategory expected = new ProductCategory("tablets", "tech", "tablets");
        when(productCategoryDao.find(5)).thenReturn(expected);
        assertEquals(expected, ps.getProductCategory(5));
        verify(productCategoryDao).find(5);
    }

    @Test
    public void getProductsForCategory() {
        List<Product> expected = new ArrayList<>();
        expected.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        expected.add(new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        expected.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));

        ProductCategory pc = new ProductCategory("tablets", "tech", "tablets");
        when(productCategoryDao.find(5)).thenReturn(pc);
        when(productDao.getBy(pc)).thenReturn(expected);
        assertEquals(expected, productDao.getBy(pc));
        verify(productDao).getBy(pc);
    }
//
    @Test
    public void getAllCategories() {
        ArrayList<ProductCategory> expectedCategories = new ArrayList<>();
        expectedCategories.add(tablet);
        expectedCategories.add(cellphone);
        when(productCategoryDao.getAll()).thenReturn(expectedCategories);
        assertEquals(expectedCategories, productCategoryDao.getAll());
        verify(productCategoryDao).getAll();
    }
//
    @Test
    public void getSupplierName() {
        when(productSupplierDao.find(1)).thenReturn(amazon);
        assertEquals(amazon, productSupplierDao.find(1));
        verify(productSupplierDao).find(1);
    }
//
    @Test
    public void getProductsForSupplier() {
        List<Product> expected = new ArrayList<>();
        expected.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        expected.add(new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        when(productDao.getBy(tablet)).thenReturn(expected);
        assertEquals(productDao.getBy(tablet), expected);
        verify(productDao).getBy(tablet);
    }

    @Test
    public void getAllSuppliers() {
        ArrayList<Supplier> expectedSuppliers = new ArrayList<>();
        expectedSuppliers.add(apple);
        expectedSuppliers.add(amazon);
        when(productSupplierDao.getAll()).thenReturn(expectedSuppliers);
        assertEquals(expectedSuppliers, productSupplierDao.getAll());
        verify(productSupplierDao).getAll();
    }

    @Test
    public void getProduct() {
        Product expected = new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon);
        when(productDao.find(1)).thenReturn(expected);
        assertEquals(expected, productDao.find(1));
        verify(productDao).find(1);
    }
}