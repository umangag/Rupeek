package com.umang.myapplication.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.umang.myapplication.ModelClass;

import java.util.List;

/**
 * Created by umangagarwal on 11,August,2019
 */

@Dao
public interface ItemsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertItems(ModelClass modelClass);

    @Delete
    int deleteItems(ModelClass modelClass);

    @Query("SELECT * FROM " + ModelClass.TABLE)
    ModelClass getItem();

}
