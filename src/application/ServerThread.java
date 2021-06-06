package application;

import dao.AlarmDAO;
import dao.EarthquakeDAO;
import dao.LoginDAO;
import dao.TyphoonDAO;
import dto.AlarmDTO;
import dto.EarthquakeDTO;
import dto.LoginDTO;
import dto.TyphoonDTO;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class ServerThread extends Thread {
    public static final int LOGIN = 0;
    public static final int CITY = 1;
    public static final int STATE = 2;
    public static final int COUNTY = 3;
    public static final int SELECT_ALARM = 4;
    public static final int INSERT_ALARM = 5;
    public static final int DELETE_ALARM = 6;
    public static final int EARTHQUAKE = 7;
    public static final int TYPHOON = 8;

    private Socket socket;
    private String userIP;

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
        ObjectInputStream objectInputStream;
        ObjectOutputStream objectOutputStream;
        boolean program_stop = false;

        while (true) {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            // 밸류로 DTO의 Set을 가지는 HashMap, key로 어떤 데이터 주고받는지 구분
            HashMap<Integer, Object> readHashMap = new HashMap<>();
            HashMap<Integer, Object> writeHashMap = new HashMap<>();
            AlarmDTO alarmDTO = null;
            EarthquakeDTO eqkDTO = null;
            TyphoonDTO tpnDTO = null;

            boolean result = false;
            try {
                readHashMap = (HashMap<Integer, Object>) objectInputStream.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            int key = 0; // 키 값 찾기 (키 갯수 1개)
            for (int keyValue : readHashMap.keySet()) {
                key = keyValue;
            }

            // 키값에 따른 서버의 행동
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

                    lgDto.setResult(LoginDAO.checkLogin(target1, target2));

                    writeHashMap.put(LOGIN, lgDto);
                    objectOutputStream.writeObject(writeHashMap);
                    objectOutputStream.flush();
                    break;
                case SELECT_ALARM:
                    alarmDTO = (AlarmDTO) readHashMap.get(key);

                    target1 = alarmDTO.getUserID();

                    System.out.println(target1);

                    ArrayList<String> alarmList = AlarmDAO.selectAlarm(target1);

                    writeHashMap.put(SELECT_ALARM, alarmList);
                    objectOutputStream.writeObject(writeHashMap);
                    objectOutputStream.flush();
                    break;
                case INSERT_ALARM:
                    alarmDTO = (AlarmDTO) readHashMap.get(key);
                    target1 = alarmDTO.getUserID();
                    target2 = alarmDTO.getTime();

                    System.out.println(target1);
                    System.out.println(target2);

                    result = AlarmDAO.insertAlarm(alarmDTO);

                    writeHashMap.put(INSERT_ALARM, result);
                    objectOutputStream.writeObject(writeHashMap);
                    objectOutputStream.flush();
                    break;
                case DELETE_ALARM:
                    alarmDTO = (AlarmDTO) readHashMap.get(key);
                    target1 = alarmDTO.getUserID();
                    target2 = alarmDTO.getTime();

                    System.out.println(target1);
                    System.out.println(target2);

                    result = AlarmDAO.insertAlarm(alarmDTO);

                    writeHashMap.put(INSERT_ALARM, result);
                    objectOutputStream.writeObject(writeHashMap);
                    objectOutputStream.flush();
                    break;
                case EARTHQUAKE:
                    System.out.println("태풍 조회 요청받음");
                    eqkDTO = EarthquakeDAO.selectData();

                    writeHashMap.put(EARTHQUAKE, eqkDTO);
                    objectOutputStream.writeObject(writeHashMap);
                    objectOutputStream.flush();
                    break;
                case TYPHOON:
                    System.out.println("지진 조회 요청받음");
                    tpnDTO = TyphoonDAO.selectData();

                    writeHashMap.put(TYPHOON, tpnDTO);
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
