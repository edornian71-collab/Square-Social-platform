package com.example.squaresocialplatform;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Feed extends RecyclerView.Adapter<Feed.MyViewHolder> {

    ArrayList<FeedItem> FeedItems;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView usernameTv;
        TextView postTv;
        Button commentButton;
        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            this.usernameTv = itemView.findViewById(R.id.username);
            this.postTv = itemView.findViewById(R.id.post);
            this.commentButton = itemView.findViewById(R.id.comments);


        }


    }

    public Feed(ArrayList<FeedItem> items) {
        this.FeedItems = items;

    }

    @NonNull
    @Override
    public Feed.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Feed.MyViewHolder holder, int position) {
        FeedItem currentItem = FeedItems.get(position);

        holder.usernameTv.setText(currentItem.username);
        holder.postTv.setText(currentItem.post);
        if (currentItem.isExpanded) {
            holder.postTv.setMaxLines(Integer.MAX_VALUE);
        } else {
            holder.postTv.setMaxLines(4);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                currentItem.isExpanded = !currentItem.isExpanded;
                notifyItemChanged(holder.getBindingAdapterPosition());
            }
        });
        holder.commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();

                Intent intent = new Intent(context, CommentActivity.class);

                intent.putExtra("USER_NAME", currentItem.username);

                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return FeedItems.size();
    }


}
