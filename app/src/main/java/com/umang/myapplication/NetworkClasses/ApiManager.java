package com.umang.myapplication.NetworkClasses;

import android.os.AsyncTask;

import com.umang.myapplication.Database.AppDatabase;
import com.umang.myapplication.ModelClass;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by umangagarwal on 11,August,2019
 */
public class ApiManager {

    private ApiResponse apiResponse;

    public ApiManager(ApiResponse apiResponse) {
        this.apiResponse = apiResponse;
    }

    public void getData() {

        Call<ModelClass> searchDetails = RestService.getRestClient().getData("http://www.mocky.io/v2/5c261ccb3000004f0067f6ec");

        searchDetails.enqueue(new Callback<ModelClass>() {
            @Override
            public void onResponse(Call<ModelClass> call, final Response<ModelClass> response) {
                apiResponse.apiCallback(response.body());

                new BackgroundExecute().execute(new Runnable() {
                    @Override
                    public void run() {
                        AppDatabase.getInstance().itemsDAO().insertItems(response.body());
                    }
                });
            }

            @Override
            public void onFailure(Call<ModelClass> call, Throwable t) {
                System.out.println("ApiManager.onFailure::: "+t.getMessage());
            }
        });
    }

    private static class BackgroundExecute extends AsyncTask<Runnable, Void, Void> {
        @Override
        protected Void doInBackground(Runnable... tasks) {
            if (tasks != null) {
                for (Runnable task : tasks) {
                    task.run();
                }
            }

            return null;
        }


    }
}
