package kr.ac.mju.hanmaeum.utils;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Arrays;

import kr.ac.mju.hanmaeum.R;
import kr.ac.mju.hanmaeum.utils.object.shuttle.ShuttleLocation;

/**
 * Modified by Jinhyeon Park on 2017-01-21.
 */

public class Constants {
    // Navigation Drawer
    // 셔틀버스
    public static final int SHUTTLE_BUS = 1;
    // 셔틀타는곳
    public static final int SHUTTLE_LOCATION = 2;
    // 시외버스
    public static final int INTERCITY_BUS = 3;
    // 터미널버스
    public static final int KOBUS = 4;
    // 길찾기
    public static final int LOAD_SEARCH = 5;
    // 지하철
    public static final int SUBWAY = 6;

    // Fragment Key
    public static final String FRAGMENT_KEY = "fragment_key";

    // Shuttle Vacation Key
    public static final int VACATION_KEY = 75;

    // Location Key
    public static final String LOCATION_LAT_KEY = "location_lat_key";
    public static final String LOCATION_LON_KEY = "location_lon_key";

    // Shuttle Location LatLng
    // 진입로
    public static final ShuttleLocation[] RAMP_SHUTTLE_LOCATION_LIST = {
            new ShuttleLocation(37.2242302, 127.1873092),
            new ShuttleLocation(37.231699, 127.188480),
            new ShuttleLocation(37.2358708, 127.1889518),
            new ShuttleLocation(37.2384788, 127.1896083),
            new ShuttleLocation(37.2341187, 127.1884465),
            new ShuttleLocation(37.231447, 127.188081),
            new ShuttleLocation(37.2221354, 127.1885939),
            new ShuttleLocation(37.2195511, 127.1833236)
    };
    // 시내
    public static final ShuttleLocation[] DOWNTOWN_SHUTTLE_LOCATION_LIST = {
            new ShuttleLocation(37.2242302, 127.1873092),
            new ShuttleLocation(37.231699, 127.188480),
            new ShuttleLocation(37.2358708, 127.1889518),
            new ShuttleLocation(37.234705, 127.198204),
            new ShuttleLocation(37.234988, 127.204369),
            new ShuttleLocation(37.233378, 127.208933),
            new ShuttleLocation(37.2341187, 127.1884465),
            new ShuttleLocation(37.231447, 127.188081),
            new ShuttleLocation(37.2221354, 127.1885939),
            new ShuttleLocation(37.222658, 127.186624),
            new ShuttleLocation(37.2195511, 127.1833236)
    };


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

    /* Subway Search */
    public static final String SUBWAY_SEARCH_INFO_URL = "http://swopenAPI.seoul.go.kr/api/subway/";
    public static final String SUBWAY_SEARCH_INFO_KEY = "4d54417a657935753131384471734d6e";
    public static final String SUBWAY_SEARCH_INFO_PARAMS = "/json/shortestRoute/";

    /* InterCity */
    public static final String INTERCITY_INFO_URL = "http://bus.go.kr/searchResult6.jsp?searchName=7,";

    /* Info */
    public static final String WEATHER_KEY = "21d42754e1e68e44f1282daac7b1576f";
    public static final String WEATHER_URL = "http://api.openweathermap.org/";
    public static final String WEATHER_ICON = "http://openweathermap.org/img/w/";

    /* Preference */
    public static final String DATABASE_CREATED_PREF = "created_db";
    public static final String DATABASE_INIT_PREF = "init_db";

    /* Database */
    public static final String BOOKMARK_DATABASE = "bookmark_db";

    /* Table */
    public static final String BOOKMARK_TABLE = "bookmark_table";

    /* Table Column */
    public static final String TABLE_COL_ID = "_id";
    public static final String TABLE_COL_TIME = "time";
    public static final String TABLE_COL_BOOKMARK = "bookmark";

    /* Query */
    public static final String CREATE_TABLE_QUERY = "create table if not exists "
            + BOOKMARK_TABLE + "("
            + TABLE_COL_ID + " varchar, "
            + TABLE_COL_TIME + " varchar, "
            + TABLE_COL_BOOKMARK + " boolean "
            + ");";

    /* InterCity */
    public static final String[] seoulIntercity = {
            "06:30", "06:50", "07:10", "07:40", "08:00", "08:30", "09:10", "09:50", "10:30", "11:00", "11:30", "12:10", "12:50", "13:30", "14:00", "14:30", "15:00",
            "15:30", "16:10", "16:40", "17:10", "17:40", "18:20", "19:00", "19:40", "20:20", "21:00", "21:40", "22:10"
    };

    public static final String[] gwangjuIntercity = {
            "06:50", "08:10", "09:20", "10:20", "11:40", "13:00", "14:10", "15:10", "16:30", "18:10", "19:10", "20:00"
    };

    public static final String[] changwonIntercity = {
            "07:10", "10:10", "13:10", "16:10", "19:00"
    };

    public static final String[] busanIntercity = {
            "07:00", "08:30", "10:00", "11:30", "13:00", "14:30", "16:00", "17:30", "18:40"
    };

    public static final String[] westBusanIntercity = {
            "07:40", "10:45", "15:20", "18:30"
    };

    public static final String[] ansanIntercity = {
            "06:30", "07:00", "08:30", "09:20", "10:25", "11:25", "12:30", "13:30", "14:40", "15:30", "16:30", "17:30", "18:20", "19:40", "20:30", "21:30"
    };

    public static final String[] jeonjuIntercit = {
            "07:40", "10:10", "11:15", "12:30", "13:45", "15:10", "16:15", "17:30", "18:45"
    };

    public static final String[] youngwolTaebakIntercity = {
            "07:00", "15:00"
    };

    public static final String[] dongDaeguIntercity = {
            "07:40", "09:10", "10:40", "12:10", "13:40", "15:10", "16:40", "18:10", "19:40"
    };

    public static final String[] wonjuIntercity = {
            "06:50", "07:40", "08:20", "10:30", "12:10", "14:30", "16:10", "18:10", "19:00", "19:50", "20:40"
    };

    public static final String[] gyeongjuPohangIntercity = {
            "08:00", "10:05", "15:00"
    };

    public static final String[] icheonYeojuIntercity = {
            "09:20", "12:50", "16:20", "19:50"
    };

    public static final String[] chungjuJeomchonSangjuIntercity = {
            "08:30", "15:40", "19:30"
    };

    public static final String[] sokchoIntercity = {
            "08:10", "13:10", "18:10"
    };

    public static final String[] gwangmyeongIntercity = {
            "06:00", "11:25", "17:30"
    };

    public static final String[] gunsanIntercity = {
            "06:40", "08:05", "10:45", "12:05", "14:45", "16:05", "18:50", "20:05"
    };

    public static final String[] jinjuIntercity = {
            "07:00", "08:30", "10:30", "12:30", "14:00", "16:10", "18:00", "19:20"
    };

    public static final String[] gimhaeIntercity = {
            "08:10", "11:30", "14:50", "18:20"
    };

    public static final String[] gimpoAirportIntercity = {
            "05:20", "06:00", "06:35", "07:20", "08:10", "09:10", "10:10", "10:55", "11:50", "12:40", "13:25", "14:20", "15:10", "15:50", "16:40", "17:35", "18:15", "18:55", "19:40", "20:30"
    };

    public static final String[] incheonAirportIntercity = {
            "04:15", "04:25", "04:55", "05:10", "05:25", "05:40", "05:55", "06:10", "06:25", "06:40", "06:55", "07:15", "07:40", "08:00", "08:20", "08:40", "09:00", "09:20", "09:20", "09:40",
            "10:00", "10:25", "10:45", "11:10", "11:30", "11:45", "12:10", "12:30", "12:50", "13:10", "13:30", "13:50", "14:10", "14:25", "14:40", "15:00", "15:20", "15:40", "16:00", "16:20",
            "16:35", "17:00", "17:20", "17:40", "18:00", "18:20", "18:50", "19:20", "19:50", "20:25", "21:00"
    };

    public static final String[] incheonIntercity = {
            "06:20", "06:50", "07:10", "08:00", "08:40", "09:00", "09:25", "09:50", "10:15", "10:35", "10:50", "11:05", "11:20", "11:35", "11:50", "12:05", "12:20", "12:45", "13:10", "13:35",
            "14:00", "14:25", "14:45", "15:05", "15:25", "15:40", "15:55", "16:10", "16:30", "16:45", "17:05", "17:25", "17:50", "18:15", "18:40", "19:25", "19:45", "20:05", "20:20", "20:35",
            "20:50", "21:05", "21:20", "21:35", "21:50", "22:00"
    };

    public static final String[] daejeonIntercity = {
            "06:30", "07:25", "08:35", "09:30", "10:30", "11:30", "12:25", "13:30", "14:35", "15:30", "16:30", "17:25", "18:30", "19:20", "20:20"
    };

    public static final String[] cheonanIntercity = {
            "06:20", "06:40", "07:20", "07:40", "09:25", "10:20", "11:30", "12:45", "13:40", "14:30", "15:20", "16:10", "17:00", "17:50", "18:35", "19:30", "20:10"
    };

    public static final String[] cheongjuIntercity = {
            "07:30", "09:10", "11:20", "13:10", "14:30", "16:10", "18:00", "19:10"
    };

    public static final String[] sejongYuseongIntercity = {
            "06:00", "12:00", "18:00"
    };

    public static final String[] gumiUlsanIntercity = {
            "08:40", "11:40", "14:40", "18:00"
    };

    public static final String[] gangneungIntercity = {
            "07:30", "09:00", "11:30", "13:40", "16:00"
    };

    public static final String[] goyangIntercity = {
            "07:00", "10:40", "13:30", "17:50", "20:20"
    };

    public static Object[] TERMINAL_LIST = new Object[]{
            seoulIntercity,
            gwangjuIntercity,
            changwonIntercity,
            busanIntercity,
            westBusanIntercity,
            ansanIntercity,
            jeonjuIntercit,
            youngwolTaebakIntercity,
            dongDaeguIntercity,
            wonjuIntercity,
            gyeongjuPohangIntercity,
            icheonYeojuIntercity,
            chungjuJeomchonSangjuIntercity,
            sokchoIntercity,
            gwangmyeongIntercity,
            gunsanIntercity,
            jinjuIntercity,
            gimhaeIntercity,
            gimpoAirportIntercity,
            incheonAirportIntercity,
            incheonIntercity,
            daejeonIntercity,
            cheonanIntercity,
            cheongjuIntercity,
            sejongYuseongIntercity,
            gumiUlsanIntercity,
            gangneungIntercity,
            goyangIntercity
    };
}
