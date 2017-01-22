package kr.ac.mju.hanmaeum.utils.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import kr.ac.mju.hanmaeum.utils.Constants;
import kr.ac.mju.hanmaeum.utils.subway.SearchSTNBySubwayLineService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Youthink on 2017-01-22.
 */

public class SubwayService {

    private static final String BASE_URL = Constants.NORMAL_URL + Constants.SUBWAY_KEY + Constants.KEY;

    public static Object retrofit(Class<?> className) {
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(HttpLoggingIntercept())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(className);
    }

    public static ListAPI api() {
        return (ListAPI) retrofit(ListAPI.class);
    }

    public interface ListAPI {
        @GET("1/200/{id}/")
        Call<SearchSTNBySubwayLineService> getSubwayInfo(@Path("id") String id);
    }

    public static OkHttpClient HttpLoggingIntercept(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }
}
