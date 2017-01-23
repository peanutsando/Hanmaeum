package kr.ac.mju.hanmaeum.utils.object.subway;

/**
 * Created by Youthink on 2017-01-22.
 */

public class Subway {

    /**
     * STATION_CD : 역번호
     * STATION_NM : 역이름
     * LINE_NUM : 호선선
     */
    private long STATION_CD;
    private String STATION_NM;
    private String LINE_NUM;
    private String FR_CODE;
    private int subway_images;

    public Subway(String FR_CODE, String LINE_NUM, long STATION_CD, String STATION_NM) {
        this.FR_CODE = FR_CODE;
        this.LINE_NUM = LINE_NUM;
        this.STATION_CD = STATION_CD;
        this.STATION_NM = STATION_NM;
    }

    public String getFR_CODE() {
        return FR_CODE;
    }

    public void setFR_CODE(String FR_CODE) {
        this.FR_CODE = FR_CODE;
    }

    public String getLINE_NUM() {
        return LINE_NUM;
    }

    public void setLINE_NUM(String LINE_NUM) {
        this.LINE_NUM = LINE_NUM;
    }

    public long getSTATION_CD() {
        return STATION_CD;
    }

    public void setSTATION_CD(long STATION_CD) {
        this.STATION_CD = STATION_CD;
    }

    public String getSTATION_NM() {
        return STATION_NM;
    }

    public void setSTATION_NM(String STATION_NM) {
        this.STATION_NM = STATION_NM;
    }

    public int getSubway_images() {
        return subway_images;
    }

    public void setSubway_images(int subway_images) {
        this.subway_images = subway_images;
    }
}
