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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import dto.earthquakeDTO;

public class eqkExplorer {

    public static int checkResult(String code) {
        switch (code) {
            case "01":
                System.out.println("어플리케이션 에러");
                return 1;
            case "02":
                System.out.println("데이터베이스 에러");
                return 1;
            case "03":
                System.out.println("데이터없음 에러");
                return 1;
            case "04":
                System.out.println("HTTP 에러"); return 1;
            case "05":
                System.out.println("서비스 연결실패 에러"); return 1;
            case "10":
                System.out.println("잘못된 요청 파라메터 에러"); return 1;
            case "11":
                System.out.println("필수요청 파라메터가 없음"); return 1;
            case "12":
                System.out.println("해당 오픈API서비스가 없거나 폐기됨"); return 1;
            case "20":
                System.out.println("서비스 접근거부"); return 1;
            case "21":
                System.out.println("일시적으로 사용할 수 없는 서비스 키"); return 1;
            case "22":
                System.out.println("서비스 요청제한횟수 초과에러"); return 1;
            case "30":
                System.out.println("등록되지 않은 서비스키"); return 1;
            case "31":
                System.out.println("기한만료된 서비스키"); return 1;
            case "32":
                System.out.println("등록되지 않은 IP"); return 1;
            case "33":
                System.out.println("서명되지 않은 호출"); return 1;
            case "99":
                System.out.println("기타에러");
                 return 1;
            default:
                break;
        }
        return 0;
    }
 
    public static earthquakeDTO getEqkAPI() throws IOException, ParseException {
        earthquakeDTO eqkDTO = new earthquakeDTO();

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
        String today = sdf.format(date);

        Date dDate = new Date();
        dDate = new Date(dDate.getTime()+(1000*60*60*24*-1));
        SimpleDateFormat dSdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
        String yesterday = dSdf.format(dDate);

        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/EqkInfoService/getEqkMsg"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=PVFskifGo6nMHGeSblPRth4Z1OOkDF4TxEgTx4S6wetjkJXm6ehtOZmG6sDbc649VzKBpv139smOCrNG%2FvQzZQ%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + URLEncoder.encode("-", "UTF-8")); /*공공데이터포털에서 받은 인증키*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON)*/
        urlBuilder.append("&" + URLEncoder.encode("fromTmFc","UTF-8") + "=" + URLEncoder.encode(yesterday, "UTF-8")); /*시간(년월일)*/
        urlBuilder.append("&" + URLEncoder.encode("toTmFc","UTF-8") + "=" + URLEncoder.encode(today, "UTF-8")); /*시간(년월일)*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
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
        String result= sb.toString();
    
    // Json parser를 만들어 만들어진 문자열 데이터를 객체화 
		JSONParser parser = new JSONParser(); 
		JSONObject obj = (JSONObject) parser.parse(result);
		// response 키를 가지고 데이터를 파싱 
		JSONObject parse_response = (JSONObject) obj.get("response"); 
        // response 로 부터 header 찾기
        JSONObject parse_header = (JSONObject) parse_response.get("header");

        String resultCode = parse_header.get("resultCode").toString();
        if(checkResult(resultCode)==1)
        {
            return eqkDTO;
        }

        // response 로 부터 body 찾기
		JSONObject parse_body = (JSONObject) parse_response.get("body"); 
		// body 로 부터 items 찾기 
		JSONObject parse_items = (JSONObject) parse_body.get("items");

		// items로 부터 itemlist 를 받기 
		JSONArray parse_item = (JSONArray) parse_items.get("item");

		JSONObject earthQuake; // parse_item은 배열형태이기 때문에 하나씩 데이터를 하나씩 가져올때 사용
		// 카테고리와 값만 받아오기
		for(int i = 0 ; i < parse_item.size(); i++) {
			earthQuake = (JSONObject) parse_item.get(i);
            Object fcTp = earthQuake.get("fcTp");
            Object img = earthQuake.get("img");
            Object intScale = earthQuake.get("inT");
            Object lat = earthQuake.get("lat");
            Object loc = earthQuake.get("loc");
            Object lon = earthQuake.get("lon");
            Object mt = earthQuake.get("mt");
            Object rem = earthQuake.get("rem");
            Object tmEqk = earthQuake.get("tmEqk");
            Object dep = earthQuake.get("dep");
            System.out.println("cnt : "+earthQuake.get("cnt"));
            System.out.println("fcTp : "+earthQuake.get("fcTp"));
            System.out.println("img : "+earthQuake.get("img"));
            System.out.println("inT : "+earthQuake.get("inT"));
            System.out.println("lat : "+earthQuake.get("lat"));
            System.out.println("loc : "+earthQuake.get("loc"));
            System.out.println("lon : "+earthQuake.get("lon"));
            System.out.println("mt : "+earthQuake.get("mt"));
            System.out.println("rem : "+earthQuake.get("rem"));
            System.out.println("stnId : "+earthQuake.get("stnId"));
            System.out.println("tmEqk : "+earthQuake.get("tmEqk"));
            System.out.println("tmFc : "+earthQuake.get("tmFc"));
            System.out.println("tmSeq : "+earthQuake.get("tmSeq"));
            System.out.println("dep : "+earthQuake.get("dep"));
            eqkDTO = new earthquakeDTO(fcTp.toString(), img.toString(), intScale.toString(), lat.toString(), loc.toString(), lon.toString(), mt.toString(), rem.toString(), tmEqk.toString(), dep.toString());
		}
        return eqkDTO;
        
    }

}