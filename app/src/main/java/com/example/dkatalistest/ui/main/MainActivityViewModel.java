package com.example.dkatalistest.ui.main;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.dkatalistest.model.ResponseModel;
import com.example.dkatalistest.repository.Repository;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivityViewModel extends ViewModel {
    public static final String TAG = MainActivityViewModel.class.getSimpleName();
    private Repository repository;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MutableLiveData<ResponseModel> responseModelLiveData = new MutableLiveData<>();


    public MainActivityViewModel() {
        this.repository = new Repository();
    }

    public MutableLiveData<ResponseModel> getResponseModelLiveData() {
        return responseModelLiveData;
    }

    public void getUser() {
        Disposable disposableGetCategory = repository.getResponse("")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseModel -> {
                            Log.i(TAG, "observeDBLikedContentList: subscribe" + responseModel);
                            if (responseModel != null && responseModel.getResults() != null && !responseModel.getResults().isEmpty() && responseModel.getResults().get(0).getUser() != null) {
                                responseModelLiveData.postValue(responseModel);
                            }


                        }
                        , throwable -> {
                            Log.i(TAG, "observeDBLikedContentList: thorwable");
                        });
        compositeDisposable.add(disposableGetCategory);
    }

    public void addToFavourite() {
        if (responseModelLiveData != null && responseModelLiveData.getValue() != null && !responseModelLiveData.getValue().getResults().isEmpty() && responseModelLiveData.getValue().getResults().get(0).getUser() != null) {
            Disposable disposableGetCategory = Observable.just(responseModelLiveData.getValue().getResults().get(0).getUser())
                    .subscribeOn(Schedulers.io())
                    .doOnNext(user -> {
                        user.setFavourite(true);
                        repository.addToFavourite(user);
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(user -> {
                                Log.i(TAG, "observeDBLikedContentList: subscribe" + user);
                            }
                            , throwable -> {
                                Log.i(TAG, "observeDBLikedContentList: thorwable");
                            });
            compositeDisposable.add(disposableGetCategory);
        }

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }

}
