package kr.ac.mju.hanmaeum.utils.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import kr.ac.mju.hanmaeum.utils.Constants;
import kr.ac.mju.hanmaeum.utils.object.InterCity.Page;
import okhttp3.ConnectionPool;
import okhttp3.Dispatcher;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Youthink on 2017-02-04.
 */

public class InterCityService {
    private static final String BASE_URL = Constants.INTERCITY_INFO_URL;

    public static Object retrofit(Class<?> className) {
        Dispatcher dispatcher = new Dispatcher(Executors.newFixedThreadPool(20));
        dispatcher.setMaxRequests(20);
        dispatcher.setMaxRequestsPerHost(1);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .dispatcher(dispatcher)
                .connectionPool(new ConnectionPool(100, 30, TimeUnit.SECONDS))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(PageAdapter.FACTORY)
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    public static ListAPI interCityInfoApi() {
        return (ListAPI) retrofit(ListAPI.class);
    }

    public interface ListAPI {
        @GET("/")
        Call<List<Page>> getInterCityInfo(@Query("searchName") String searchName);
    }

    static final class PageAdapter implements Converter<ResponseBody, ArrayList<Page>> {

        static final Converter.Factory FACTORY = new Converter.Factory() {
            @Override
            public Converter<ResponseBody, ?> responseBodyConverter(
                    Type type, Annotation[] annotations, Retrofit retrofit) {
                if (type == Page.class) return new PageAdapter();
                return null;
            }
        };

        @Override public ArrayList<Page> convert(ResponseBody value) throws IOException {
            Document document = Jsoup.parse(value.string());

            ArrayList<Page> pageArrayList = new ArrayList<>();

            List<String> stationName = new ArrayList<>();
            for (Element element : document.select("")) {
                stationName.add(element.attr(""));
            }

            List<String> busLocation = new ArrayList<>();
            for (Element element : document.select("")) {
                busLocation.add(element.attr(""));
            }

            for (int i = 0; i < stationName.size(); i++) {
                pageArrayList.add(new Page(stationName.get(i), busLocation.get(i)));
            }

            return pageArrayList;
        }
    }
}
