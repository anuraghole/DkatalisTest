package com.example.dkatalistest.repository.localdb.convertors;

import androidx.room.TypeConverter;

import com.example.dkatalistest.model.Location;
import com.example.dkatalistest.model.Name;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class DataConverter {
    @TypeConverter // note this annotation
    public String fromLocation(Location location) {
        if (location == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Location>() {
        }.getType();
        String json = gson.toJson(location, type);
        return json;
    }

    @TypeConverter // note this annotation
    public Location toLocation(String location) {
        if (location == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Location>() {
        }.getType();
        Location locationModel = gson.fromJson(location, type);
        return locationModel;
    }



    @TypeConverter // note this annotation
    public String fromLocation(Name name) {
        if (name == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Name>() {
        }.getType();
        String json = gson.toJson(name, type);
        return json;
    }


    @TypeConverter // note this annotation
    public Name toName(String name) {
        if (name == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Name>() {
        }.getType();
        Name nameModel = gson.fromJson(name, type);
        return nameModel;
    }

}
