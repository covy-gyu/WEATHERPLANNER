package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.earthquakeDTO;

public class earthquakeDAO {

    public earthquakeDAO() {
    }

    boolean insertResult = false;
    boolean deleteResult = false;
    boolean updateResult = false;

    private PreparedStatement pstmt = null;
    private Connection conn = null;
    private ResultSet rs = null;

    public earthquakeDTO selectData() {

        String SQLcu = "SELECT * FROM weather.Earthquake ORDER BY tmEqk DESC LIMIT 1";
        earthquakeDTO resultDTO = new earthquakeDTO();
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(SQLcu);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                resultDTO.setTmEqk(rs.getString("tmEqk"));
                resultDTO.setFcTp(rs.getString("fcTp"));
                resultDTO.setImg(rs.getString("img"));
                resultDTO.setLat(rs.getString("lat"));
                resultDTO.setLon(rs.getString("lon"));
                resultDTO.setLoc(rs.getString("loc"));
                resultDTO.setMt(rs.getString("mt"));
                resultDTO.setDep(rs.getString("dep"));
                resultDTO.setRem(rs.getString("rem"));
                resultDTO.setIntScale(rs.getString("intScale"));
            }
        } catch (SQLException sqle) {
            System.out.println("SELECT문에서 예외 발생");
            sqle.printStackTrace();
            return resultDTO;
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
        return resultDTO;
    }

    public boolean insertData(earthquakeDTO eqkData) {
        String SQL = "INSERT INTO weather.Earthquake(tmEqk,fcTp,img,lat,lon,loc,mt,dep,rem,intScale)"
                + "VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, eqkData.getTmEqk());
            pstmt.setString(2, eqkData.getFcTp());
            pstmt.setString(3, eqkData.getImg());
            pstmt.setString(4, eqkData.getLat());
            pstmt.setString(5, eqkData.getLon());
            pstmt.setString(6, eqkData.getLoc());
            pstmt.setString(7, eqkData.getMt());
            pstmt.setString(8, eqkData.getDep());
            pstmt.setString(9, eqkData.getRem());
            pstmt.setString(10, eqkData.getIntScale());
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
