package dto;

import java.io.Serializable;
import java.util.HashMap;

public class WeatherDTO implements Serializable{
    private static final long serialVersionUID = 1234567890L;

    private String fcstTIme;
    private HashMap<String, String> fcstMap;

    public WeatherDTO() {
    }

    public WeatherDTO(String fcstTIme, HashMap<String, String> fcstMap) {
        this.fcstTIme = fcstTIme;
        this.fcstMap = fcstMap;
    }

    public String getFcstTIme() {
        return fcstTIme;
    }

    public void setFcstTIme(String fcstTIme) {
        this.fcstTIme = fcstTIme;
    }

    public HashMap<String, String> getFcstMap() {
        return fcstMap;
    }

    public void setFcstMap(HashMap<String, String> fcstMap) {
        this.fcstMap = fcstMap;
    }
}
