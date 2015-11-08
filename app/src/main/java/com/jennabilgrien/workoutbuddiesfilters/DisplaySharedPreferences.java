package com.jennabilgrien.workoutbuddiesfilters;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;

import com.jennabilgrien.workoutbuddiesfilters.R;
import com.jennabilgrien.workoutbuddiesfilters.Filters;
import com.jennabilgrien.workoutbuddiesfilters.App;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import android.widget.TextView.BufferType;

import android.app.Activity;

import java.util.List;

import static android.widget.TextView.*;

/**
 * Created by jennabilgrien on 11/5/15.
 */
public class DisplaySharedPreferences extends Activity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MatchedPreferences()).commit();

        //View a = findViewById(R.id.btnGetPreferences);
        //View b = findViewById(R.id.btnPrefs);
        //b.setVisibility(View.GONE);
        //a.setVisibility(View.GONE);
    }

    public class MatchedPreferences extends PreferenceFragment {
        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.prefs);

            SharedPreferences prefs = PreferenceManager
                    .getDefaultSharedPreferences(getApplicationContext());

            String genderPrefs = prefs.getString("genderpref", "Default list prefs");
            String agePrefs = prefs.getString("agepref", "Default list prefs");
            String fitnessPrefs = prefs.getString("fitnesspref", "Default list prefs");
            String levelPrefs = prefs.getString("levelpref", "Default list prefs");
            String freqPrefs = prefs.getString("freqpref", "Default list prefs");
            String availPrefs = prefs.getString("availpref", "Default list prefs");
            /*
            StringBuilder builder = new StringBuilder();
            builder.append("Gender: " + genderPrefs + "\n");
            builder.append("Age: " + agePrefs + "\n");
            builder.append("Fitness Plan: " + fitnessPrefs + "\n");
            builder.append("Fitness Level: " + levelPrefs + "\n");
            builder.append("Gym Frequency: " + freqPrefs + "\n");
            builder.append("Time Availability: " + availPrefs + "\n");
            */

            ParseQuery<ParseObject> query = ParseQuery.getQuery("dog");
            query.whereStartsWith("Gender", genderPrefs);
            query.whereStartsWith("Age", agePrefs);
            query.findInBackground(new FindCallback<ParseObject>() {
                public void done(List<ParseObject> matchList, ParseException e) {
                    if (e == null) {
                        Log.d("gender", "Retrieved " + matchList.size() + " matches");
                        StringBuilder matchesString = new StringBuilder();
                        for (ParseObject person : matchList) {
                            matchesString.append(person.getString("FirstName"));
                        }
                        setContentView(R.layout.listview_main);
                        textView = (TextView) findViewById(R.id.gender);

                        textView.setText(matchesString.toString());
                    } else {
                        Log.d("gender", "Error: " + e.getMessage());
                    }
                }
            });

            /*query.whereEqualTo("Gender", "male");
            query.findInBackground(new FindCallback<ParseObject>()  {

                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    if (e == null) {
                        Log.d("Gender", "Retrieved " + objects.size() + " Males");
                        for (Object userObject : objects) {
                            // use dealsObject.get('columnName') to access the properties of the Deals object.
                            userObject.toString();
                        }
                    } else {
                        Log.d("Brand", "Error: " + e.getMessage());
                    }
                }

                //new

                //RemoteDataTask()

                //.

                //execute();
                */
            }
        }
    }








    /*class RemoteDataTask extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(getApplicationContext());
            mProgressDialog.setTitle("Filtering Users");
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected String doInBackground(Void...params) {
            SharedPreferences prefs = PreferenceManager
                    .getDefaultSharedPreferences(getApplicationContext());
            try {
                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("dog");
                query.whereEqualTo("Gender", prefs.getString("genderpref", "Default list prefs"));
                ob = query.find();
            }
            catch (ParseException e){
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {

            ParseQuery query = new ParseQuery("Gender");

            query.whereEqualTo("Gender", "male");
            query.findInBackground(new FindCallback() {

                @Override
                public void done(List objects, ParseException e) {
                    if (e == null) {
                        Log.d("Gender", "Retrieved " + objects.size() + " Males");
                        for (Object userObject : objects) {
                            // use dealsObject.get('columnName') to access the properties of the Deals object.
                            userObject.toString();
                        }
                    } else {
                        Log.d("Brand", "Error: " + e.getMessage());
                    }
                }
            });

            //listview.setOnItemClickListener(new OnItemClickListener() {
            //    @Override
            //    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //Send single item click data to PrefsActivity Class
            //        Intent i = new Intent(Filters.this,
            //                PrefsActivity.class);
            //Pass data "gender" followed by the position
            //        i.putExtra("gender", ob.get(position).getString("gender"));
            //Open PrefsActivity.java Activity
            //        startActivity(i);
        }
        //});
    }
}
*/

