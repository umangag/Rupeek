package com.umang.myapplication.Database;

import android.os.AsyncTask;

import com.umang.myapplication.ModelClass;

import java.util.List;

/**
 * Created by umangagarwal on 11,August,2019
 */
public class DataAccess {

    public static void insertData(final ModelClass modelClass) {
        new BackgroundExecute().execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase.getInstance().itemsDAO().insertItems(modelClass);
            }
        });

    }

    public static void getItemsData(DataAccessWithResultListener<ModelClass> listener) {
        new GetItemsData(listener).execute();
    }

    public interface DataAccessWithResultListener<T> {
        void onSuccess(T result);
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

    private static class GetItemsData extends AsyncTask<Void, Void, ModelClass> {

        DataAccessWithResultListener<ModelClass> listener;

        private GetItemsData(DataAccessWithResultListener<ModelClass> listener) {
            this.listener = listener;
        }

        @Override
        protected ModelClass doInBackground(Void... voids) {
            return AppDatabase.getInstance().itemsDAO().getItem();
        }

        @Override
        protected void onPostExecute(ModelClass modelClass) {
            super.onPostExecute(modelClass);
            listener.onSuccess(modelClass);
        }
    }
}
