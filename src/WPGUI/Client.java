package WPGUI;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import dto.*;

public class Client {
    private static ObjectOutputStream oos;
    private static ObjectInputStream ois;

    public static final int LOGIN = 0;
    public static final int CITY = 1;
    public static final int STATE = 2;
    public static final int COUNTY = 3;
    public static final int WEATHER = 4;
    public static final int AIR = 5;

    HashMap<Integer, Object> readHashMap = null;
    HashMap<Integer, Object> wirteHashMap = null;
    HashMap<Integer, Object> map = new HashMap<Integer, Object>();
    HashSet<LocationDTO> ldtos = null;
    ArrayList<weatherDTO> wdtos = null;

    int key = 0; // 키 값 찾기 (키 갯수 1개)

    public void findKey(HashMap<Integer, Object> rMap) {
        for (int keyValue : readHashMap.keySet()) {
            key = keyValue;
        }
    }

    public int setLoginAndgetResult(String id, String pw) throws IOException{
        oos = new ObjectOutputStream(Myconn.socket.getOutputStream());
        LoginDTO dto = new LoginDTO();
        dto.setId(id);
        dto.setPw(pw);
        map.put(LOGIN, dto);
        oos.writeObject(map);
        oos.flush();

        ois = new ObjectInputStream(Myconn.socket.getInputStream());
        try {
            readHashMap = (HashMap<Integer, Object>) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        findKey(readHashMap);

        dto = (LoginDTO) readHashMap.get(key);
        int result = dto.getResult(); ;
        return result;
    }


    public String[] setCityAndGetState(String city) throws IOException {

        oos = new ObjectOutputStream(Myconn.socket.getOutputStream());
        LocationDTO resultDto = new LocationDTO();
        resultDto.setCity(city);
        HashSet<LocationDTO> dto = new HashSet<>();
        dto.add(resultDto);
        map.put(CITY, dto);
        oos.writeObject(map);
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
        map.put(STATE, dto);
        oos.writeObject(map);
        oos.flush();

        ois = new ObjectInputStream(Myconn.socket.getInputStream());
        try {
            readHashMap = (HashMap<Integer, Object>) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        findKey(readHashMap);
        ldtos = (HashSet<LocationDTO>) readHashMap.get(key);
        System.out.println(ldtos);
        String[] county = new String[ldtos.size()];
        Iterator<LocationDTO> it = ldtos.iterator();
        int i = 0;
        while (it.hasNext()) {
            county[i] = it.next().getCounty();
            i++;
        }
        return county;
    }

    public airDTO getWeatherAndAir(String city, String state, String county, String[] time, String[] temperature,
            String[] probPreci, String[] cloud) throws IOException {

        oos = new ObjectOutputStream(Myconn.socket.getOutputStream());
        LocationDTO resultDto = new LocationDTO();
        resultDto.setCity(city);
        resultDto.setState(state);
        resultDto.setCounty(county);
        HashSet<LocationDTO> dto = new HashSet<>();
        dto.add(resultDto);
        map.put(WEATHER, dto);
        oos.writeObject(map);
        oos.flush();

        ois = new ObjectInputStream(Myconn.socket.getInputStream());
        try {
            readHashMap = (HashMap<Integer, Object>) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        findKey(readHashMap);
        wdtos = (ArrayList<weatherDTO>) readHashMap.get(key);
        int i = 0;
        for (weatherDTO dtos : wdtos) {
            time[i] = dtos.getFcstTIme();
            temperature[i] = dtos.getFcstMap().get("T1H").toString();
            probPreci[i] = dtos.getFcstMap().get("REH").toString();
            cloud[i] = dtos.getFcstMap().get("SKY").toString();
            i++;
        }
        ois = new ObjectInputStream(Myconn.socket.getInputStream());
        try {
            readHashMap = (HashMap<Integer, Object>) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        findKey(readHashMap);
        airDTO adto = (airDTO) readHashMap.get(key);
        return adto;
    }


}
