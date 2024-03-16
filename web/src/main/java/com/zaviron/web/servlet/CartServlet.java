package com.zaviron.web.servlet;

import ejb.remote.Cart;
import ejb.remote.Hello;
import ejb.remote.Iot;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
@WebServlet(name = "CartServlet",value = "/cart")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
//            InitialContext initialContext =new InitialContext();
//       Cart cart=(Cart) initialContext.lookup("java:global/ear-1.0/com.zaviron-ejb-1.0/CartBean");
//
//           resp.getWriter().write(cart.getDetails());
            InitialContext initialContext =new InitialContext();
//            Hello hello=(Hello)initialContext.lookup("java:global/IotDevice-1.0/HelloBean");
//            hello.getDetails();
            Iot iot =(Iot) initialContext.lookup("java:global/IotDevice-1.0/IotBean");


        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
//
   }
}
