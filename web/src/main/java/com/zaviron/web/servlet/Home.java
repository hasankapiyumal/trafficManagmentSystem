package com.zaviron.web.servlet;

import ejb.remote.DataAnalysis;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;

@WebServlet(name = "Home",value = "/home")
public class Home extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataAnalysis dataAnalysis;
        try {
            InitialContext context =new InitialContext();
            HttpSession httpSession = req.getSession();
            if (httpSession.getAttribute("dataAnalysis-session")==null){
                dataAnalysis =(DataAnalysis) context.lookup("java:global/ear-1.0/com.zaviron-ejb-1.0/VehicleDataAnalysis");
                httpSession.setAttribute("dataAnalysis-session",dataAnalysis);

            }else {
                dataAnalysis =(DataAnalysis) httpSession.getAttribute("dataAnalysis-session");
            }
            resp.sendRedirect("traffic");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}
