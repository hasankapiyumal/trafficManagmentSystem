package com.zaviron.web.servlet;

import com.google.gson.JsonObject;
import com.zaviron.web.data.AnalyseData;
import ejb.remote.DataAnalysis;
import ejb.remote.Iot;
import jakarta.jms.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AnalyseTraffic", value = "/traffic")
public class AnalyseTraffic extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataAnalysis dataAnalysis;
        Context context;
        try {
            context = new InitialContext();
            dataAnalysis=(DataAnalysis) context.lookup("java:global/ear-1.0/com.zaviron-ejb-1.0/VehicleDataAnalysis");
        //    resp.getWriter().write(String.valueOf(dataAnalysis.getAverageVehicleSpeed()));
         //   resp.getWriter().write(dataAnalysis.getIdentifyTrafficPattern());
            JsonObject jsonObject =new JsonObject();
            jsonObject.addProperty("averageSpeed",dataAnalysis.getAverageVehicleSpeed());
            jsonObject.addProperty("trafficPattern",dataAnalysis.getIdentifyTrafficPattern());
            String json = jsonObject.toString();
            resp.setContentType("application/json");
            resp.getWriter().write(json);

        } catch (NamingException e) {
            throw new RuntimeException(e);
        }

    }
}
