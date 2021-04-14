package com.example.baseproject;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.ui.PlayerView;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>{

    private ArrayList<Post> posts = new ArrayList<>();


    public HomeAdapter ( ArrayList<Post> posts) {

        this.posts = posts;
    }

    @Override
    public int getItemViewType(int position) {
        Post current = posts.get(position);

        if (current.getType().equals("image")) {
            return 1;
        }
        return 0;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.post_feed, parent, false);
        return new HomeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post current = posts.get(position);
        Drawable drawable = ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.ic_launcher_foreground);

        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            PostImage postImage = (PostImage) current;

            viewHolder.textView.setText(current.getDescription());
            viewHolder.avatarView.setImageDrawable(drawable);
            viewHolder.dateTimeView.setText(current.getDateTime().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
            Glide.with(viewHolder.imageView.getContext())
                    .load(postImage.getImageUrl())
                    .into(viewHolder.imageView);
        }

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView avatarView;
        TextView nameView;
        TextView dateTimeView;
        TextView textView;
        ImageView imageView;
        TextView description;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            avatarView = itemView.findViewById(R.id.profile);
            nameView = itemView.findViewById(R.id.name);
            dateTimeView = itemView.findViewById(R.id.timestamp);
            textView = itemView.findViewById(R.id.text);
            imageView = itemView.findViewById(R.id.photo);
            description = itemView.findViewById(R.id.description);
        }
    }
}
