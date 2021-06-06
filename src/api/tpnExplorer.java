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

import dto.TyphoonDTO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class tpnExplorer {

    
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

    public static TyphoonDTO getTpnAPI() throws IOException, ParseException {
        TyphoonDTO tpnDTO = new TyphoonDTO();

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
        String today = sdf.format(date);

        Date dDate = new Date();
        dDate = new Date(dDate.getTime()+(1000*60*60*24*-1));
        SimpleDateFormat dSdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
        String yesterday = dSdf.format(dDate);

        StringBuilder urlBuilder = new StringBuilder(
                "http://apis.data.go.kr/1360000/TyphoonInfoService/getTyphoonInfo"); /* URL */
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8")
                + "=PVFskifGo6nMHGeSblPRth4Z1OOkDF4TxEgTx4S6wetjkJXm6ehtOZmG6sDbc649VzKBpv139smOCrNG%2FvQzZQ%3D%3D"); /*
                                                                                                                       * Service
                                                                                                                       * Key
                                                                                                                       */
        urlBuilder.append("&" + URLEncoder.encode("ServiceKey", "UTF-8") + "="
                + URLEncoder.encode("-", "UTF-8")); /* 공공데이터포털에서 받은 인증키 */
        urlBuilder
                .append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지번호 */
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
                + URLEncoder.encode("10", "UTF-8")); /* 한 페이지 결과 수 */
        urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "="
                + URLEncoder.encode("JSON", "UTF-8")); /* 요청자료형식(XML/JSON)Default: XML */
        urlBuilder.append("&" + URLEncoder.encode("fromTmFc", "UTF-8") + "="
                + URLEncoder.encode(yesterday, "UTF-8")); /* 시간(년월일) */
        urlBuilder.append("&" + URLEncoder.encode("toTmFc", "UTF-8") + "="
                + URLEncoder.encode(today, "UTF-8")); /* 시간(년월일) */
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
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
            return tpnDTO;
        }

        // response 로 부터 body 찾기
        JSONObject parse_body = (JSONObject) parse_response.get("body");

        // body 로 부터 items 찾기
        JSONObject parse_items = (JSONObject) parse_body.get("items");

        // items로 부터 itemlist 를 받기
        JSONArray parse_item = (JSONArray) parse_items.get("item");

        JSONObject typoon; // parse_item은 배열형태이기 때문에 하나씩 데이터를 하나씩 가져올때 사용
        // 카테고리와 값만 받아오기
       
        for (int i = 0; i < parse_item.size(); i++) {
            typoon = (JSONObject) parse_item.get(i);
            Object img = typoon.get("img");
            Object tmFc = typoon.get("tmFc");
            Object typTm = typoon.get("typTm");
            Object typLat = typoon.get("typLat");
            Object typLon = typoon.get("typLon");
            Object typLoc = typoon.get("typLoc");
            Object typDir = typoon.get("typDir");
            Object typSp = typoon.get("typSp");
            Object typPs = typoon.get("typPs");
            Object typWs = typoon.get("typWs");
            Object typ15 = typoon.get("typ15");
            Object typ25 = typoon.get("typ25");
            Object typName = typoon.get("typName");
            Object typEn = typoon.get("typEn");
            Object rem = typoon.get("rem");
            Object other = typoon.get("other");
            System.out.println("태풍 경로 이미지 : " + typoon.get("img"));
            System.out.println("발표 시각 : " + typoon.get("tmFc"));
            System.out.println("태풍시각 : " + typoon.get("typTm"));
            System.out.println("위도 : " + typoon.get("typLat"));
            System.out.println("경도 : " + typoon.get("typLon"));
            System.out.println("태풍예상위치 : " + typoon.get("typLoc"));
            System.out.println("진행방향 : " + typoon.get("typDir"));
            System.out.println("이동속도 : " + typoon.get("typSp"));
            System.out.println("중심기압 : " + typoon.get("typPs"));
            System.out.println("최대풍속 : " + typoon.get("typWs"));
            System.out.println("강풍반경 : " + typoon.get("typ15"));
            System.out.println("폭풍반경 : " + typoon.get("typ25"));
            System.out.println("태풍이름(한글) : " + typoon.get("typName"));
            System.out.println("태풍이름(영문) : " + typoon.get("typEn"));
            System.out.println("참고사항 : " + typoon.get("rem"));
            System.out.println("비고 : " + typoon.get("other"));
            tpnDTO = new TyphoonDTO(img.toString(), tmFc.toString(), typTm.toString(), typLat.toString(), typLon.toString(), typLoc.toString(), typDir.toString(), typSp.toString(), typPs.toString(), typWs.toString(), typ15.toString(), typ25.toString(), typName.toString(), typEn.toString(), rem.toString(), other.toString());
        }
        return tpnDTO;
    }
}