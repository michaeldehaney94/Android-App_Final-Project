package com.example.baseproject;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private static final String TAG = "LaunchActivityLog";
    private ArrayList<Post> posts;

    public PostAdapter(ArrayList<Post> posts) {
        this.posts = posts;
    }

    @Override
    public int getItemViewType(int position) {

        Post current = posts.get(position);
        switch (current.getType()) {
            case "video":
                return 1;
            default:
                return 0;
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;

        try {
            if (viewType == 1) {
                view = LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.home_feed, parent, false);
            }
        }catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "app has executed");
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post current = posts.get(position);
        Resources res = holder.itemView.getResources();
        Drawable drawable = ResourcesCompat.getDrawable(res, R.drawable.exo_controls_play, null);

        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.avatarView.setImageDrawable(drawable);
            viewHolder.username.setText(current.getTextContent());
            viewHolder.dateTimeView.setText(current.getDateTime()
                    .format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
            viewHolder.description.setText(current.getTextContent());

            MediaItem mediaItem = MediaItem.fromUri(current.getVideoContent());
            SimpleExoPlayer player = new SimpleExoPlayer.Builder(viewHolder.videoView.getContext()).build();
            viewHolder.videoView.setPlayer(player);
            player.setMediaItem(mediaItem);
            player.prepare();

        }

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircularImageView avatarView;
        TextView username;
        TextView dateTimeView;
        TextView description;
        PlayerView videoView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            avatarView = itemView.findViewById(R.id.user_image);
            dateTimeView = itemView.findViewById(R.id.timestamp);
            username = itemView.findViewById(R.id.username);
            description = itemView.findViewById(R.id.content_description);
            videoView = itemView.findViewById(R.id.video_content);


        }
    }
}
