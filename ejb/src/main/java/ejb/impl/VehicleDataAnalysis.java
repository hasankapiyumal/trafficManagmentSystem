package ejb.impl;

import ejb.remote.DataAnalysis;
import jakarta.ejb.Stateless;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
public class VehicleDataAnalysis implements DataAnalysis {
    private List<Double> vehicleSpeed = new ArrayList<>();

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
            return "detect high speed traffic ";
        } else if (averageVehicleSpeed<50|averageVehicleSpeed>40) {
            return "detect no traffic ";
        } else if (averageVehicleSpeed<40| averageVehicleSpeed>30) {
            return "detect normal traffic";
        } else if (averageVehicleSpeed<30|averageVehicleSpeed>20) {
            return "detect high traffic";
        } else if (averageVehicleSpeed<20) {
            return "detect heavy traffic please check issue";
        }
        return "not working";
    }


}
