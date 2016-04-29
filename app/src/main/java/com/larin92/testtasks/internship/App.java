package com.larin92.testtasks.internship;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by larin92 on 20.04.2016.
 */
public class App extends Application {
    final static String TAG = "App class";
    static List<CardModel> inWork;
    static List<CardModel> done;
    static List<CardModel> waiting;

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
                return 3;
        }
    }

    //  operations with data will be moved elsewhere
    //  when we'll stop working with mocks
    public void createData(Context context) {
        List<String> urls;
        List<String> urls0 = new ArrayList<>(Arrays.
                asList(context.getResources().
                        getStringArray(R.array.urls0)));
        List<String> urls1 = new ArrayList<>(Arrays.
                asList(context.getResources().
                        getStringArray(R.array.urls1)));
        List<String> urls2 = new ArrayList<>(Arrays.
                asList(context.getResources().
                        getStringArray(R.array.urls2)));
        Resources res = context.getResources();
        String[] mock0 = res.getStringArray(R.array.mock0);
        String[] mock1 = res.getStringArray(R.array.mock1);
        String[] mock2 = res.getStringArray(R.array.mock2);
        int status;
        int category;
        String description;
        String title = context.getString(R.string.mockTitle);
        for (int i = 0; i < 20; i++) {
            CardModel cardModel = null;
            status = i % 3;
            category = status;
            //  this looks kinda messy, but it's mocks
            //  with real data we'll use readable setters like one below
            switch (status) {
                case 0:
                    urls = urls0;
                    description = context.getString(R.string.description);
                    cardModel = new CardModel(category, description, mock0[5], mock0[1], mock0[2], mock0[3], i, status, urls, mock0[4], 14, title + mock0[0]);
                    break;
                case 1:
                    urls = urls1;
                    cardModel = new CardModel(category, mock1[6], mock1[1], mock1[2], mock1[3], mock1[4], 9999, status, urls1, mock1[5], -999, title + mock1[0]);
                    break;
                case 2:
                    urls = urls2;
                    cardModel = new CardModel(category, mock2[1], mock2[2], mock2[3], mock2[4], mock2[5], 1234, status, urls2, mock2[6], 999, title + mock2[0]);
                    break;
            }
            addData(cardModel);
        }
    }
    /**
     * To use with not-mocks

     cardModel = new CardModel()
             .setCategory(category)
             .setDescription(description)
             .setAddress(address)
             .setDateCreated(dateCreated)
             .setDateRegistered(dateRegistered)
             .setDateResolveTo(dateResolveTo)
             .setLikes(likes)
             .setStatus(status)
             .setUrls(urls)
             .setResponsible(responsible)
             .setTitle(title);
     */
}
