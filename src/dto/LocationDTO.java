package dto;

import java.io.Serializable;

public class LocationDTO implements Serializable {
    private static final long serialVersionUID = 1234567890L;
    private String id;
   
    private String city;
    private String state;
    private String county;
    private String x;
    private String y;
    public LocationDTO(){}
    public LocationDTO(String id, String city, String state, String county, String x, String y)
    {
        this.id=id;
        this.city=city;
        this.state = state;
        this.county=county;
        this.x=x;
        this.y=y;
    }
    public String getCity() {
        return city;
    }


    public void setCity(String city) {
        this.city = city;
    }


    public String getState() {
        return state;
    }


    public void setState(String state) {
        this.state = state;
    }


    public String getCounty() {
        return county;
    }


    public void setCounty(String county) {
        this.county = county;
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id + " | ");
        sb.append(city + " | ");
        sb.append(state + " | ");
        sb.append(county + " | ");
        sb.append(x + " | ");
        sb.append(y + " | ");
        return sb.toString();
    }
}