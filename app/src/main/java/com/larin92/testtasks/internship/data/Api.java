package com.larin92.testtasks.internship.data;

import com.larin92.testtasks.internship.data.model.json.Model;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


public class Api {

    private final static String ENDPOINT = "http://dev-contact.yalantis.com/rest/v1/";

    private static volatile Api mApi = null;
    private static volatile ApiService mApiService = null;
    private static final int mBatch = 20;

    public interface ApiService {

        @GET("tickets")
        Observable<List<Model>> getCards(@Query("state") String status);

        @GET("tickets")
        Observable<List<Model>> getCards(@Query("state") String status,
                                         @Query("amount") int amount);

        @GET("tickets")
        Observable<List<Model>> getCards(@Query("state") String status,
                                         @Query("amount") int amount,
                                         @Query("offset") int offset);
    }

    public static Api please() {
        if (mApi != null)
            return mApi;
        mApi = new Api();
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ENDPOINT)
                .build();
        mApiService = retrofit.create(ApiService.class);
        return mApi;
    }

    public int getBatchSize() {
        return mBatch;
    }

    public Observable<List<Model>> update(String status, int offset) {
        return mApiService.getCards(status, offset + mBatch);
    }

    public Observable<List<Model>> getBatch(String status, int offset) {
        return mApiService.getCards(status, mBatch, offset);
    }

    public Observable<List<Model>> getAll(String status) {
        return mApiService.getCards(status);
    }
}
