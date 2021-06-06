package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.AlarmDTO;
public class AlarmDAO {

    public AlarmDAO() {
    }

    static boolean insertResult = false;
    static boolean deleteResult = false;
    static boolean updateResult = false;

    private static PreparedStatement pstmt = null;
    private static Connection conn = null;
    private static ResultSet rs = null;

    public static ArrayList<String> selectAlarm(String id) {
        String SQL = "SELECT time FROM weather.Alarm WHERE id = ?";
        ArrayList<String> result = new ArrayList<>();
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();
            pstmt.setString(1, id);
            while (rs.next()) {
                result.add(rs.getString("time"));
            }
        } catch (SQLException sqle) {
            System.out.println("SELECT문에서 예외 발생");
            sqle.printStackTrace();
            return result;
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
        return result;
    }

    public static boolean insertAlarm(AlarmDTO dto) {
        String SQL = "INSERT INTO weather.Alarm(id,time)"
                + "VALUES (?,?)";
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, dto.getUserID());
            pstmt.setString(2, dto.getTime());
            pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("INSERT문에서 예외 발생");
            sqle.printStackTrace();
            return insertResult;
        } finally {
            try {
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
        insertResult = true;
        return insertResult;
    }
    public static boolean delteAlarm(AlarmDTO dto) {
        String SQL = "DELTE from weather.Alarm WHERE id = ? AND time = ?";

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, dto.getUserID());
            pstmt.setString(2, dto.getTime());
            pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("INSERT문에서 예외 발생");
            sqle.printStackTrace();
            return deleteResult;
        } finally {
            try {
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
        deleteResult = true;
        return deleteResult;
    }
}
