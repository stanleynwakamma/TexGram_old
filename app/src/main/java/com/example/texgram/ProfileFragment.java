package com.example.texgram;

import android.os.Bundle;

import com.parse.ParseUser;


public class ProfileFragment extends FeedFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        filterUser = ParseUser.getCurrentUser();
    }

}