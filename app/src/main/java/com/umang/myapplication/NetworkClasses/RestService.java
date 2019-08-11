package com.umang.myapplication.NetworkClasses;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.concurrent.TimeUnit;

/**
 * Created by umangagarwal on 20,July,2019
 */

public class RestService {

    private static APIModule APIModule;

    public static APIModule getRestClient() {
        if (APIModule != null) {
            return APIModule;
        }

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(10, TimeUnit.SECONDS);
        httpClient.readTimeout(60, TimeUnit.SECONDS);
        httpClient.writeTimeout(30, TimeUnit.SECONDS);

        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl("https://abc.com")
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        APIModule = restAdapter.create(APIModule.class);

        return APIModule;
    }

}