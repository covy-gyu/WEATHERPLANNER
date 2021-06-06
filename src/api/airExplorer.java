package api;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.sql.*;

public class AirExplorer {
    public static String[] airApi(String stationLoc) throws IOException, ParseException, SQLException {


        StringBuilder urlBuilder = new StringBuilder("http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=fkfZWCi2%2BQaJ2hdPhd6%2BA1kA%2Bw9BaUkxLjZF8MQgsBQp6cFpJRnnknK%2BfThnr%2B4ZsifbIZwMWA3gdfROjitazQ%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("serviceKey","UTF-8") + "=" + URLEncoder.encode("인증키(URL Encode)", "UTF-8")); /*공공데이터포털에서 받은 인증키*/
        urlBuilder.append("&" + URLEncoder.encode("_returnType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*xml 또는 json*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("stationName","UTF-8") + "=" + URLEncoder.encode(stationLoc, "UTF-8")); /*측정소 이름*/
        urlBuilder.append("&" + URLEncoder.encode("dataTerm","UTF-8") + "=" + URLEncoder.encode("DAILY", "UTF-8")); /*요청 데이터기간(1일: DAILY, 1개월: MONTH, 3개월: 3MONTH)*/
        urlBuilder.append("&" + URLEncoder.encode("ver","UTF-8") + "=" + URLEncoder.encode("1.3", "UTF-8")); /*버전별 상세 결과 참고*/
        URL url = new URL(urlBuilder.toString());
        //System.out.print(url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
       // System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        String result = sb.toString();
        //System.out.println(result);

        // Json parser를 만들어 만들어진 문자열 데이터를 객체화
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(result);
        // response 키를 가지고 데이터를 파싱
        JSONArray parse_list = (JSONArray) obj.get("list");
//        // response 로 부터 body 찾기
//        JSONObject parse_body = (JSONObject) parse_list.get("body");
//        // body 로 부터 items 찾기
//        JSONObject parse_items = (JSONObject) parse_body.get("items");
//
//        // items로 부터 itemlist 를 받기
//        JSONArray parse_item = (JSONArray) parse_items.get("item");
        String category;
        JSONObject air; // parse_item은 배열형태이기 때문에 하나씩 데이터를 하나씩 가져올때 사용
        // 카테고리와 값만 받아오기
        
        String[] resultValue = new String[6];
        for (int i = 0; i < parse_list.size(); i++) {
            air = (JSONObject) parse_list.get(i);
            Object dataTime = air.get("dataTime");
            Object o3Value = air.get("o3Value");
            Object pm10Value = air.get("pm10Value");
            Object pm25Value = air.get("pm25Value");
            Object o3Grade = air.get("o3Grade");
            Object pm10Grade1h = air.get("pm10Grade1h");
            Object pm25Grade1h = air.get("pm25Grade1h");


            System.out.print("\tdataTime : " + dataTime);
            System.out.print(", o3Value : " + o3Value);
            System.out.print(", pm10Value : " + pm10Value);
            System.out.print(", pm25Value : " + pm25Value);
            System.out.print(", o3Grade : " + o3Grade);
            System.out.print(", pm10Grade1h : " + pm10Grade1h);
            System.out.println(", pm25Grade1h : " + pm25Grade1h);

            resultValue[0] = pm10Value.toString();
            resultValue[1] = pm25Value.toString();
            resultValue[2] = o3Value.toString();
            resultValue[3] = pm10Grade1h.toString();
            resultValue[4] = pm25Grade1h.toString();
            resultValue[5] = o3Grade.toString();
        }

        return resultValue;
    }
}