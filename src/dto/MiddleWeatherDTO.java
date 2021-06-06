
    package dto;

import java.io.Serializable;

public class MiddleWeatherDTO implements Serializable {
    private static final long serialVersionUID = 1234567890L;
    private String rnSt3Pm;
    private String rnSt4Pm;
    private String rnSt5Pm;
    private String rnSt6Pm;
    private String wf3Pm;
    private String wf4Pm;
    private String wf5Pm;
    private String wf6Pm;
    private String taMin3;
    private String taMax3;
    private String taMin4;
    public String getTaMin3() {
        return taMin3;
    }

    public void setTaMin3(String taMin3) {
        this.taMin3 = taMin3;
    }

    public String getTaMax3() {
        return taMax3;
    }

    public void setTaMax3(String taMax3) {
        this.taMax3 = taMax3;
    }

    public String getTaMin4() {
        return taMin4;
    }

    public void setTaMin4(String taMin4) {
        this.taMin4 = taMin4;
    }

    public String getTaMax4() {
        return taMax4;
    }

    public void setTaMax4(String taMax4) {
        this.taMax4 = taMax4;
    }

    public String getTaMin5() {
        return taMin5;
    }

    public void setTaMin5(String taMin5) {
        this.taMin5 = taMin5;
    }

    public String getTaMax5() {
        return taMax5;
    }

    public void setTaMax5(String taMax5) {
        this.taMax5 = taMax5;
    }

    public String getTaMin6() {
        return taMin6;
    }

    public void setTaMin6(String taMin6) {
        this.taMin6 = taMin6;
    }

    public String getTaMax6() {
        return taMax6;
    }

    public void setTaMax6(String taMax6) {
        this.taMax6 = taMax6;
    }

    private String taMax4;
    private String taMin5;
    private String taMax5;
    private String taMin6;
    private String taMax6;



    public MiddleWeatherDTO() {
    }

    public MiddleWeatherDTO(String rnSt3Pm, String rnSt4Pm, String rnSt5Pm, String rnSt6Pm, String wf3Pm, String wf4Pm,
                            String wf5Pm, String wf6Pm, String taMin3, String taMax3, String taMin4, String taMax4,
                            String taMin5, String taMax5, String taMin6, String taMax6) {
        this.rnSt3Pm = rnSt3Pm;
        this.rnSt4Pm = rnSt4Pm;
        this.rnSt5Pm = rnSt5Pm;
        this.rnSt6Pm = rnSt6Pm;
        this.wf3Pm = wf3Pm;
        this.wf4Pm = wf4Pm;
        this.wf5Pm = wf5Pm;
        this.wf6Pm = wf6Pm;
        this.taMin3 = taMin3;
        this.taMax3 = taMax3;
        this.taMin4 = taMin4;
        this.taMax4 = taMax4;
        this.taMin5 = taMin5;
        this.taMax5 = taMax5;
        this.taMin6 = taMin6;
        this.taMax6 = taMax6;
    }

    public String getRnSt3Pm() {
        return rnSt3Pm;
    }

    public void setRnSt3Pm(String rnSt3Pm) {
        this.rnSt3Pm = rnSt3Pm;
    }

    public String getRnSt4Pm() {
        return rnSt4Pm;
    }

    public void setRnSt4Pm(String rnSt4Pm) {
        this.rnSt4Pm = rnSt4Pm;
    }

    public String getRnSt5Pm() {
        return rnSt5Pm;
    }

    public void setRnSt5Pm(String rnSt5Pm) {
        this.rnSt5Pm = rnSt5Pm;
    }

    public String getRnSt6Pm() {
        return rnSt6Pm;
    }

    public void setRnSt6Pm(String rnSt6Pm) {
        this.rnSt6Pm = rnSt6Pm;
    }

    public String getWf3Pm() {
        return wf3Pm;
    }

    public void setWf3Pm(String wf3Pm) {
        this.wf3Pm = wf3Pm;
    }

    public String getWf4Pm() {
        return wf4Pm;
    }

    public void setWf4Pm(String wf4Pm) {
        this.wf4Pm = wf4Pm;
    }

    public String getWf5Pm() {
        return wf5Pm;
    }

    public void setWf5Pm(String wf5Pm) {
        this.wf5Pm = wf5Pm;
    }

    public String getWf6Pm() {
        return wf6Pm;
    }

    public void setWf6Pm(String wf6Pm) {
        this.wf6Pm = wf6Pm;
    }
}
