package ejb.remote;

import jakarta.ejb.Remote;

import java.util.List;
@Remote
public interface DataAnalysis {
    public double calculateAverageVehicleSpeed(Double vehicleSpeed);
    public String identifyTrafficPattern(String trafficPattern);


}
