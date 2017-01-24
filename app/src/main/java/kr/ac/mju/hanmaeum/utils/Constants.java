package kr.ac.mju.hanmaeum.utils;

import kr.ac.mju.hanmaeum.R;

/**
 * Modified by Jinhyeon Park on 2017-01-21.
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
    public static final String SHUTTLE_URL = "http://www.mju.ac.kr/mbs/mjukr/campusmap/HT5/CampusMap_yi_02_sub03.jsp";

    /* NOTICE ACTIONBAR TITLE*/
    public static final String ACTION_TITLE = "공지사항";

    /* NoticeContent GET INTENT DATA */
    public static final String URL = "URL";
    public static final String TIMESTAMP = "TIMESTAMP";
    public static final String TITLE = "TITLE";

    /* JSOUP PARSING TAG */
    public static final String TITLE_ELEMENT = "td[class=title]";
    public static final String NUMBER_ELEMENT = "td[class=no]";
    public static final String TIMESTAMP_ELEMENT = "td:eq(3)";
    public static final String CHECK_URL_ELEMENT = "td > a[href]";
    public static final String HREF_ELEMENT = "abs:href";
    public static final String DIVVIEW = "#divView";
    public static final String DIVVIEW_IMG = "#divView > img";
    public static final String DIVVIEW_P_IMG = "#divView > p > img";
    public static final String IMG_ATTR = "src";

    /* Glide Picture Size */
    public static final int GLIDE_WIDTH = 700;
    public static final int GLIDE_HEIGHT = 700;

    /* HTML TAG */
    public static final String BR = "<br />";

    /* Intent CODE for notice */
    public static final int ACTIVITY_REQUEST_CODE = 1;

    /* NOTICE_CONTENT_PARAMS*/
    public static final String NOTICE_TITLE = "제목 : ";
    public static final String NOTICE_TIMESTAMP = "작성일 : ";

    public static final String SHUTTLE_TABLE_ELEMENT = "tr";

    /* Subway */
    public static final String SUBWAY_INFO_KEY = "465144726e7935753130344f704d5946";
    public static String SUBWAY_INFO_URL = "http://openAPI.seoul.go.kr:8088/";
    public static String SUBWAY_INFO_PARAMS = "/json/SearchSTNBySubwayLineService/";

    public static String SUBWAY_ARRIVAL_INFO_KEY = "484e444c6379357538354152775566";
    public static String SUBWAY_ARRIVAL_INFO_URL = "http://swopenapi.seoul.go.kr/api/subway/";
    public static String SUBWAY_ARRIVAL_INFO_PARAMS = "/json/realtimeStationArrival/";

    public static final String[] SUBWAY_LINE_KEY = {
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "I", "K", "B", "A", "G", "S", "SU"
    };
    public static final int[] SUBWAY_LIEN_IMAGE = {
            R.drawable.line1,
            R.drawable.line2,
            R.drawable.line3,
            R.drawable.line4,
            R.drawable.line5,
            R.drawable.line6,
            R.drawable.line7,
            R.drawable.line8,
            R.drawable.line9,
            R.drawable.line_in,
            R.drawable.line_kj,
            R.drawable.line_b,
            R.drawable.line_air,
            R.drawable.line_g,
            R.drawable.line_sin,
            R.drawable.line_su,
    };

    /* Info */
    public static final String WEATHER_KEY = "21d42754e1e68e44f1282daac7b1576f";
    public static final String WEATHER_URL = "http://api.openweathermap.org/";
}
