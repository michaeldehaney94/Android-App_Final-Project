package com.example.baseproject;

import java.time.LocalDateTime;

public class Comments {
    String userName;
    LocalDateTime timeStamp;
    String postedComment;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getPostedComment() {
        return postedComment;
    }

    public void setPostedComment(String postedComment) {
        this.postedComment = postedComment;
    }
}
