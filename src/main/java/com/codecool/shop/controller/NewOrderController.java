package com.codecool.shop.controller;

import com.codecool.shop.model.User;
import com.codecool.shop.model.order.BillingAddress;
import com.codecool.shop.model.order.Order;
import com.codecool.shop.model.order.OrderType;
import com.codecool.shop.model.order.ShippingAddress;
import com.codecool.shop.util.FileWriterLocal;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/new-order"})
public class NewOrderController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        new Order(
                req.getParameter("name"),
                req.getParameter("email"),
                req.getParameter("phone"),
                new BillingAddress(
                    req.getParameter("billing-country"),
                    req.getParameter("billing-city"),
                    req.getParameter("billing-zip"),
                    req.getParameter("billing-street")
                ),
                new ShippingAddress(
                        req.getParameter("shipping-country"),
                        req.getParameter("shipping-city"),
                        req.getParameter("shipping-zip"),
                        req.getParameter("shipping-street")
                )
        );
        User.getInstance().getNewOrder().setOrderType(OrderType.CHECKEDOUT);
        FileWriterLocal.saveAdminLog(User.getInstance().getNewOrder());
        resp.sendRedirect("/payment");
    }
}
