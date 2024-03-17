package com.zaviron.web.servlet;

import ejb.remote.DataAnalysis;
import ejb.remote.Iot;
import jakarta.jms.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;

@WebServlet(name = "AnalyseTraffic", value = "/traffic")
public class AnalyseTraffic extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataAnalysis dataAnalysis;
        try {
            InitialContext context = new InitialContext();
            QueueConnectionFactory factory = (QueueConnectionFactory) context.lookup("iotConnectionFactory");
            QueueConnection connection = factory.createQueueConnection();
            connection.start();

            QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = (Queue) context.lookup("myQueue");
            QueueReceiver receiver = session.createReceiver(queue);
            HttpSession httpSession = req.getSession();
            dataAnalysis = (DataAnalysis) httpSession.getAttribute("dataAnalysis-session");
            receiver.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        if (message instanceof MapMessage) {
                            MapMessage mapMessage = (MapMessage) message;
                            double vehicleSpeed = mapMessage.getDouble("vehicleSpeed");
                            boolean trafficLightStatus = mapMessage.getBoolean("trafficLightStatus");
                            double latitude = mapMessage.getDouble("latitude");
                            double longitude = mapMessage.getDouble("longitude");

                            // Process the received data
                            System.out.println("Received IoT data:");
                            System.out.println("Vehicle Speed: " + vehicleSpeed);
                            System.out.println("Traffic Light Status: " + trafficLightStatus);
                            System.out.println("Latitude: " + latitude);
                            System.out.println("Longitude: " + longitude);
                            //    totalVehicleSpeed.add(vehicleSpeed);

                            //   System.out.println( vehicleDataAnalysis.calculateAverageVehicleSpeed(totalVehicleSpeed));
                            double v = dataAnalysis.calculateAverageVehicleSpeed(vehicleSpeed);
                            String identifyTrafficPattern = dataAnalysis.identifyTrafficPattern(v);

                            System.out.println(v);
                            System.out.println(identifyTrafficPattern);
                            resp.getWriter().write((int) v);
                        }
                    } catch (JMSException e) {
                        System.err.println("Error processing message: " + e.getMessage());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }


                }
            });
            while (true) {
            }


        } catch (NamingException e) {
            throw new RuntimeException(e);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
