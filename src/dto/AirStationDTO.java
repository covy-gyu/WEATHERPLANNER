package dto;

import java.io.Serializable;

public class AirStationDTO implements Serializable {
    private static final long serialVersionUID = 1234567890L;
    private String city;
    private String stationName;
    private String stationLocation;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(city + " | ");
        sb.append(stationName + " | ");
        sb.append(stationLocation + " | ");
        return sb.toString();
    }
}