package com.umang.myapplication.Database;

import android.app.Application;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umang.myapplication.ApplicationClass;
import com.umang.myapplication.ModelClass;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by umangagarwal on 11,August,2019
 */


@Database(entities = {ModelClass.class}, version = 1, exportSchema = false)
@TypeConverters({AppDatabase.Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    private static final String DB_NAME = "items.db";
    private static AppDatabase instance;

    public abstract ItemsDAO itemsDAO();

    public static synchronized AppDatabase getInstance() {
        if (instance == null) {
            instance = Room.databaseBuilder(ApplicationClass.getApp(), AppDatabase.class, DB_NAME).build();
        }
        return instance;
    }

    public static class Converters {
        @TypeConverter
        public  ArrayList<ModelClass.Locations> fromString(String value) {
            Type listType = new TypeToken<ArrayList<ModelClass.Locations>>() {}.getType();
            return new Gson().fromJson(value, listType);
        }

        @TypeConverter
        public  String fromArrayList(ArrayList<ModelClass.Locations> list) {
            Gson gson = new Gson();
            String json = gson.toJson(list);
            return json;
        }
    }
}
