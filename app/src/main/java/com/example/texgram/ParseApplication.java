package com.example.texgram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Register Post Class as subclass
        ParseObject.registerSubclass(Post.class);

        // Use for troubleshooting -- remove this line for production
        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);


        // set applicationId, and server server based on the values in the Heroku settings.
        // clientKey is not needed unless explicitly configured
        // any network interceptors must be added with the Configuration Builder given this syntax

        final Parse.Configuration configuration = new Parse.Configuration.Builder(this)
                .applicationId("texgramId")
                .clientKey("mytexgramId")
                .server("http://texgram.herokuapp.com/parse")
                .build();
        Parse.initialize(configuration);

        // parse-dashboard --appId texgramId --masterKey mytexgramId --serverURL "http://texgram.herokuapp.com/parse"

    }
}