package com.larin92.testtasks.internship;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by larin92 on 20.04.2016.
 */
public class App {
    final static String TAG = "App class";
    static List<CardModel> inWork;
    static List<CardModel> done;
    static List<CardModel> waiting;
    static int ID = 0;

    public App() {
        inWork = new ArrayList<>();
        done = new ArrayList<>();
        waiting = new ArrayList<>();
    }

    public static List<CardModel> getInWork() {
        return inWork;
    }

    public static List<CardModel> getDone() {
        return done;
    }

    public static List<CardModel> getWaiting() {
        return waiting;
    }

    public int addData(CardModel cardModel) {
        switch (cardModel.getmStatus()) {
            case 0:
                inWork.add(cardModel);
                return 0;
            case 1:
                done.add(cardModel);
                return 1;
            case 2:
                waiting.add(cardModel);
                return 2;
            default:
                cardModel.setmStatus(3);
                waiting.add(cardModel);
                Log.v(TAG, "we've got an unclear status");
                return 3;
        }
    }

    public void createData(Context context) {
        List<String> urls = new ArrayList<>(Arrays.
                asList(context.getResources().
                        getStringArray(R.array.urls)));
        int status;
        String category;
        String description;
        for (int i = 0; i < 10; i++) {
            status = i % 3;
            switch (status) {
                case 0:
                    category = "Black Hole";
                    description = "Hello there";
                    break;
                case 1:
                    category = "Neutron Star";
                    description = context.getString(R.string.description);
                    break;
                case 2:
                    category = "Supernova Explosion";
                    description = context.getString(R.string.description);
                    break;
                default:
                    status = 3;
                    category = "Black Matter";
                    description = "cmon";
            }
            CardModel cardModel = new CardModel(ID, category, description, "улица Пушкина, дом Колотушкина", "I was created", "a long long time ago", "when Earth didn't even existed", i, status, urls, "The Creator", 14 );
            ID++;
            addData(cardModel);
        }
        Log.v(TAG, String.valueOf(inWork.size()));
        Log.v(TAG, String.valueOf(done.size()));
        Log.v(TAG, String.valueOf(waiting.size()));
    }
}
