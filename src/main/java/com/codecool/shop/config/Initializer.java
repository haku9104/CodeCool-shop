package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);
        Supplier apple = new Supplier("Apple", "Cellphones");
        supplierDataStore.add(apple);
        Supplier DJI = new Supplier("DJI", "Televisions");
        supplierDataStore.add(DJI);

        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        ProductCategory drones = new ProductCategory("Drones", "Hardware", "Drones.");
        productCategoryDataStore.add(drones);
        ProductCategory cellphone = new ProductCategory("Cellphones", "Hardware", "Mobile phones.");
        productCategoryDataStore.add(cellphone);

        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        productDataStore.add(new Product("Iphone 12", 899, "USD", "The iPhone 12 and iPhone 12 mini are Apple's mainstream flagship iPhones for 2020.", cellphone, apple));
        productDataStore.add(new Product("Iphone 11 PRO", 779, "USD", "iPhone 11 Pro smartphone was launched on 10th September 2019.", cellphone, apple));
        productDataStore.add(new Product("DJI mavic pro", 499, "USD", "The DJI Mavic Pro is a portable and powerful drone with a 3-axis gimbal 4K camera, a max transmission range of 4.1 mi (7 km), and a sophisticated design", drones, DJI));
        productDataStore.add(new Product("DJI mini 2", 399, "USD", "The DJI Mini 2 is pilot-friendly, powerful and tiny at the same time. Impressive performance, stunning image quality and creative videos are just a few choices away.", drones, DJI));

    }
}
