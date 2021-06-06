package dto;

import java.io.Serializable;

public class LoginDTO implements Serializable{
    private static final long serialVersionUID = 1234567890L;
    private String id;
    private String pw;
    private int result;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public LoginDTO() {}
    public LoginDTO(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }
    public String getPw() {
        return pw;
    }
    public void setPw(String pw) {
        this.pw = pw;
    }
    public int getResult() {
        return result;
    }
    public void setResult(int result) {
        this.result = result;
    }
}