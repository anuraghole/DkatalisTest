package com.example.dkatalistest.ui.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.dkatalistest.R;
import com.example.dkatalistest.databinding.ActivityMainBinding;
import com.example.dkatalistest.model.Location;
import com.example.dkatalistest.model.Name;
import com.example.dkatalistest.model.ResponseModel;
import com.example.dkatalistest.model.User;
import com.example.dkatalistest.ui.BaseActivity;
import com.example.dkatalistest.ui.favourites.FavouriteListActivity;
import com.example.dkatalistest.util.Constant;
import com.example.dkatalistest.util.OnSwipeTouchListener;
import com.example.dkatalistest.util.ProgressUtils;


public class MainActivity extends BaseActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private MainActivityViewModel viewModel;
    private ActivityMainBinding binding;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        progressDialog = ProgressUtils.getProgressDialog(this, Constant.LOADING);
        setListener();
        setObserver();
        init();
        callApi();
        setTouchListener();
    }

    private void setTouchListener() {
        binding.cvMain.setOnTouchListener(new OnSwipeTouchListener(this){

            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                //Toast.makeText(MainActivity.this, "left", Toast.LENGTH_SHORT).show();
                viewModel.getUser();
            }

            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
              //  Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();
                viewModel.addToFavourite();
                viewModel.getUser();
            }

            @Override
            public void onSwipeTop() {
                super.onSwipeTop();
               // Toast.makeText(MainActivity.this, "top", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onSwipeBottom() {
                super.onSwipeBottom();
               // Toast.makeText(MainActivity.this, "bottom", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setObserver() {
        viewModel.getResponseModelLiveData().observe(this, new Observer<ResponseModel>() {
            @Override
            public void onChanged(ResponseModel responseModel) {
                if (responseModel != null && responseModel.getResults() != null && responseModel.getResults().get(0) != null) {
                    reset();
                    User user= responseModel.getResults().get(0).getUser();
                    Name name =user.getName();
                    String imgUrl=user.getPicture();
                    String strName = name.getTitle() + ". " + name.getFirst() + " " + name.getLast();
                    binding.setAbout("My name is");
                    binding.setDetail(strName);
                    if (user.getGender().equalsIgnoreCase("female")){
                        Glide.with(MainActivity.this).load(imgUrl).error(R.drawable.ic_female_profile_placeholder).placeholder(R.drawable.ic_female_profile_placeholder)
                                .into(binding.civProfile);
                    }else if (user.getGender().equalsIgnoreCase("male")){
                        Glide.with(MainActivity.this).load(imgUrl).error(R.drawable.ic_male_profile_placeholder).placeholder(R.drawable.ic_male_profile_placeholder)
                                .into(binding.civProfile);
                    }else {
                        Glide.with(MainActivity.this).load(imgUrl).error(R.drawable.ic_male_profile_placeholder).placeholder(R.drawable.ic_male_profile_placeholder)
                                .into(binding.civProfile);
                    }
                    Log.i(TAG, "onChanged: " + responseModel);
                } else {
                    Log.i(TAG, "onChanged: " + responseModel);
                }
            }
        });

    }

    private void setListener() {
        binding.ivName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNameIconClick();
            }
        });
        binding.ivCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCalendarIconClick();
            }
        });
        binding.ivLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLocationIconClick();
            }
        });
        binding.ivPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPhoneIconClick();
            }
        });
        binding.ivLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLockIconClick();
            }
        });

        binding.btnFavList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, FavouriteListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void reset() {
        binding.ivName.setImageDrawable(getDrawable(R.drawable.ic_name_selected));
        binding.ivCalender.setImageDrawable(getDrawable(R.drawable.ic_calendar));
        binding.ivLocation.setImageDrawable(getDrawable(R.drawable.ic_location));
        binding.ivPhone.setImageDrawable(getDrawable(R.drawable.ic_phone));
        binding.ivLock.setImageDrawable(getDrawable(R.drawable.ic_lock));
    }

    private void onNameIconClick() {
        binding.ivName.setImageDrawable(getDrawable(R.drawable.ic_name_selected));
        binding.ivCalender.setImageDrawable(getDrawable(R.drawable.ic_calendar));
        binding.ivLocation.setImageDrawable(getDrawable(R.drawable.ic_location));
        binding.ivPhone.setImageDrawable(getDrawable(R.drawable.ic_phone));
        binding.ivLock.setImageDrawable(getDrawable(R.drawable.ic_lock));

        if (viewModel.getResponseModelLiveData() != null && viewModel.getResponseModelLiveData().getValue() != null
                && viewModel.getResponseModelLiveData().getValue().getResults() != null
                && viewModel.getResponseModelLiveData().getValue().getResults().get(0) != null) {
            Name name = viewModel.getResponseModelLiveData().getValue().getResults().get(0).getUser().getName();
            String strName = name.getTitle() + ". " + name.getFirst() + " " + name.getLast();
            binding.setAbout("My name is");
            binding.setDetail(strName);
        }

    }

    private void onCalendarIconClick() {
        binding.ivName.setImageDrawable(getDrawable(R.drawable.ic_name));
        binding.ivCalender.setImageDrawable(getDrawable(R.drawable.ic_calendar_selected));
        binding.ivLocation.setImageDrawable(getDrawable(R.drawable.ic_location));
        binding.ivPhone.setImageDrawable(getDrawable(R.drawable.ic_phone));
        binding.ivLock.setImageDrawable(getDrawable(R.drawable.ic_lock));

        if (viewModel.getResponseModelLiveData() != null && viewModel.getResponseModelLiveData().getValue() != null
                && viewModel.getResponseModelLiveData().getValue().getResults() != null
                && viewModel.getResponseModelLiveData().getValue().getResults().get(0) != null) {
            String dob = viewModel.getResponseModelLiveData().getValue().getResults().get(0).getUser().getDob();
            binding.setAbout("My DOB is");
            binding.setDetail(dob);
        }

    }

    private void onLocationIconClick() {
        binding.ivName.setImageDrawable(getDrawable(R.drawable.ic_name));
        binding.ivCalender.setImageDrawable(getDrawable(R.drawable.ic_calendar));
        binding.ivLocation.setImageDrawable(getDrawable(R.drawable.ic_location_selected));
        binding.ivPhone.setImageDrawable(getDrawable(R.drawable.ic_phone));
        binding.ivLock.setImageDrawable(getDrawable(R.drawable.ic_lock));

        if (viewModel.getResponseModelLiveData() != null && viewModel.getResponseModelLiveData().getValue() != null
                && viewModel.getResponseModelLiveData().getValue().getResults() != null
                && viewModel.getResponseModelLiveData().getValue().getResults().get(0) != null) {
            // Name name= viewModel.getResponseModelLiveData().getValue().getResults().get(0).getUser().getName();
            Location location = viewModel.getResponseModelLiveData().getValue().getResults().get(0).getUser().getLocation();
            String strLocation = location.getZip() + " " + location.getStreet() + " " + location.getCity() + " " + location.getState();
            binding.setAbout("My Address is");
            binding.setDetail(strLocation);
        }

    }

    private void onPhoneIconClick() {
        binding.ivName.setImageDrawable(getDrawable(R.drawable.ic_name));
        binding.ivCalender.setImageDrawable(getDrawable(R.drawable.ic_calendar));
        binding.ivLocation.setImageDrawable(getDrawable(R.drawable.ic_location));
        binding.ivPhone.setImageDrawable(getDrawable(R.drawable.ic_phone_selected));
        binding.ivLock.setImageDrawable(getDrawable(R.drawable.ic_lock));

        if (viewModel.getResponseModelLiveData() != null && viewModel.getResponseModelLiveData().getValue() != null
                && viewModel.getResponseModelLiveData().getValue().getResults() != null
                && viewModel.getResponseModelLiveData().getValue().getResults().get(0) != null) {

            String phone = viewModel.getResponseModelLiveData().getValue().getResults().get(0).getUser().getPhone();
            binding.setAbout("My Phone Number is");
            binding.setDetail(phone);
        }

    }

    private void onLockIconClick() {
        binding.ivName.setImageDrawable(getDrawable(R.drawable.ic_name));
        binding.ivCalender.setImageDrawable(getDrawable(R.drawable.ic_calendar));
        binding.ivLocation.setImageDrawable(getDrawable(R.drawable.ic_location));
        binding.ivPhone.setImageDrawable(getDrawable(R.drawable.ic_phone));
        binding.ivLock.setImageDrawable(getDrawable(R.drawable.ic_lock_selected));

        if (viewModel.getResponseModelLiveData() != null && viewModel.getResponseModelLiveData().getValue() != null
                && viewModel.getResponseModelLiveData().getValue().getResults() != null
                && viewModel.getResponseModelLiveData().getValue().getResults().get(0) != null) {
            Name name = viewModel.getResponseModelLiveData().getValue().getResults().get(0).getUser().getName();
            //Location location=responseModel.getResults().get(0).getUser().getLocation();
            // String strLocation= location.getZip()+" "+location.getStreet()+" " +location.getCity()+" "+location.getState();
            String strName = name.getTitle() + " " + name.getFirst() + " " + name.getLast();
            binding.setAbout("Lock Info");
            binding.setDetail("Profile is locked");
        }

    }

    private void callApi() {

        viewModel.getUser();
    }


    private void init() {

    }

}