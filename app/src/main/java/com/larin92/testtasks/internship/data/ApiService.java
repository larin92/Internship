package com.larin92.testtasks.internship.data;

import com.larin92.testtasks.internship.data.model.json.Model;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

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
