package dto;

public class earthquakeDTO {
   
    private String fcTp;
    private String img;
    private String intScale;
    private String lat;
    private String loc;
    private String lon;
    private String mt;
    private String rem;
    private String tmEqk;
    private String dep;

    public earthquakeDTO() {}
    public earthquakeDTO(String fcTp, String img, String intScale, String lat, String loc, String lon, String mt,
            String rem, String tmEqk, String dep) {
        this.fcTp = fcTp;
        this.img = img;
        this.intScale = intScale;
        this.lat = lat;
        this.loc = loc;
        this.lon = lon;
        this.mt = mt;
        this.rem = rem;
        this.tmEqk = tmEqk;
        this.dep = dep;
    }

    public String getFcTp() {
        return fcTp;
    }
    public void setFcTp(String fcTp) {
        this.fcTp = fcTp;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public String getIntScale() {
        return intScale;
    }
    public void setIntScale(String intScale) {
        this.intScale = intScale;
    }
    public String getLat() {
        return lat;
    }
    public void setLat(String lat) {
        this.lat = lat;
    }
    public String getLoc() {
        return loc;
    }
    public void setLoc(String loc) {
        this.loc = loc;
    }
    public String getLon() {
        return lon;
    }
    public void setLon(String lon) {
        this.lon = lon;
    }
    public String getMt() {
        return mt;
    }
    public void setMt(String mt) {
        this.mt = mt;
    }
    public String getRem() {
        return rem;
    }
    public void setRem(String rem) {
        this.rem = rem;
    }
    public String getTmEqk() {
        return tmEqk;
    }
    public void setTmEqk(String tmEqk) {
        this.tmEqk = tmEqk;
    }
    public String getDep() {
        return dep;
    }
    public void setDep(String dep) {
        this.dep = dep;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("발생시각 : "+ tmEqk + " | ");
        sb.append("이미지 : "+ img + " | ");
        sb.append("진도 : "+ intScale + " | ");
        sb.append("위도 : "+ lat + " | ");
        sb.append("위치 : "+ loc + " | ");
        sb.append("경도 : "+ lon + " | ");
        sb.append("규모 : "+ mt + " | ");
        sb.append("참고사항 : "+ rem + " | ");
        sb.append("유형 : "+ fcTp + " | ");
        sb.append("깊이 : "+ dep + " | ");
        return sb.toString();
    }
}
