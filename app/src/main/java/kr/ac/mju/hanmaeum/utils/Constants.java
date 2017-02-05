package kr.ac.mju.hanmaeum.utils;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

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
}
