package com.example.dkatalistest.ui.favourites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;

import com.example.dkatalistest.R;
import com.example.dkatalistest.databinding.ActivityFavouriteBinding;
import com.example.dkatalistest.model.User;
import com.example.dkatalistest.ui.BaseActivity;
import com.example.dkatalistest.ui.favourites.adapter.FavUserListAdapter;

import java.util.ArrayList;
import java.util.List;

public class FavouriteListActivity extends BaseActivity {
    public static final String TAG = FavouriteListActivity.class.getSimpleName();
    private FavouriteActivityViewModel viewModel;
    private ActivityFavouriteBinding binding;
    private FavUserListAdapter favUserListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_favourite);
        viewModel = ViewModelProviders.of(this).get(FavouriteActivityViewModel.class);
        favUserListAdapter=new FavUserListAdapter(this,new ArrayList<User>());
        binding.recyclerView.setAdapter(favUserListAdapter);
        viewModel.getFavouriteUserList();
        setObserver();

    }

    private void setObserver() {
        viewModel.getLiveUserList().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> userList) {
                if (userList!=null){
                    favUserListAdapter.setUserList(userList);
                }
                Log.i(TAG, "onChanged: "+userList);
            }
        });
    }
}