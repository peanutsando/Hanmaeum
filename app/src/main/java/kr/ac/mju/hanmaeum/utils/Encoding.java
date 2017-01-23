package kr.ac.mju.hanmaeum.utils;

import android.util.Log;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by Youthink on 2017-01-23.
 */

public class Encoding {
    private static final String TAG = "Encoder Error";
    private static final String CHARSET = "utf-8";

    public static String encodeResult(String url) {
        String result = url;
        try {
            result = URLEncoder.encode(url, CHARSET);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return result;
    }

    public static String decodeResult(String url) {
        String result = url;
        try {
            result = URLDecoder.decode(url, CHARSET);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return result;
    }
}
