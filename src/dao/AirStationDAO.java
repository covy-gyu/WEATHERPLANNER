package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AirStationDAO {
    public static String selectStationName(String city, String state){
        PreparedStatement pstmt = null;
        Connection conn = null;
        ResultSet rs = null;
        String stationName="";
        String SQL = "SELECT stationName FROM weather.AirStation Where city=? and stationLocation like ?";

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, city);
            pstmt.setString(2, "%"+state+"%");
            rs = pstmt.executeQuery();

            if(rs.next())
                stationName = rs.getString("stationName");
        } catch (SQLException sqle) {
            System.out.println("SELECT문에서 예외 발생");
            sqle.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return stationName;
    }
}