package dto;

import java.io.Serializable;

public class TyphoonDTO implements Serializable {
    private static final long serialVersionUID = 1234567890L;

    private String img;
    private String tmFc;
    private String typTm;
    private String typLat;
    private String typLon;
    private String typLoc;
    private String typDir;
    private String typSp;
    private String typPs;
    private String typWs;
    private String typ15;
    private String typ25;
    private String typName;
    private String typEn;
    private String rem;
    private String other;

    public TyphoonDTO() {
    }

    public TyphoonDTO(String img, String tmFc, String typTm, String typLat, String typLon, String typLoc, String typDir,
            String typSp, String typPs, String typWs, String typ15, String typ25, String typName, String typEn,
            String rem, String other) {
        this.img = img;
        this.tmFc = tmFc;
        this.typTm = typTm;
        this.typLat = typLat;
        this.typLon = typLon;
        this.typLoc = typLoc;
        this.typDir = typDir;
        this.typSp = typSp;
        this.typPs = typPs;
        this.typWs = typWs;
        this.typ15 = typ15;
        this.typ25 = typ25;
        this.typName = typName;
        this.typEn = typEn;
        this.rem = rem;
        this.other = other;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTmFc() {
        return tmFc;
    }

    public void setTmFc(String tmFc) {
        this.tmFc = tmFc;
    }

    public String getTypTm() {
        return typTm;
    }

    public void setTypTm(String typTm) {
        this.typTm = typTm;
    }

    public String getTypLat() {
        return typLat;
    }

    public void setTypLat(String typLat) {
        this.typLat = typLat;
    }

    public String getTypLon() {
        return typLon;
    }

    public void setTypLon(String typLon) {
        this.typLon = typLon;
    }

    public String getTypLoc() {
        return typLoc;
    }

    public void setTypLoc(String typLoc) {
        this.typLoc = typLoc;
    }

    public String getTypDir() {
        return typDir;
    }

    public void setTypDir(String typDir) {
        this.typDir = typDir;
    }

    public String getTypSp() {
        return typSp;
    }

    public void setTypSp(String typSp) {
        this.typSp = typSp;
    }

    public String getTypPs() {
        return typPs;
    }

    public void setTypPs(String typPs) {
        this.typPs = typPs;
    }

    public String getTypWs() {
        return typWs;
    }

    public void setTypWs(String typWs) {
        this.typWs = typWs;
    }

    public String getTyp15() {
        return typ15;
    }

    public void setTyp15(String typ15) {
        this.typ15 = typ15;
    }

    public String getTyp25() {
        return typ25;
    }

    public void setTyp25(String typ25) {
        this.typ25 = typ25;
    }

    public String getTypName() {
        return typName;
    }

    public void setTypName(String typName) {
        this.typName = typName;
    }

    public String getTypEn() {
        return typEn;
    }

    public void setTypEn(String typEn) {
        this.typEn = typEn;
    }

    public String getRem() {
        return rem;
    }

    public void setRem(String rem) {
        this.rem = rem;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("태풍 경로 이미지 : " + img + " | ");
        sb.append("발표 시각 : " + tmFc + " | ");
        sb.append("태풍시각 : " + typTm + " | ");
        sb.append("위도 : " + typLat + " | ");
        sb.append("경도 : " + typLon + " | ");
        sb.append("태풍예상위치 : " + typLoc + " | ");
        sb.append("진행방향 : " + typDir + " | ");
        sb.append("이동속도 : " + typSp + " | ");
        sb.append("중심기압 : " + typPs + " | ");
        sb.append("최대풍속 : " + typWs + " | ");
        sb.append("강풍반경 : " + typ15 + " | ");
        sb.append("폭풍반경 : " + typ25 + " | ");
        sb.append("태풍이름(한글) : " + typName + " | ");
        sb.append("태풍이름(영문) : " + typEn + " | ");
        sb.append("참고사항 : " + rem + " | ");
        sb.append("비고 : " + other + " | ");
        return sb.toString();
    }
}
