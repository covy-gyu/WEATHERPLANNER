package dao;

import java.sql.*;

public class DBConnection {
    public static Connection getConnection() {
        String server = "jdbc:mysql://192.187.99.151:3306/weather"; // 서버 주소
        String user_name = "root"; //  접속자 id
        String password = "1234"; // 접속자 pw
        Connection conn = null;
        //JDBC 드라이버 로드
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC 드라이버를 로드하는데에 문제 발생" + e.getMessage());
            e.printStackTrace();
        }
        //DB 접속
        try{
            conn=DriverManager.getConnection(server + "/" + "?useSSL=false", user_name, password);
            System.out.println("DB연결 완료!");
        } catch (SQLException e) {
            System.err.println("DB연결 오류" + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }

}