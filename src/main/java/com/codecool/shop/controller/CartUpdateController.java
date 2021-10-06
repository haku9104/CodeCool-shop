package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.User;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns = {"/cart/update"})
public class CartUpdateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productId, quantity;
        try {
            productId = Integer.parseInt(req.getParameter("productid"));
            quantity = Integer.parseInt(req.getParameter("quantity"));
        } catch (Exception ex) {
            System.out.println(ex);
            resp.sendRedirect("/cart");
            return;
        }
        try {
            User.getInstance().updateCart(productId, quantity);
        } catch (ConcurrentModificationException ex) {
            System.out.println(ex.getStackTrace());
        }
        resp.sendRedirect("/cart");
    }
}