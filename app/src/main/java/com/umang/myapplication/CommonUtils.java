package com.umang.myapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by umangagarwal on 11,August,2019
 */
public class CommonUtils {

    public static boolean isOnline(Context app) {
        ConnectivityManager cm = (ConnectivityManager) app.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

}
