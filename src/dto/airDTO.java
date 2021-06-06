package dto;

public class AirDTO {
    public AirDTO() {}
    public AirDTO(String dataTime, String o3Value, String pm10Value, String pm25Value, String o3Grade,
            String pm10Grade1h, String pm25Grade1h) {
        this.dataTime = dataTime;
        this.o3Value = o3Value;
        this.pm10Value = pm10Value;
        this.pm25Value = pm25Value;
        this.o3Grade = o3Grade;
        this.pm10Grade1h = pm10Grade1h;
        this.pm25Grade1h = pm25Grade1h;
    }
    public String getDataTime() {
        return dataTime;
    }
    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }
    public String getO3Value() {
        return o3Value;
    }
    public void setO3Value(String o3Value) {
        this.o3Value = o3Value;
    }
    public String getPm10Value() {
        return pm10Value;
    }
    public void setPm10Value(String pm10Value) {
        this.pm10Value = pm10Value;
    }
    public String getPm25Value() {
        return pm25Value;
    }
    public void setPm25Value(String pm25Value) {
        this.pm25Value = pm25Value;
    }
    public String getO3Grade() {
        return o3Grade;
    }
    public void setO3Grade(String o3Grade) {
        this.o3Grade = o3Grade;
    }
    public String getPm10Grade1h() {
        return pm10Grade1h;
    }
    public void setPm10Grade1h(String pm10Grade1h) {
        this.pm10Grade1h = pm10Grade1h;
    }
    public String getPm25Grade1h() {
        return pm25Grade1h;
    }
    public void setPm25Grade1h(String pm25Grade1h) {
        this.pm25Grade1h = pm25Grade1h;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("시각 : " + dataTime + " | ");
        sb.append("o3value : " + o3Value + " | ");
        sb.append("pm10value : " + pm10Value + " | ");
        sb.append("pm25value : " + pm25Value + " | ");
        sb.append("o3grade : " + o3Grade + " | ");
        sb.append("pm10grade1h : " + pm10Grade1h + " | ");
        sb.append("pm25grade1h : " + pm25Grade1h + " | ");
        return sb.toString();
    }
    private String dataTime;
    private String o3Value;
    private String pm10Value;
    private String pm25Value;
    private String o3Grade;
    private String pm10Grade1h;
    private String pm25Grade1h;

    
}
