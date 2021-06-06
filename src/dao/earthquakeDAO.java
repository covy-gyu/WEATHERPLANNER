package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import dto.EarthquakeDTO;

public class EarthquakeDAO {

    public EarthquakeDAO() {
    }

    static boolean insertResult = false;
    
    private static PreparedStatement pstmt = null;
    private static Connection conn = null;
    private static ResultSet rs = null;

    public static EarthquakeDTO selectData() {

        String SQLcu = "SELECT * FROM weather.Earthquake ORDER BY tmEqk DESC LIMIT 1";
        EarthquakeDTO dto = new EarthquakeDTO();
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(SQLcu);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                dto.setTmEqk(rs.getString("tmEqk"));
              //  dto.setFcTp(rs.getString("fcTp"));
                dto.setImg(rs.getString("img"));
                dto.setLat(rs.getString("lat"));
                dto.setLon(rs.getString("lon"));
                dto.setLoc(rs.getString("loc"));
                dto.setMt(rs.getString("mt"));
               // dto.setDep(rs.getString("dep"));
                dto.setRem(rs.getString("rem"));
                dto.setIntScale(rs.getString("intScale"));
            }
        } catch (SQLException sqle) {
            System.out.println("SELECT문에서 예외 발생");
            sqle.printStackTrace();
            return dto;
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
        return dto;
    }

    public static boolean insertData(EarthquakeDTO dto) {
        String SQL = "INSERT INTO weather.Earthquake(tmEqk,fcTp,img,lat,lon,loc,mt,dep,rem,intScale)"
                + "VALUES (?,?,?,?,?,?,?,?,?,?)";
        if(Objects.equals(dto, null))
        return insertResult;
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, dto.getTmEqk());
            pstmt.setString(2, dto.getFcTp());
            pstmt.setString(3, dto.getImg());
            pstmt.setString(4, dto.getLat());
            pstmt.setString(5, dto.getLon());
            pstmt.setString(6, dto.getLoc());
            pstmt.setString(7, dto.getMt());
            pstmt.setString(8, dto.getDep());
            pstmt.setString(9, dto.getRem());
            pstmt.setString(10, dto.getIntScale());
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
}
