package com.example.squaresocialplatform;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Comment extends RecyclerView.Adapter<Comment.MyViewHolder> {


        ArrayList<CommentItem> items;



        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView usernameTv;
            TextView commentTv;
            public MyViewHolder(@NonNull View itemView) {

                super(itemView);
                this.usernameTv = itemView.findViewById(R.id.comment_username);
                this.commentTv = itemView.findViewById(R.id.comment);

            }


        }

    public Comment(ArrayList<CommentItem> items) {
            this.items = items;

        }

        @NonNull
        @Override
        public Comment.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_layout, parent, false);
            return new Comment.MyViewHolder(view);
        }



    @Override
        public void onBindViewHolder(@NonNull Comment.MyViewHolder holder, int position) {
            CommentItem currentItem = items.get(position);

            holder.usernameTv.setText(currentItem.username);
            holder.commentTv.setText(currentItem.comment);
        }


        @Override
        public int getItemCount() {
            return items.size();
        }
}
