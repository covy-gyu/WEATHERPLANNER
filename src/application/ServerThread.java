package application;

import dao.LocationDAO;
import dao.LoginDAO;
import dto.LocationDTO;
import dto.LoginDTO;

import java.io.*;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;


public class ServerThread extends Thread {
    public static final int LOGIN = 0;
    public static final int CITY = 1;
    public static final int STATE = 2;
    public static final int COUNTY = 3;
    public static final int WEATHER = 4;
    public static final int AIR = 5;
    private Socket socket;
    private String userIP;
    private HashMap<Integer, Object> hashMap = new HashMap();

    ServerThread(Socket socket) {
        this.socket = socket;
        this.userIP = this.socket.getInetAddress().toString();
    }

    public void run() {
        try {
            System.out.println("run");
            service();
        } catch (IOException e) {
            System.out.println("************" + userIP + "접속 종료.");
        } finally {
            try {
                closeAll();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void service() throws IOException {
        //Connection conn = DBConnection.getConnection();
        ObjectInputStream objectInputStream;
        ObjectOutputStream objectOutputStream;
        boolean program_stop = false;

        while (true) {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            //밸류로 DTO의 Set을 가지는 HashMap, key로 어떤 데이터 주고받는지 구분
            HashMap<Integer, Object> readHashMap = new HashMap<>();
            HashMap<Integer, Object> writeHashMap = new HashMap<>();
            try {
                readHashMap = (HashMap<Integer, Object>) objectInputStream.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            int key = 0; //키 값 찾기 (키 갯수 1개)
            for (int keyValue : readHashMap.keySet()) {
                key = keyValue;
            }

            //키값에 따른 서버의 행동
            String target1 = "";
            String target2 = "";
            String target3 = "";
            switch (key) {
                case LOGIN:
                    LoginDTO lgDto = (LoginDTO) readHashMap.get(key);

                    target1 = lgDto.getId();
                    target2 = lgDto.getPw();

                    System.out.println(target1);
                    System.out.println(target2);

                    lgDto.setResult(LoginDAO.checkLogin(target1,target2)); 

                    writeHashMap.put(LOGIN, lgDto);
                    objectOutputStream.writeObject(writeHashMap);
                    objectOutputStream.flush();
                    break;
               
            } // end switch
            if (program_stop) {
                break;
            }
        }
    }

    public void closeAll() throws IOException {
        if (socket != null)
            socket.close();
    }
}
