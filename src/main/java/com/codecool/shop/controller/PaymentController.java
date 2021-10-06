package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.Payment;
import com.codecool.shop.model.User;
import com.codecool.shop.model.order.Order;
import com.codecool.shop.model.order.OrderType;
import com.codecool.shop.util.FileWriterLocal;
import com.codecool.shop.util.EmailSender;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(urlPatterns = {"/payment"})
public class PaymentController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("userName", User.getInstance().getName());
        context.setVariable("cartSum", User.getInstance().cartSum());
        context.setVariable("totalPrice", User.getInstance().cartSumPrice());
        engine.process("product/payment.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        String isError = "";
        try {
            String paymentType = req.getParameter("payment-type");
            isError = req.getParameter("error-checkbox") != null ? "true" : "false";
            if (isError.equals("true")) {
                User.getInstance().getNewOrder().setOrderType(OrderType.FAILED);
            } else {
                User.getInstance().getNewOrder().setOrderType(OrderType.PAID);
            }
            Payment payment;
            if (paymentType.equals("credit-card")) {
                payment = new Payment(req.getParameter("cardNumber"),
                                      req.getParameter("CVS"),
                                      req.getParameter("expirationDate"),
                                      req.getParameter("cardOwnerName"));
            } else {
                payment = new Payment(req.getParameter("email"),
                                      req.getParameter("password"));
            }
            User.getInstance().getNewOrder().setPayment(payment);
            context.setVariable("orderDetails", User.getInstance().getNewOrder());
            context.setVariable("total", User.getInstance().cartSumPrice());
            // if (isError.equals("false")) EmailSender.sendEmail(User.getInstance().getNewOrder());
        }  catch (Exception ex) {
            User.getInstance().getNewOrder().setOrderType(OrderType.FAILED);
            isError = "true";
            System.out.println(ex);
        } finally {
            try {
                FileWriterLocal.serializeObj(User.getInstance().getNewOrder());
                FileWriterLocal.saveAdminLog(User.getInstance().getNewOrder());
            } catch (NullPointerException exe) {
                User.getInstance().setNewOrder(new Order(OrderType.FAILED));
                isError = "true";
            } finally {
                User.getInstance().setShoppingCart(new HashMap<>());
                context.setVariable("isError", isError);
                engine.process("product/confirmation-page.html", context, resp.getWriter());
            }
        }

    }
}
