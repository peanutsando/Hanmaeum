package kr.ac.mju.hanmaeum.utils.service;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Youthink on 2017-01-22.
 */

public class RestApiLogging {
    public static OkHttpClient HttpLoggingIntercept(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }
}
