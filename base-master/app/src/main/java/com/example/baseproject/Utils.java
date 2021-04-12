package com.example.baseproject;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class Utils {
    public static Post convertToPost(QueryDocumentSnapshot queryDocumentSnapshot) {

        if (queryDocumentSnapshot == null) {
            return null;
        }

        String postType = (String) queryDocumentSnapshot.get("postType");

        if (postType != null && postType.equals("image")) {
            return convertToPostImage(queryDocumentSnapshot);
        }
        return null;
    }

    private static PostImage convertToPostImage(QueryDocumentSnapshot queryDocumentSnapshot) {

        PostImage postImage = new PostImage();

        String userId = (String) queryDocumentSnapshot.get("userId");
        Timestamp timestamp = (queryDocumentSnapshot.get("timestamp") == null) ? null : queryDocumentSnapshot.getTimestamp("timestamp");
        String description = (String) queryDocumentSnapshot.get("description");
        String comments = String.valueOf((queryDocumentSnapshot.get("comments") == null) ? 0 : queryDocumentSnapshot.getLong("comments").intValue());
        int likes = (queryDocumentSnapshot.get("likes") == null) ? 0 : queryDocumentSnapshot.getLong("likes").intValue();
        String imageUrl = (String) queryDocumentSnapshot.get("imageUrl");

        postImage.setUserId(userId);
        postImage.setDescription(description);
        postImage.setComments(comments);
        postImage.setLikes(likes);
        postImage.setImageUrl(imageUrl);
        postImage.setId(queryDocumentSnapshot.getId());

        return postImage;
    }
}
