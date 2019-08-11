package com.umang.myapplication.NetworkClasses;

import com.umang.myapplication.ModelClass;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by umangagarwal on 20,July,2019
 */
public interface APIModule {

    @GET
    Call<ModelClass> getData(@Url String url);

}
