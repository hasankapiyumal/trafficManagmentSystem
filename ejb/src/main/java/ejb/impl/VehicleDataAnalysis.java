package ejb.impl;

import ejb.remote.DataAnalysis;
import jakarta.ejb.Stateless;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class VehicleDataAnalysis implements DataAnalysis {
private List<Double> vehicleSpeed =new ArrayList<>();

    @Override
    public double calculateAverageVehicleSpeed(Double vehicleSpeed) {


        return 10;
    }

    @Override
    public String identifyTrafficPattern(String trafficPattern) {
        return null;
    }
}
