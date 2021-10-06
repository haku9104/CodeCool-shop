package com.codecool.shop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.dao.implementation.UserDaoJdbc;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class ProductService{
    private ProductDao productDao;
    private ProductCategoryDao productCategoryDao;
    private SupplierDao productSupplierDao;
    private UserDao userDao;

    public ProductService() {
//        setup();
        this.productDao = ProductDaoMem.getInstance();
        this.productCategoryDao = ProductCategoryDaoMem.getInstance();
        this.productSupplierDao = SupplierDaoMem.getInstance();
    }

    public ProductService(ProductDao productDao, ProductCategoryDao productCategoryDao, SupplierDao productSupplierDao) {
        this.productDao = productDao;
        this.productCategoryDao = productCategoryDao;
        this.productSupplierDao = productSupplierDao;
    }

//    public void setup() {
//        try {
//            DataSource dataSource = connect();
//            userDao = new UserDaoJdbc(dataSource);
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        }
//    }

    public ProductCategory getProductCategory(int categoryId){
        return productCategoryDao.find(categoryId);
    }

    public List<Product> getProductsForCategory(int categoryId){
        var category = productCategoryDao.find(categoryId);
        return productDao.getBy(category);
    }

    public List<ProductCategory> getAllCategories() {
        return productCategoryDao.getAll();
    }

    public Supplier getSupplierName(int supplierId) {
        return productSupplierDao.find(supplierId);
    }

    public List<Product> getProductsForSupplier(int supplierId) {
        var supplier = productSupplierDao.find(supplierId);
        return productDao.getBy(supplier);
    }

    public List<Supplier> getAllSuppliers() {
        return productSupplierDao.getAll();
    }

    public Product getProduct(int productId) {
        return productDao.find(productId);
    }

//    public DataSource connect() throws SQLException {
//        PGSimpleDataSource dataSource = new PGSimpleDataSource();
//        String dbName = System.getenv("PSQL_DB_NAME");
//        String user = System.getenv("PSQL_USER_NAME");
//        String password = System.getenv("PSQL_PASSWORD");
//
//        dataSource.setDatabaseName(dbName);
//        dataSource.setUser(user);
//        dataSource.setPassword(password);
//
//        System.out.println("Trying to connect");
//        dataSource.getConnection().close();
//        System.out.println("Connection ok.");
//
//        return dataSource;
//    }
}
