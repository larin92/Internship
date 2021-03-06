package com.larin92.testtasks.internship;

import android.app.Application;
import android.content.Context;

import com.facebook.FacebookSdk;
import com.larin92.testtasks.internship.manager.ApiManager;
import com.larin92.testtasks.internship.manager.DatabaseManager;
import com.larin92.testtasks.internship.model.CardModel;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class App extends Application {

    private static Context sContext;
    private static volatile ApiManager sApiManager;
    private static volatile DatabaseManager sDataManager;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        FacebookSdk.sdkInitialize(getApplicationContext());
        CardModel.initQueries(getInWorkQuery(), getDoneQuery(), getWaitingQuery());

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public static ApiManager getApiManager() {
        if (sApiManager == null) {
            sApiManager = new ApiManager();
        }
        return sApiManager;
    }

    public static DatabaseManager getDatabaseManager() {
        if (sDataManager == null) {
            sDataManager = new DatabaseManager();
        }
        return sDataManager;
    }

    private List<Integer> getInWorkQuery() {
        List<Integer> queries = new ArrayList<>();
        queries.add(0);
        queries.add(9);
        queries.add(5);
        queries.add(7);
        queries.add(8);
        return queries;
    }

    private List<Integer> getDoneQuery() {
        List<Integer> queries = new ArrayList<>();
        queries.add(10);
        queries.add(6);
        return queries;
    }

    private List<Integer> getWaitingQuery() {
        List<Integer> queries = new ArrayList<>();
        queries.add(1);
        queries.add(3);
        queries.add(4);
        return queries;
    }

    public static Context getContext() {
        return sContext;
    }
}
