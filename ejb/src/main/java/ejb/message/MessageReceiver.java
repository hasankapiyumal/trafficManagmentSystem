package ejb.message;

import ejb.impl.VehicleDataAnalysis;
import ejb.remote.Iot;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.MapMessage;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;

import javax.naming.InitialContext;
import java.util.ArrayList;
import java.util.List;

//@MessageDriven(
//        activationConfig = {
//                @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "myQueue")
//        }
//)
public class MessageReceiver implements MessageListener {

    List<Double> totalVehicleSpeed = new ArrayList<>();
    VehicleDataAnalysis vehicleDataAnalysis = new VehicleDataAnalysis();

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

             //   System.out.println( vehicleDataAnalysis.calculateAverageVehicleSpeed(totalVehicleSpeed));
            }
        } catch (JMSException e) {
            System.err.println("Error processing message: " + e.getMessage());
        }

    }


}
