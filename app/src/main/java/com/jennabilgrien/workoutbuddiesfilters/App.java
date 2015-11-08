package com.jennabilgrien.workoutbuddiesfilters;

/**
 * Created by jennabilgrien on 10/22/15.
 */

import android.app.Application;
import com.parse.Parse;
import com.parse.ParseUser;
import com.parse.ParseACL;

public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this, "7soWwAwPRuzUVjRfOv7LF4N6POp7tMe2YrEYuub5", "DWPjnu4B77OIgEgEmYT6CVlTYxizm38C6liNCXPU"); // Your Application ID and Client Key are defined elsewhere

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);

    }
}
