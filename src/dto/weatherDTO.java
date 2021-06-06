package dto;

import java.util.HashMap;

public class weatherDTO {
    private String fcstTIme;
    private HashMap<Object, Object> fcstMap;

    public weatherDTO() {
    }

    public weatherDTO(String fcstTIme, HashMap<Object, Object> fcstMap) {
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
