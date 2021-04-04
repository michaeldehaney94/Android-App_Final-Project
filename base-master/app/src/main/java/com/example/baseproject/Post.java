package com.example.baseproject;

import java.time.LocalDateTime;

import androidx.annotation.NonNull;

public class Post {

    String type;
    String TextContent;
    String VideoContent;
    LocalDateTime DateTime;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTextContent() {
        return TextContent;
    }

    public void setTextContent(String textContent) {
        this.TextContent = textContent;
    }

    public String getVideoContent() {
        return VideoContent;
    }

    public void setVideoContent(String videoContent) {
        this.VideoContent = videoContent;
    }

    public LocalDateTime getDateTime() {
        return DateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.DateTime = dateTime;
    }


}
