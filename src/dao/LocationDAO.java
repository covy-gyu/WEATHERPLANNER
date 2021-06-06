package dao;

import dto.LocationDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;

public class LocationDAO {
    public static ArrayList<LocationDTO> selectAll() {
        ArrayList<LocationDTO> dtos = new ArrayList<LocationDTO>();
        Statement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        String SQL = "SELECT * FROM weather.Location";

        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                String id = rs.getString("id");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String county = rs.getString("county");
                String x = rs.getString("x");
                String y = rs.getString("y");
                LocationDTO dto = new LocationDTO(id, city, state, county, x, y);
                dtos.add(dto);
            }
        } catch (SQLException sqle) {
            System.out.println("SELECT문에서 예외 발생");
            sqle.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return dtos;
    }
     public static HashSet<String> selectGroup(String city){
        ArrayList<LocationDTO> dtos = new ArrayList<LocationDTO>();
        PreparedStatement pstmt = null;
        Connection conn = null;
        ResultSet rs = null;
        String SQL = "SELECT state FROM weather.Location Where city= ?";
        ArrayList<String> list = new ArrayList<String>();

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, city);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String state = rs.getString("state");
                list.add(state);
            }
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


         HashSet<String> hashSet = new HashSet<>(list);
        return hashSet;
    }
    public static HashSet<String> selectCounty(String city, String state){
        ArrayList<LocationDTO> dtos = new ArrayList<LocationDTO>();
        PreparedStatement pstmt = null;
        Connection conn = null;
        ResultSet rs = null;
        String SQL = "SELECT county FROM weather.Location Where city=? and state= ?";
        ArrayList<String> list = new ArrayList<String>();

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, city);
            pstmt.setString(2, state);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String county = rs.getString("county");
                list.add(county);
            }
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


        HashSet<String> hashSet = new HashSet<>(list);
        return hashSet;
    }

    public static String[] selectXY(String city,String state, String county){
        String[] result = new String[2];
        PreparedStatement pstmt = null;
        Connection conn = null;
        ResultSet rs = null;
        String SQL = "SELECT x,y FROM weather.Location Where city=? and state= ? and county= ?";

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, city);
            pstmt.setString(2, state);
            pstmt.setString(3, county);

            rs = pstmt.executeQuery();

            if(rs.next()) {
                String x = rs.getString("x");
                String y = rs.getString("y");
                result[0] = x;
                result[1] = y;
            }
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
        return result;
    }

}