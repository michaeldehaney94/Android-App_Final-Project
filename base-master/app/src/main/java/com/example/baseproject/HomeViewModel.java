package com.example.baseproject;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final String TAG = "HomeViewModel";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private MutableLiveData<List<Post>> posts;

    public LiveData<List<Post>> getPosts() {
        if(posts == null) {
            posts = new MutableLiveData<List<Post>>();
            loadPosts();
        }
        return posts;
    }

    private void loadPosts () {
        //Access posts data collection
        db.collection("posts").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    QuerySnapshot documents = task.getResult();

                    if (documents == null) {
                        return;
                    }

                    List<Post> list = new ArrayList<>();

                    for (QueryDocumentSnapshot document : documents) {
                        Post post = Utils.convertToPost(document);

                        if (!list.contains(post)) {
                            Log.i(TAG, post.toString());
                            list.add(post);
                        }
                    }
                    posts.postValue(list);
                }
            }
        });

    }
}
