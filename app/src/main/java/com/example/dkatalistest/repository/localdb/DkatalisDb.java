package com.example.dkatalistest.repository.localdb;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.dkatalistest.model.User;
import com.example.dkatalistest.repository.localdb.convertors.DataConverter;


@Database(version = 1 , exportSchema = false , entities = {User.class})
@TypeConverters(DataConverter.class)
public abstract  class DkatalisDb extends RoomDatabase {

    public static final String DB_NAME = "dkatalis_db";

    public abstract UserDao getUserDao();

}
