package com.larin92.testtasks.internship.manager;

import com.larin92.testtasks.internship.api.ApiSettings;
import com.larin92.testtasks.internship.api.services.ApiService;
import com.larin92.testtasks.internship.model.json.Model;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;


public class ApiManager implements Manager {


    private static volatile ApiService sApiService;
    private static final int sBatch = 20;

    static {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiSettings.SERVER)
                .build();
        sApiService = retrofit.create(ApiService.class);
    }

    @Override
    public void clear() {

    }

    public int getBatchSize() {
        return sBatch;
    }

    public Observable<List<Model>> update(String status, int offset) {
        return sApiService.getCards(status, sBatch + offset);
    }

    public Observable<List<Model>> getBatch(String status, int offset) {
        return sApiService.getCards(status, sBatch, offset);
    }

    public Observable<List<Model>> getAll(String status) {
        return sApiService.getCards(status);
    }
}
