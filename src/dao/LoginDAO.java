package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    public LoginDAO() {}

    private static PreparedStatement pstmt = null;
    private static Connection conn = null;
    private static ResultSet rs = null;

    public static int checkLogin(String id, String pw) {

        String SQL = "SELECT password FROM weather.User WHERE id = ?";
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            
            if(rs.next()){
                //pw일치시 실행
                if(rs.getString(1).equals(pw)){
                    return 1;//성공
                } else
                    return 0;//비밀번호 불일치
            }
            return -1;
        } catch (SQLException sqle) {
            System.out.println("SELECT문에서 예외 발생");
            sqle.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return -2;//데이터베이스 오류
    }

}
