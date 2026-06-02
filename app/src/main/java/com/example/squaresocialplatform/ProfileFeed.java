package com.example.squaresocialplatform;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class ProfileFeed extends RecyclerView.Adapter<ProfileFeed.MyViewHolder> {

    ArrayList<ProfileFeedItem> PFItems;

    public ProfileFeed(ArrayList<ProfileFeedItem> listItem) {
        this.PFItems = listItem;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView usernameTv;
        TextView contentTv;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            this.usernameTv = itemView.findViewById(R.id.postUser);
            this.contentTv = itemView.findViewById(R.id.postContent);
        }
    }

    @NonNull
    @Override
    public ProfileFeed.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileFeed.MyViewHolder holder, int position) {
        ProfileFeedItem current = PFItems.get(position);

        holder.usernameTv.setText(current.username);
        holder.contentTv.setText(current.content);
    }

    @Override
    public int getItemCount() {
        return PFItems.size();
    }
}
