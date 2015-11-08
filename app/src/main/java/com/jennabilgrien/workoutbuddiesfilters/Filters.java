package com.jennabilgrien.workoutbuddiesfilters;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.content.SharedPreferences;
import android.util.Log;
import java.util.List;
import android.widget.AdapterView.OnItemClickListener;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import java.util.ArrayList;
import com.parse.FindCallback;
import android.content.Context;
import android.widget.Toast;

public class Filters extends Activity {
    TextView textView;

    ListView listview;
    List<ParseObject> ob;
    ProgressDialog mProgressDialog;
    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);

        Button btnPrefs = (Button) findViewById(R.id.btnPrefs);
        Button btnGetPrefs = (Button) findViewById(R.id.btnGetPreferences);

        textView = (TextView) findViewById(R.id.txtPrefs);

        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.btnPrefs:
                        Intent intent = new Intent(Filters.this,
                                PrefsActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.btnGetPreferences:
                        Intent intent2 = new Intent(Filters.this,
                                DisplaySharedPreferences.class);
                        startActivity(intent2);
                        break;

                    default:
                        break;
                }
            }
        };

        btnPrefs.setOnClickListener(listener);
        btnGetPrefs.setOnClickListener(listener);
    }

    private void addDrawerItems() {
        String[] osArray = { "Android", "iOS", "Windows", "OS X", "Linux" };
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);
    }

    /*private void displaySharedPreferences() {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(Filters.this);

        String genderPrefs = prefs.getString("genderpref", "Default list prefs");
        String agePrefs = prefs.getString("agepref", "Default list prefs");
        String fitnessPrefs = prefs.getString("fitnesspref", "Default list prefs");
        String levelPrefs = prefs.getString("levelpref", "Default list prefs");
        String freqPrefs = prefs.getString("freqpref", "Default list prefs");
        String availPrefs = prefs.getString("availpref", "Default list prefs");

        StringBuilder builder = new StringBuilder();
        builder.append("Gender: " + genderPrefs + "\n");
        builder.append("Age: " + agePrefs + "\n");
        builder.append("Fitness Plan: " + fitnessPrefs + "\n");
        builder.append("Fitness Level: " + levelPrefs + "\n");
        builder.append("Gym Frequency: " + freqPrefs + "\n");
        builder.append("Time Availability: " + availPrefs + "\n");


        textView.setText(builder.toString());

        setContentView(R.layout.listview_main);

        new RemoteDataTask().execute();
    }

    class RemoteDataTask extends AsyncTask<Void, Void, String>{

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(Filters.this);
            mProgressDialog.setTitle("Filtering Users");
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected String doInBackground(Void...params) {
            SharedPreferences prefs = PreferenceManager
                    .getDefaultSharedPreferences(Filters.this);
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
            });*/

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
    //}
    //}


