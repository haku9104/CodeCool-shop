package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.User;
import com.codecool.shop.service.ProductService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/user-details")
public class UserDetailsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductService productService = new ProductService();
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("allCategories", productService.getAllCategories());
        context.setVariable("allSuppliers", productService.getAllSuppliers());
        context.setVariable("cartSum", User.getInstance().cartSum());
        context.setVariable("user", User.getInstance());
        context.setVariable("dataChanged", User.getInstance().isDataChanged());

        User.getInstance().setDataChanged(false);

        engine.process("product/user-details.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = User.getInstance();

        user.setFullName(req.getParameter("full-name"));
        user.setEmail(req.getParameter("email"));
        user.setPhone(req.getParameter("phone"));

        user.setBilling_street(req.getParameter("billing_street"));
        user.setBilling_city(req.getParameter("billing_city"));
        user.setBilling_country(req.getParameter("billing_country"));
        user.setBilling_zip(req.getParameter("billing_zip"));

        user.setShipping_street(req.getParameter("shipping_street"));
        user.setShipping_city(req.getParameter("shipping_city"));
        user.setShipping_country(req.getParameter("shipping_country"));
        user.setShipping_zip(req.getParameter("shipping_zip"));

        user.setDataChanged(true);

        resp.sendRedirect("/user-details");
    }
}
