package ejb.impl;

import ejb.remote.DataAnalysis;
import jakarta.ejb.Singleton;
import jakarta.ejb.Stateless;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class VehicleDataAnalysis implements DataAnalysis {
    private List<Double> vehicleSpeed = new ArrayList<>();
    private String trafficPattern;
    private boolean trafficLightStatus;
    private double longitude;
    private double latitude;
    private String trafficFlow;
    private double overallMobilityEfficiency;

    @Override
    public double calculateAverageVehicleSpeed(Double vehicleSpeed) {

        this.vehicleSpeed.add(vehicleSpeed);
        double avgVehicleSpeed = 0;
        for (Double speed : this.vehicleSpeed) {
            avgVehicleSpeed += speed / this.vehicleSpeed.size();
        }

        return avgVehicleSpeed;


    }

    @Override
    public String identifyTrafficPattern(Double averageVehicleSpeed) {


        if (averageVehicleSpeed > 50) {
            return trafficPattern = "detect high speed traffic ";
        } else if (averageVehicleSpeed < 50 | averageVehicleSpeed > 40) {
            return trafficPattern = "detect no traffic ";
        } else if (averageVehicleSpeed < 40 | averageVehicleSpeed > 30) {
            return trafficPattern = "detect normal traffic";
        } else if (averageVehicleSpeed < 30 | averageVehicleSpeed > 20) {
            return trafficPattern = "detect high traffic";
        } else if (averageVehicleSpeed < 20) {
            return trafficPattern = "detect heavy traffic please check issue";
        }
        return trafficPattern = "not working";
    }

    @Override
    public double getAverageVehicleSpeed() {

        double avg_VehicleSpeed = 0;
        for (Double speed : this.vehicleSpeed) {
            avg_VehicleSpeed += speed / this.vehicleSpeed.size();
        }

        return avg_VehicleSpeed;
    }

    @Override
    public String getIdentifyTrafficPattern() {
        return trafficPattern;
    }

    @Override
    public String trafficFlowAnalyse(boolean trafficLightStatus, double longitude, double latitude, double vehicleSpeed) {
        if (trafficLightStatus) {
            if (latitude > 90 && longitude < 360) {
                if (vehicleSpeed > 50) {
                    return trafficFlow = " high speed traffic flow ";
                } else if (vehicleSpeed < 50 | vehicleSpeed > 40) {
                    return trafficFlow = "Normal traffic flow ";
                } else if (vehicleSpeed < 40 | vehicleSpeed > 30) {
                    return trafficFlow = "low traffic flow";
                } else if (vehicleSpeed < 30 | vehicleSpeed > 20) {
                    return trafficFlow = "high traffic flow";
                } else if (vehicleSpeed < 20) {
                    return trafficFlow = "Emergency traffic flow";
                }

            } else {
                return trafficFlow = "Traffic flow not functioning";
            }
        } else {
            if (vehicleSpeed > 5) {
                return trafficFlow = "Detect vehicle break traffic rules";
            }
        }

        return trafficFlow = "not working";
    }

    @Override
    public String getTrafficFlowAnalyse() {
        return trafficFlow;
    }

    @Override
    public double mobilityEfficiency(boolean trafficLightStatus, double vehicleSpeed) {
        if (trafficLightStatus){
            if (vehicleSpeed>50){
                return overallMobilityEfficiency= 90.0;
            } else if (vehicleSpeed<50 &&vehicleSpeed>40) {
                return overallMobilityEfficiency= 75.0;
            } else if (vehicleSpeed<40 && vehicleSpeed>30) {
                return overallMobilityEfficiency=50.0;
            } else if (vehicleSpeed<30 && vehicleSpeed>20) {
                return overallMobilityEfficiency= 30;
            }else {
                return overallMobilityEfficiency=10;
            }
        }
        return overallMobilityEfficiency=0;
    }

    @Override
    public double getMobilityEfficiency() {
        return overallMobilityEfficiency;
    }


}



