package ejb.message;

import ejb.impl.VehicleDataAnalysis;
import ejb.remote.DataAnalysis;
import ejb.remote.Iot;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.MapMessage;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.List;

@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "myQueue")
        }
)
public class MessageReceiver implements MessageListener {

    List<Double> totalVehicleSpeed = new ArrayList<>();
    InitialContext context;
    DataAnalysis dataAnalysis;

    {
        try {
            context = new InitialContext();
            dataAnalysis=(DataAnalysis) context.lookup("java:global/ear-1.0/com.zaviron-ejb-1.0/VehicleDataAnalysis");

        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }



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
                totalVehicleSpeed.add(vehicleSpeed);

                 Double averageVehicleSpeed= dataAnalysis.calculateAverageVehicleSpeed(vehicleSpeed);
                 dataAnalysis.identifyTrafficPattern(averageVehicleSpeed);
                 dataAnalysis.trafficFlowAnalyse(trafficLightStatus,longitude,latitude,vehicleSpeed);
                 dataAnalysis.mobilityEfficiency(trafficLightStatus,vehicleSpeed);
                System.out.println(averageVehicleSpeed);
                System.out.println(dataAnalysis.getIdentifyTrafficPattern());

            }
        } catch (JMSException e) {
            System.err.println("Error processing message: " + e.getMessage());
        }

    }


}