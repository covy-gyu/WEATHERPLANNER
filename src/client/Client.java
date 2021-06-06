package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import dto.*;

public class Client {
    private  ObjectOutputStream oos;
    private  ObjectInputStream ois;
    public static final int LOGIN = 0;
    public static final int CITY = 1;
    public static final int STATE = 2;
    public static final int COUNTY = 3;
    public static final int SELECT_ALARM = 4;
    public static final int INSERT_ALARM = 5;
    public static final int DELETE_ALARM = 6;
    public static final int EARTHQUAKE = 7;
    public static final int TYPHOON = 8;
    public static final int MIDDLEWEATHER = 9;

    HashMap<Integer, Object> readHashMap = new HashMap<>();
    HashMap<Integer, Object> wirteHashMap = new HashMap<>();
    HashSet<LocationDTO> ldtos = null;
    ArrayList<WeatherDTO> wdtos = null;
    EarthquakeDTO eDto = null;
    TyphoonDTO tDto = null;
    MiddleWeatherDTO mDto = null;
    Object request = null;

    int key = 0; // 키 값 찾기 (키 갯수 1개)

    public void findKey(HashMap<Integer, Object> rMap) {
        for (int keyValue : rMap.keySet()) {
            key = keyValue;
        }
    }

    public String[] getAlarms() throws IOException{
        oos = new ObjectOutputStream(Myconn.socket.getOutputStream());
        AlarmDTO dto = new AlarmDTO();
        dto.setUserID(Myconn.SessionUserID);
        wirteHashMap = new HashMap<>();
        wirteHashMap.put(SELECT_ALARM,dto);
        oos.writeObject(wirteHashMap);
        oos.flush();

        ois = new ObjectInputStream(Myconn.socket.getInputStream());
        try {
            readHashMap = (HashMap<Integer, Object>) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        findKey(readHashMap);

        ArrayList<String> alarmList = (ArrayList<String>) readHashMap.get(key);
        String [] result = new String[alarmList.size()];
        for(int i=0;i<alarmList.size();i++)
        {
            result[i] = alarmList.get(i);
        }
        return result;
    }
    public void saveAlarm(String time) throws IOException{
        oos = new ObjectOutputStream(Myconn.socket.getOutputStream());
        AlarmDTO dto = new AlarmDTO();
        dto.setUserID(Myconn.SessionUserID);
        dto.setTime(time);
        wirteHashMap = new HashMap<>();
        wirteHashMap.put(INSERT_ALARM,dto);
        oos.writeObject(wirteHashMap);
        oos.flush();

        ois = new ObjectInputStream(Myconn.socket.getInputStream());
        try {
            readHashMap = (HashMap<Integer, Object>) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        findKey(readHashMap);

        boolean result = (boolean) readHashMap.get(key);
        if(result == true)
        {
            System.out.println("db : 알림저장 성공");
        }
        else
            System.out.println("db : 알림저장 실패");
    }
    public void deleteAlarm(String time) throws IOException{
        oos = new ObjectOutputStream(Myconn.socket.getOutputStream());
        AlarmDTO dto = new AlarmDTO();
        dto.setUserID(Myconn.SessionUserID);
        dto.setTime(time);
        wirteHashMap = new HashMap<>();
        wirteHashMap.put(INSERT_ALARM,dto);
        oos.writeObject(wirteHashMap);
        oos.flush();

        ois = new ObjectInputStream(Myconn.socket.getInputStream());
        try {
            readHashMap = (HashMap<Integer, Object>) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        findKey(readHashMap);

        boolean result = (boolean) readHashMap.get(key);
        if(result == true)
        {
            System.out.println("db : 알림삭제 성공");
        }
        else
            System.out.println("db : 알림삭제 실패");
    }

    public int setLoginAndgetResult(String id, String pw) throws IOException {
        oos = new ObjectOutputStream(Myconn.socket.getOutputStream());
        LoginDTO dto = new LoginDTO();
        dto.setId(id);
        dto.setPw(pw);
        wirteHashMap = new HashMap<>();
        wirteHashMap.put(LOGIN, dto);
        oos.writeObject(wirteHashMap);
        oos.flush();

        ois = new ObjectInputStream(Myconn.socket.getInputStream());
        try {
            readHashMap = (HashMap<Integer, Object>) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        findKey(readHashMap);

        dto = (LoginDTO) readHashMap.get(key);
        int result = dto.getResult();
        ;
        return result;
    }

    public String[] setCityAndGetState(String city) throws IOException {

        oos = new ObjectOutputStream(Myconn.socket.getOutputStream());
        LocationDTO resultDto = new LocationDTO();
        resultDto.setCity(city);
        HashSet<LocationDTO> dto = new HashSet<>();
        dto.add(resultDto);
        wirteHashMap = new HashMap<>();
        wirteHashMap.put(CITY, dto);
        oos.writeObject(wirteHashMap);
        oos.flush();

        ois = new ObjectInputStream(Myconn.socket.getInputStream());
        try {
            readHashMap = (HashMap<Integer, Object>) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        findKey(readHashMap);
        ldtos = (HashSet<LocationDTO>) readHashMap.get(key);

        String[] state = new String[ldtos.size()];
        Iterator<LocationDTO> it = ldtos.iterator();
        int i = 0;
        while (it.hasNext()) {
            state[i] = it.next().getState();
            i++;
        }
        return state;
    }

    public String[] setStateAndGetCounty(String city, String state) throws IOException {

        oos = new ObjectOutputStream(Myconn.socket.getOutputStream());
        LocationDTO resultDto = new LocationDTO();
        resultDto.setCity(city);
        resultDto.setState(state);
        HashSet<LocationDTO> dto = new HashSet<>();
        dto.add(resultDto);
        wirteHashMap = new HashMap<>();
        wirteHashMap.put(STATE, dto);
        oos.writeObject(wirteHashMap);
        oos.flush();

        ois = new ObjectInputStream(Myconn.socket.getInputStream());
        try {
            readHashMap = (HashMap<Integer, Object>) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        findKey(readHashMap);
        ldtos = (HashSet<LocationDTO>) readHashMap.get(key);
        String[] county = new String[ldtos.size()];
        Iterator<LocationDTO> it = ldtos.iterator();
        int i = 0;
        while (it.hasNext()) {
            county[i] = it.next().getCounty();
            i++;
        }
        return county;
    }

    public void getWeather(String city, String state, String county, String[] time, String[] temperature,
             String[] rainAmount, String[] cloud) throws IOException {

        oos = new ObjectOutputStream(Myconn.socket.getOutputStream());
        LocationDTO resultDto = new LocationDTO();
        resultDto.setCity(city);
        resultDto.setState(state);
        resultDto.setCounty(county);
        HashSet<LocationDTO> dto = new HashSet<>();
        dto.add(resultDto);
        wirteHashMap = new HashMap<>();
        wirteHashMap.put(COUNTY, dto);
        oos.writeObject(wirteHashMap);
        oos.flush();

        ois = new ObjectInputStream(Myconn.socket.getInputStream());
        try {
            readHashMap = (HashMap<Integer, Object>) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        findKey(readHashMap);
        wdtos = (ArrayList<WeatherDTO>) readHashMap.get(key);
        for (int i = 0; i < wdtos.size(); i++) {
            time[i] = wdtos.get(i).getFcstTIme().substring(0,2);
            temperature[i] = wdtos.get(i).getFcstMap().get("T1H");
            rainAmount[i] = wdtos.get(i).getFcstMap().get("RN1");
            cloud[i] = wdtos.get(i).getFcstMap().get("SKY");
        }
    }

    public void getAir(String[] airInfo) throws IOException {   
        ois = new ObjectInputStream(Myconn.socket.getInputStream());
        try {
            readHashMap = (HashMap<Integer, Object>) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        findKey(readHashMap);
        AirDTO adto = (AirDTO) readHashMap.get(key);
        airInfo[0] = adto.getPm10Value();
        airInfo[1] = adto.getPm25Value();
        airInfo[2] = adto.getO3Value();
        airInfo[3] = adto.getPm10Grade1h();
        airInfo[4] = adto.getPm25Grade1h();
        airInfo[5] = adto.getO3Grade();
    }

    public void getEarthquake(String[] eqkInfo) throws IOException{
        oos = new ObjectOutputStream(Myconn.socket.getOutputStream());
        wirteHashMap = new HashMap<>();
        wirteHashMap.put(EARTHQUAKE, request);
        oos.writeObject(wirteHashMap);
        oos.flush();

        ois = new ObjectInputStream(Myconn.socket.getInputStream());
        try {
            readHashMap = (HashMap<Integer, Object>) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        findKey(readHashMap);
        eDto = (EarthquakeDTO) readHashMap.get(key);
        eqkInfo[0] = eDto.getTmEqk();
        eqkInfo[1] = eDto.getIntScale();
        eqkInfo[2] = eDto.getLoc();
        eqkInfo[3] = eDto.getLat();
        eqkInfo[4] = eDto.getLon();
        eqkInfo[5] = eDto.getRem();
        eqkInfo[6] = eDto.getImg();

    }
    public void getTyphoon(String[] tpnInfo) throws IOException{
        oos = new ObjectOutputStream(Myconn.socket.getOutputStream());
        wirteHashMap = new HashMap<>();
        wirteHashMap.put(TYPHOON, request);
        oos.writeObject(wirteHashMap);
        oos.flush();

        ois = new ObjectInputStream(Myconn.socket.getInputStream());
        try {
            readHashMap = (HashMap<Integer, Object>) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        findKey(readHashMap);
        tDto = (TyphoonDTO) readHashMap.get(key);
        tpnInfo[0] = tDto.getTypName();
        tpnInfo[1] = tDto.getTypDir();
        tpnInfo[2] = tDto.getTypSp();
        tpnInfo[3] = tDto.getTypLat();
        tpnInfo[4] = tDto.getTypLon();
        tpnInfo[5] = tDto.getTypLoc();
        tpnInfo[6] = tDto.getTypPs();
        tpnInfo[7] = tDto.getTypWs();
        tpnInfo[8] = tDto.getTyp15();
        tpnInfo[9] = tDto.getTyp25();
        tpnInfo[10] = tDto.getRem();
        tpnInfo[11] = tDto.getImg();
    }

    public void getFucsWeather(String[] fuHiTemp, String[] fuLowTemp, String[] fuExpect) throws IOException{
        
        ois = new ObjectInputStream(Myconn.socket.getInputStream());
        try {
            readHashMap = (HashMap<Integer, Object>) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        findKey(readHashMap);
        mDto = (MiddleWeatherDTO) readHashMap.get(key);
        fuHiTemp[0] = mDto.getTaMax3();
        fuHiTemp[1] = mDto.getTaMax4();
        fuHiTemp[2] = mDto.getTaMax5();
        fuHiTemp[3] = mDto.getTaMax6();
        fuHiTemp.toString();
        fuLowTemp[0] = mDto.getTaMin3();
        fuLowTemp[1] = mDto.getTaMin4();
        fuLowTemp[2] = mDto.getTaMin5();
        fuLowTemp[3] = mDto.getTaMin6();
        fuLowTemp.toString();
        fuExpect[0] = mDto.getWf3Pm();
        fuExpect[1] = mDto.getWf4Pm();
        fuExpect[2] = mDto.getWf5Pm();
        fuExpect[3] = mDto.getWf6Pm();
        fuExpect.toString();
    }

}
