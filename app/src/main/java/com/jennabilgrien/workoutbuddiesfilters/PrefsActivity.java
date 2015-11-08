package com.jennabilgrien.workoutbuddiesfilters;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;

/**
 * Created by jennabilgrien on 10/15/15.
 */
public class PrefsActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();
        //View a = findViewById(R.id.btnGetPreferences);
        //View b = findViewById(R.id.btnPrefs);
        //b.setVisibility(View.GONE);
        //a.setVisibility(View.GONE);
    }

    public static class MyPreferenceFragment extends PreferenceFragment{
        @Override
        public void onCreate(final Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.prefs);
        }
    }
}

