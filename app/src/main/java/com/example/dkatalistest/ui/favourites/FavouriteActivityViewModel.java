package com.example.dkatalistest.ui.favourites;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dkatalistest.model.ResponseModel;
import com.example.dkatalistest.model.User;
import com.example.dkatalistest.repository.Repository;
import com.example.dkatalistest.ui.main.MainActivityViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FavouriteActivityViewModel extends ViewModel {
    public static final String TAG = FavouriteActivityViewModel.class.getSimpleName();
    private Repository repository;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MutableLiveData<List<User>> liveUserList = new MutableLiveData<>();


    public FavouriteActivityViewModel() {
        this.repository = new Repository();
    }

    public MutableLiveData<List<User>> getLiveUserList() {
        return liveUserList;
    }

    public void getFavouriteUserList() {
        Disposable disposableGetCategory = repository.getFavouriteList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userList -> {
                            liveUserList.postValue(userList);
                            Log.i(TAG, "observeDBLikedContentList: subscribe" + userList);
                        }
                        , throwable -> {
                            Log.i(TAG, "observeDBLikedContentList: thorwable");
                        });
        compositeDisposable.add(disposableGetCategory);
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }

}
