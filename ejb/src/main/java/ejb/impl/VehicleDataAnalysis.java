package ejb.impl;

import ejb.remote.DataAnalysis;
import jakarta.ejb.Singleton;
import jakarta.ejb.Stateless;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class VehicleDataAnalysis implements DataAnalysis{
    private  List<Double> vehicleSpeed = new ArrayList<>();
    private String trafficPattern ;

    @Override
    public double calculateAverageVehicleSpeed(Double vehicleSpeed) {

        this.vehicleSpeed.add(vehicleSpeed);
        double avgVehicleSpeed=0;
        for (Double speed:this.vehicleSpeed){
            avgVehicleSpeed +=speed/this.vehicleSpeed.size();
        }

        return avgVehicleSpeed;


    }

    @Override
    public String identifyTrafficPattern(Double averageVehicleSpeed) {


        if (averageVehicleSpeed>50){
            return trafficPattern= "detect high speed traffic ";
        } else if (averageVehicleSpeed<50|averageVehicleSpeed>40) {
            return trafficPattern= "detect no traffic ";
        } else if (averageVehicleSpeed<40| averageVehicleSpeed>30) {
            return trafficPattern= "detect normal traffic";
        } else if (averageVehicleSpeed<30|averageVehicleSpeed>20) {
            return trafficPattern= "detect high traffic";
        } else if (averageVehicleSpeed<20) {
            return trafficPattern= "detect heavy traffic please check issue";
        }
        return trafficPattern= "not working";
    }

    @Override
    public double getAverageVehicleSpeed() {

        double avg_VehicleSpeed=0;
        for (Double speed:this.vehicleSpeed){
            avg_VehicleSpeed +=speed/this.vehicleSpeed.size();
        }

        return avg_VehicleSpeed;
    }

    @Override
    public String getIdentifyTrafficPattern() {
        return trafficPattern;
    }


}



