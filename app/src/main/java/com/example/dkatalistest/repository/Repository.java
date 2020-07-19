package com.example.dkatalistest.repository;

import androidx.room.Room;

import com.example.dkatalistest.MyApplication;
import com.example.dkatalistest.model.ResponseModel;
import com.example.dkatalistest.model.ResultsItem;
import com.example.dkatalistest.model.User;
import com.example.dkatalistest.repository.localdb.DkatalisDb;
import com.example.dkatalistest.repository.network.ApiClient;
import com.example.dkatalistest.repository.network.ApiService;

import java.util.List;

import io.reactivex.Observable;

public class Repository {
    private ApiService apiService;
    private DkatalisDb dkatalisDb;

    public Repository() {
        this.apiService = ApiClient.getClient().create(ApiService.class);
        this.dkatalisDb = Room.databaseBuilder(MyApplication.getAppContext(),DkatalisDb.class,DkatalisDb.DB_NAME).build();
    }

    public Observable<ResponseModel> getResponse(String query) {
        return  apiService.getResponse(query);
    }

    public Observable<List<User>> getFavouriteList() {
        return  dkatalisDb.getUserDao().getFavouriteList();
    }
    public void addToFavourite(User user) {
        dkatalisDb.getUserDao().insertUsers(user);
    }
}
