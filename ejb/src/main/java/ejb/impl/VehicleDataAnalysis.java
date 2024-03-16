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
    public String identifyTrafficPattern(String trafficPattern) {
        return null;
    }
}
