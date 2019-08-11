package com.umang.myapplication;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by umangagarwal on 11,August,2019
 */
@Entity(tableName = ModelClass.TABLE)
public class ModelClass {


    public static final String TABLE = "ITEMS";

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "cust_name")
    public String cust_name;

    @ColumnInfo(name = "locations")
    public ArrayList<Locations> locations;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    public ArrayList<Locations> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<Locations> locations) {
        this.locations = locations;
    }


    public static class Locations implements Parcelable {

        String place;
        String url;
        String date;
        double rate;
        String description;

        protected Locations(Parcel in) {
            place = in.readString();
            url = in.readString();
            date = in.readString();
            rate = in.readDouble();
            description = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(place);
            dest.writeString(url);
            dest.writeString(date);
            dest.writeDouble(rate);
            dest.writeString(description);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<Locations> CREATOR = new Creator<Locations>() {
            @Override
            public Locations createFromParcel(Parcel in) {
                return new Locations(in);
            }

            @Override
            public Locations[] newArray(int size) {
                return new Locations[size];
            }
        };
    }
}
