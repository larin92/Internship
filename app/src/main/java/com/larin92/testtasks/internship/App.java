package com.larin92.testtasks.internship;

import android.app.Application;
import android.content.Context;

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
                return 3;
        }
    }

    public void createData(Context context) { //this is only a mockup, so yea, hardcode
        List<String> urls;
        List<String> urls1 = new ArrayList<>(Arrays.
                asList(context.getResources().
                        getStringArray(R.array.urls)));
        List<String> urls2 = new ArrayList<>(Arrays.
                asList(context.getResources().
                        getStringArray(R.array.urls2)));
        List<String> urls3 = new ArrayList<>(Arrays.
                asList(context.getResources().
                        getStringArray(R.array.urls3)));
        int status;
        String category;
        String description;
        String title="Some juicy Astrophysics";
        for (int i = 0; i < 20; i++) {
            CardModel cardModel;
            status = i % 3;
            switch (status) {
                case 0:
                    urls = urls1;
                    category = "Black Hole";
                    description = context.getString(R.string.description);
                    cardModel = new CardModel(ID, category, description, "улица Пушкина, дом Колотушкина", "Someday", "a long long time ago", "nobody solved it", i, status, urls, "The Creator", 14, title + ": Part 1");
                    break;
                case 1:
                    urls = urls2;
                    category = "Neutron Star";
                    cardModel = new CardModel(ID, category, "Neutron star is like one big atom, the size of the mountain, it's mass is so concentrated, that on the inside it is a big bowl of a gluon soup", "Milky way", "1967", "1974", "2008", 9999, status, urls2, "Nobody knows", -999, title + ": Part 2");
                    break;
                case 2:
                    urls = urls3;
                    category = "Supernova Explosion";
                    cardModel = new CardModel(ID, category, "When supernova explodes, it shines brighter than the whole galaxy it was located in. Supernovas give birth to heavy elements, we're all consist of. We're all made of stars", "Andromeda", "185 A.D.", "1885", "2009", 1234, status, urls3, "Who asks this stuff", 999, title + ": Part 3");
                    break;
                default:
                    urls = urls1;
                    status = 3;
                    category = "Black Matter";
                    cardModel = new CardModel(ID, category, "Well, actually nobody knows what this is", "Milky way", "1967", "1974", "2008", 9999, status, urls2, "Nobody knows", -999, title + ": Part 2");
            }
            ID++;
            addData(cardModel);
        }
    }
}
