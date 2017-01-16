package kr.ac.mju.hanmaeum.utils;

/**
 * Created by Youthink on 2017-01-09.
 */
public class Constants {
    // Navigation Drawer
    // 셔틀버스
    public static final int SHUTTLE_BUS = 1;
    // 시외버스
    public static final int INTERCITY_BUS = 2;
    // 터미널버스
    public static final int KOBUS = 3;
    // 길찾기
    public static final int LOAD_SEARCH = 4;
    // 택시
    public static final int WITH_TAXI = 5;
    // 지하철
    public static final int SUBWAY = 6;
    // 검색기록
    public static final int SEARCH_LOG = 7;

    // Fragment Key
    public static final String FRAGMENT_KEY = "fragment_key";


    /* URL */
    public static final String NOTICE_URL = "http://jw4.mju.ac.kr/user/boardList.action?boardId=28510945&page=1";

    /* JSOUP PARSING TAG */
    public static final String TITLE_ELEMENT = "td[class=title]";
    public static final String NUMBER_ELEMENT = "td[class=no]";
    public static final String TIMESTAMP_ELEMENT = "td:eq(3)";
    public static final String CHECK_URL_ELEMENT = "td > a[href]";
    public static final String HREF_ELEMENT = "abs:href";
}
