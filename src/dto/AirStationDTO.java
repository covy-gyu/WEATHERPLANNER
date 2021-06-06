package dto;

public class AirStationDTO  {
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