package com.zaviron.web.servlet;

import com.google.gson.JsonObject;
import com.zaviron.web.data.AnalyseData;
import ejb.remote.Cart;
import ejb.remote.DataAnalysis;
import ejb.remote.Hello;
import ejb.remote.Iot;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import ejb.remote.DataAnalysis;
@WebServlet(name = "CartServlet",value = "/cart")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("avg_speed", 1);
        jsonObject.addProperty("traffic_pattern", 2);
        String json = jsonObject.toString();
        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }
    //    @Inject
//    private AnalyseTraffic analyseTraffic;
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        HttpSession httpSession = req.getSession();
//        DataAnalysis dataAnalysis= (DataAnalysis)httpSession.getAttribute("dataAnalysis-session");
//        if (dataAnalysis!=null){
//            double averageVehicleSpeed = dataAnalysis.getAverageVehicleSpeed();
//
//            JsonObject jsonObject = new JsonObject();
//            jsonObject.addProperty("avg_speed",averageVehicleSpeed);
//            jsonObject.addProperty("trafficPattern", 2);
//            jsonObject.addProperty("efficiency", 3);
//            String json = jsonObject.toString();
//            resp.setContentType("application/json");
//            resp.getWriter().write(json);
//            System.out.println(jsonObject+"json object");
//        }else {
//            // Handle the case when dataAnalysis is null
//            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            resp.getWriter().println("DataAnalysis object is null");
//        }
//
//
//
////
//   }
}
