package com.larin92.testtasks.internship;

import android.app.Application;
import android.content.Context;

import com.facebook.FacebookSdk;
import com.larin92.testtasks.internship.data.model.CardModel;

import java.util.ArrayList;

/**
 * Created by larin92 on 20.04.2016.
 */
public class App extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        FacebookSdk.sdkInitialize(getApplicationContext());
        CardModel.initQueries(getInWorkQuery(), getDoneQuery(), getWaitingQuery());
    }

    private ArrayList<Integer> getInWorkQuery() {
        ArrayList<Integer> queries = new ArrayList<>();
        queries.add(0);
        queries.add(9);
        queries.add(5);
        queries.add(7);
        queries.add(8);
        return queries;
    }

    private ArrayList<Integer> getDoneQuery() {
        ArrayList<Integer> queries = new ArrayList<>();
        queries.add(10);
        queries.add(6);
        return queries;
    }

    private ArrayList<Integer> getWaitingQuery() {
        ArrayList<Integer> queries = new ArrayList<>();
        queries.add(1);
        queries.add(3);
        queries.add(4);
        return queries;
    }

    public static Context getContext() {
        return mContext;
    }
}
