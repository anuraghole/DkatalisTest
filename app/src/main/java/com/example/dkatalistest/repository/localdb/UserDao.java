package com.example.dkatalistest.repository.localdb;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.example.dkatalistest.model.User;

import java.util.List;

import io.reactivex.Observable;

@Dao
public interface UserDao {

    @Query("SELECT * FROM User LIMIT :limitCount OFFSET:offset")
    public Observable<List<User>> getUsersPaging(int offset, int limitCount);

    @Query("SELECT * FROM User WHERE User.isFavourite = 1")
    public Observable<List<User>> getFavouriteList();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertUsers(User user);
}
