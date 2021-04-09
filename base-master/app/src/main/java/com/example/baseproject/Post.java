package com.example.baseproject;

import java.time.LocalDateTime;

import androidx.annotation.Nullable;

public class Post {
    private String userId;
    private LocalDateTime timestamp;
    private String description;
    private String type;
    private int comments;
    private int likes;
    private String Id;

    public String getUserId(){
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getDateTime() {
        return timestamp;
    }

    public void setDateTime(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(this == obj){
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Post post = (Post) obj;
        return Id.equals(post.Id);
    }

    @Override
    public int hashCode() {
        return Id.hashCode();
    }

    @Override
    public String toString() {
        return "ChatBout{" +
                "userId='" + userId + '\'' +
                ", timestamp=" + timestamp +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", comments=" + comments +
                ", likes=" + likes +
                ", Id='" + Id + '\'' +
                '}';
    }
}
