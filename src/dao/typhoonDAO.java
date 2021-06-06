package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.TyphoonDTO;

public class TyphoonDAO {

    public TyphoonDAO() {
    }

    boolean insertResult = false;
    boolean deleteResult = false;
    boolean updateResult = false;

    private PreparedStatement pstmt = null;
    private Connection conn = null;
    private ResultSet rs = null;

    public TyphoonDTO selectData() {

        String SQLcu = "SELECT * FROM weather.Typhoon ORDER BY typTm DESC LIMIT 1";
        TyphoonDTO resultDTO = new TyphoonDTO();
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(SQLcu);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                resultDTO.setTypTm(rs.getString("typTm"));
                resultDTO.setImg(rs.getString("img"));
                resultDTO.setTmFc(rs.getString("tmFc"));
                resultDTO.setTypLat(rs.getString("typLat"));
                resultDTO.setTypLon(rs.getString("typLon"));
                resultDTO.setTypLoc(rs.getString("typLoc"));
                resultDTO.setTypDir(rs.getString("typDir"));
                resultDTO.setTypSp(rs.getString("typSp"));
                resultDTO.setTypPs(rs.getString("typPs"));
                resultDTO.setTypWs(rs.getString("typWs"));
                resultDTO.setTyp15(rs.getString("typ15"));
                resultDTO.setTyp25(rs.getString("typ25"));
                resultDTO.setTypName(rs.getString("typName"));
                resultDTO.setTypEn(rs.getString("typEn"));
                resultDTO.setRem(rs.getString("rem"));
                resultDTO.setOther(rs.getString("other"));
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

    public boolean insertData(TyphoonDTO tpnData) {
        String SQL = "INSERT INTO weather.Typhoon(typTm,img,tmFc,typLat,typLon,typLoc,typDir,typSp,typPs,typWs,typ15,typ25,typName,typEn,rem,other)"
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, tpnData.getTypTm());
            pstmt.setString(2, tpnData.getImg());
            pstmt.setString(3, tpnData.getTmFc());
            pstmt.setString(4, tpnData.getTypLat());
            pstmt.setString(5, tpnData.getTypLon());
            pstmt.setString(6, tpnData.getTypLoc());
            pstmt.setString(7, tpnData.getTypDir());
            pstmt.setString(8, tpnData.getTypSp());
            pstmt.setString(9, tpnData.getTypPs());
            pstmt.setString(10, tpnData.getTypWs());
            pstmt.setString(11, tpnData.getTyp15());
            pstmt.setString(12, tpnData.getTyp25());
            pstmt.setString(13, tpnData.getTypName());
            pstmt.setString(14, tpnData.getTypEn());
            pstmt.setString(15, tpnData.getRem());
            pstmt.setString(16, tpnData.getOther());
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
