package com.movie.movieapp;

import android.app.Application;

import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;
import com.orhanobut.hawk.LogLevel;

/**
 * Created by Erick Renata on 07/05/2016.
 */
public class MovieApp  extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initHawk();
    }

    private void initHawk(){
        Hawk.init(this)
                .setEncryptionMethod(HawkBuilder.EncryptionMethod.MEDIUM)
                .setStorage(HawkBuilder.newSqliteStorage(this))
                .setLogLevel(LogLevel.FULL)
                .build();
    }
}
