package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import dto.TyphoonDTO;

public class TyphoonDAO {

    public TyphoonDAO() {
    }

    static boolean insertResult = false;

    private static PreparedStatement pstmt = null;
    private static Connection conn = null;
    private static ResultSet rs = null;

    public static TyphoonDTO selectData() {

        String SQLcu = "SELECT * FROM weather.Typhoon ORDER BY typTm DESC LIMIT 1";
        TyphoonDTO dto = new TyphoonDTO();
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(SQLcu);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                dto.setTypTm(rs.getString("typTm"));
                dto.setImg(rs.getString("img"));
                dto.setTmFc(rs.getString("tmFc"));
                dto.setTypLat(rs.getString("typLat"));
                dto.setTypLon(rs.getString("typLon"));
                dto.setTypLoc(rs.getString("typLoc"));
                dto.setTypDir(rs.getString("typDir"));
                dto.setTypSp(rs.getString("typSp"));
                dto.setTypPs(rs.getString("typPs"));
                dto.setTypWs(rs.getString("typWs"));
                dto.setTyp15(rs.getString("typ15"));
                dto.setTyp25(rs.getString("typ25"));
                dto.setTypName(rs.getString("typName"));
                dto.setTypEn(rs.getString("typEn"));
                dto.setRem(rs.getString("rem"));
                dto.setOther(rs.getString("other"));
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

    public static boolean insertData(TyphoonDTO dto) {
        String SQL = "INSERT INTO weather.Typhoon(typTm,img,tmFc,typLat,typLon,typLoc,typDir,typSp,typPs,typWs,typ15,typ25,typName,typEn,rem,other)"
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        if (Objects.equals(dto, null))
            return insertResult;
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, dto.getTypTm());
            pstmt.setString(2, dto.getImg());
            pstmt.setString(3, dto.getTmFc());
            pstmt.setString(4, dto.getTypLat());
            pstmt.setString(5, dto.getTypLon());
            pstmt.setString(6, dto.getTypLoc());
            pstmt.setString(7, dto.getTypDir());
            pstmt.setString(8, dto.getTypSp());
            pstmt.setString(9, dto.getTypPs());
            pstmt.setString(10, dto.getTypWs());
            pstmt.setString(11, dto.getTyp15());
            pstmt.setString(12, dto.getTyp25());
            pstmt.setString(13, dto.getTypName());
            pstmt.setString(14, dto.getTypEn());
            pstmt.setString(15, dto.getRem());
            pstmt.setString(16, dto.getOther());
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
