package kr.ac.mju.hanmaeum.utils.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import kr.ac.mju.hanmaeum.utils.Constants;
import kr.ac.mju.hanmaeum.utils.object.subway.realtimeStationArrival;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Youthink on 2017-01-22.
 */

public class SubwayInfoService {
    private static final String BASE_URL = Constants.SUBWAY_ARRIVAL_INFO_URL
            + Constants.SUBWAY_ARRIVAL_INFO_KEY + Constants.SUBWAY_ARRIVAL_INFO_PARAMS;

    public static Object retrofit(Class<?> className) {
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(RestApiLogging.HttpLoggingIntercept())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(className);
    }

    public static ListAPI subwayInfoApi() {
        return (ListAPI) retrofit(ListAPI.class);
    }

    public interface ListAPI {
        @GET("1/30/{location}/")
        Call<realtimeStationArrival> getSubwayArrivalInfo(@Path("location") String location);
    }
}
