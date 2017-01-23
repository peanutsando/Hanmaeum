package kr.ac.mju.hanmaeum.utils.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import kr.ac.mju.hanmaeum.utils.Constants;
import kr.ac.mju.hanmaeum.utils.object.weather.Info;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Youthink on 2017-01-24.
 */

public class WeatherService {

    public static Object retrofit(Class<?> className) {
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.WEATHER_URL)
                .client(RestApiLogging.HttpLoggingIntercept())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(className);
    }

    public static ListAPI subwayInfoApi() {
        return (ListAPI) retrofit(ListAPI.class);
    }

    public interface ListAPI {
        @GET("data/2.5/weather")
        Call<Info> getWeatherInfo(@Query("lat") double lat, @Query("lon") double lon, @Query("APPID") String APPID);
    }
}
