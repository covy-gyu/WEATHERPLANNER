package dto;

public class AlarmDTO {
    private String userID;
    private String time;
    public String getUserID() {
        return userID;
    }
    public AlarmDTO(){}
    public AlarmDTO(String userID, String time) {
        this.userID = userID;
        this.time = time;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
}
