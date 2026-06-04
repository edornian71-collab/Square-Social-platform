package com.example.squaresocialplatform;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Feed extends RecyclerView.Adapter<Feed.MyViewHolder> {

    ArrayList<FeedItem> FeedItems;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView usernameTv;
        TextView postTv;
       // Button commentButton;
        ImageView pfpTv;


        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            this.usernameTv = itemView.findViewById(R.id.username);
            this.postTv = itemView.findViewById(R.id.post);

            this.pfpTv = itemView.findViewById(R.id.feedpfp);
         //   this.commentButton = itemView.findViewById(R.id.comments);


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
        loadScaledImage(holder, currentItem.pfpFeed);

        //holder.commentButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Context context = view.getContext();
//
//                Intent intent = new Intent(context, CommentActivity.class);
//
//                intent.putExtra("USER_NAME", currentItem.username);
//
//                context.startActivity(intent);
//            }
//        });
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

    }
    public void loadScaledImage (@NonNull Feed.MyViewHolder holder,String path) {
        BitmapFactory.Options bounds = new BitmapFactory.Options();
        bounds.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, bounds);

        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inSampleSize = Math.max(bounds.outWidth / 55, bounds.outHeight / 55);
        if (opts.inSampleSize < 1) opts.inSampleSize = 1;

        Bitmap scaled = BitmapFactory.decodeFile(path, opts);
        if (scaled == null) return;

        int size = Math.min(scaled.getWidth(), scaled.getHeight());
        Bitmap circle = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(circle);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        float radius = size / 2f;
        canvas.drawCircle(radius, radius, radius, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(scaled, (size - scaled.getWidth()) / 2f, (size - scaled.getHeight()) / 2f, paint);

        holder.pfpTv.setImageBitmap(circle);
    }


    @Override
    public int getItemCount() {
        return FeedItems.size();
    }




}
