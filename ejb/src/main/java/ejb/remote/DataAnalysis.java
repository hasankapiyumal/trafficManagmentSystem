package ejb.remote;

import jakarta.ejb.Remote;

import java.util.List;
@Remote
public interface DataAnalysis {
    public double calculateAverageVehicleSpeed(Double vehicleSpeed);
    public String identifyTrafficPattern(Double averageVehicleSpeed);
    double getAverageVehicleSpeed();
    String getIdentifyTrafficPattern();
    String trafficFlowAnalyse(boolean trafficLightStatus,double longitude ,double latitude,double vehicleSpeed);
    String getTrafficFlowAnalyse();
    double mobilityEfficiency(boolean trafficLightStatus,double vehicleSpeed);
    double getMobilityEfficiency();



}
