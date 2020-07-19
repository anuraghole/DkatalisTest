package com.example.dkatalistest.ui.favourites.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dkatalistest.R;
import com.example.dkatalistest.databinding.LayoutItemUserBinding;
import com.example.dkatalistest.model.User;

import java.util.List;

public class FavUserListAdapter extends RecyclerView.Adapter<FavUserListAdapter.FavUserViewHolder> {
    private Context context;
    private List<User> userList;

    public FavUserListAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }


    @NonNull
    @Override
    public FavUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemUserBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.layout_item_user, parent, false);

        return new FavUserViewHolder(binding);
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull FavUserViewHolder holder, int position) {
        User user=userList.get(position);
        holder.binding.setModel(user);
        holder.binding.setName(user.getName().getTitle()+". "+" "+user.getName().getFirst()+" "+user.getName().getLast());
        Glide.with(context).load(user.getPicture()).placeholder(R.drawable.ic_male_profile_placeholder).into(holder.binding.ivAvatar);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


    static class FavUserViewHolder extends RecyclerView.ViewHolder {
        LayoutItemUserBinding binding;
        public FavUserViewHolder(@NonNull LayoutItemUserBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }

    }
}
