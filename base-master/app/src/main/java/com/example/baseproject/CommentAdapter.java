package com.example.baseproject;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private ArrayList<Comments> comments;


    public CommentAdapter(ArrayList<Comments> comments) {
        this.comments = comments;
    }

    @NonNull
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.comment_feed, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder holder, int position) {

        Resources res = holder.itemView.getResources();
        Drawable avatar = ResourcesCompat.getDrawable(res, R.drawable.exo_controls_play, null);

        Comments commentPost = comments.get(position);
        holder.avatarView.setImageDrawable(avatar);
        holder.username.setText(commentPost.getUserName());
        holder.timestamp.setText(commentPost.getTimeStamp().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
        holder.commentText.setText(commentPost.getPostedComment());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircularImageView avatarView;
        TextView username;
        TextView timestamp;
        TextView commentText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            avatarView = (itemView).findViewById(R.id.user_image);
            username = (itemView).findViewById(R.id.username);
            timestamp = (itemView).findViewById(R.id.timestamp);
            commentText = (itemView).findViewById(R.id.comment_text);


        }
    }
}
