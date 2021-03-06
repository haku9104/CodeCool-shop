package com.codecool.shop.controller.login;

import com.codecool.shop.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns={"/login"})
public class LoginController extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("name");
        String password = req.getParameter("password");

        if(username.equals("name") && password.equals("password")) {
            HttpSession session = req.getSession();
            session.setAttribute("name", username);
        } else {
            User.getInstance().setLoginError(true);
        }

        resp.sendRedirect(req.getHeader("referer"));
    }
}