package com.jennabilgrien.workoutbuddiesfilters;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import android.widget.ArrayAdapter;
import android.app.Activity;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import android.view.*;
import android.widget.TextView;
import android.graphics.Color;

/**
 * Created by jennabilgrien on 11/5/15.
 */
public class DisplaySharedPreferences extends Activity {

    ListView listView;
    private ProgressDialog pDialog;
    //private ArrayList<String> userList = new ArrayList<String>();
    //ArrayAdapter<String> adapter;
    SimpleAdapter adapter;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //addPreferencesFromResource(R.xml.prefs);

        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());

        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        String genderPrefs = prefs.getString("genderpref", "Default list prefs");
        String agePrefs = prefs.getString("agepref", "Default list prefs");
        //String fitnessPrefs = prefs.getString("fitnesspref", "Default list prefs");
        //String levelPrefs = prefs.getString("levelpref", "Default list prefs");
        //String freqPrefs = prefs.getString("freqpref", "Default list prefs");
        //String availPrefs = prefs.getString("availpref", "Default list prefs");

        ParseQuery<ParseObject> query = ParseQuery.getQuery("dog");
        query.whereStartsWith("Gender", genderPrefs);
        query.whereStartsWith("Age", agePrefs);

        setContentView(R.layout.listview_main);
        listView = (ListView) findViewById(R.id.gender);

        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> matchList, ParseException e) {
                if (e == null) {
                    Log.d("gender", "Retrieved " + matchList.size() + " matches");
                    List<Map<String, String>> userList = new ArrayList<Map<String, String>>();
                    for (ParseObject person : matchList) {
                        //String userInfo;
                        //userInfo = "Name: " + person.getString("FirstName") + " " + person.getString("LastName") + "\n" +
                        //    "Age: " + person.getString("Age") + "\t" + "Fitness Goal: " + person.getString("fitness") + "\n\n";
                        //userList.add(userInfo);
                        Map<String, String> user = new HashMap<String, String>(2);
                        user.put("name", person.getString("FirstName") + " " + person.getString("LastName"));
                        user.put("info", person.getString("Age"));
                        userList.add(user);
                    }
                    pDialog.dismiss();
                    //adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_black_text, R.id.list_content, userList);
                    adapter = new SimpleAdapter(getApplicationContext(), userList, android.R.layout.simple_list_item_2,
                            new String[] {"name", "info"}, new int[] {android.R.id.text1, android.R.id.text2}){
                        @Override
                    public View getView(int position, View convertView, ViewGroup parent){
                            View v = super.getView(position, convertView, parent);
                            TextView tv1 = (TextView)v.findViewById(android.R.id.text1);
                            TextView tv2 = (TextView)v.findViewById(android.R.id.text2);
                            tv1.setTextColor(Color.BLACK);
                            tv2.setTextColor(Color.BLACK);
                            return v;
                        }
                    };

                    listView.setAdapter(adapter);

                } else {
                    Log.d("gender", "Error: " + e.getMessage());
                }
            }
        });
    }

}