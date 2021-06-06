package dto;

import java.util.HashMap;

public class WeatherDTO {
    private String fcstTIme;
    private HashMap<Object, Object> fcstMap;

    public WeatherDTO() {
    }

    public WeatherDTO(String fcstTIme, HashMap<Object, Object> fcstMap) {
        this.fcstTIme = fcstTIme;
        this.fcstMap = fcstMap;
    }

    public String getFcstTIme() {
        return fcstTIme;
    }

    public void setFcstTIme(String fcstTIme) {
        this.fcstTIme = fcstTIme;
    }

    public HashMap<Object, Object> getFcstMap() {
        return fcstMap;
    }

    public void setFcstMap(HashMap<Object, Object> fcstMap) {
        this.fcstMap = fcstMap;
    }
}
