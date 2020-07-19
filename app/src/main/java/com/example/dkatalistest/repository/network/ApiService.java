package com.example.dkatalistest.repository.network;


import com.example.dkatalistest.model.ResponseModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("0.4/")
    Observable<ResponseModel> getResponse(@Query("randomapi") String query);

}