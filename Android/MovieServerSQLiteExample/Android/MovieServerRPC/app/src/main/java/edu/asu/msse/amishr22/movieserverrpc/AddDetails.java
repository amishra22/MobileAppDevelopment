package edu.asu.msse.amishr22.movieserverrpc;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Movie;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

/*
* Copyright 2016 Abhinav Mishra,
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*
* Purpose: This is a part of class assignment
*
* Right To Use: This assignment can be used by the instructor and grader
* for evaluation purpose.
* Ser598 Mobile Systems
* @author Abhinav Mishra amishr22@asu.edu
* MS Software Engineering, CIDSE
* @version 1.0 March 2016
*/

public class AddDetails extends AppCompatActivity implements SearchView.OnQueryTextListener {

    /*
    This class handles the option to add a new movie. Also there is an option of search given in the action
    bar. On clicking the search, the user can enter the the movie title. If the search was unsuccessful a toast
    is displayed with error message and otherwise the edit text field is populated with the respective values.
    After clicking add the movie is added to database.
     */

    private String searchQuery, searchString;
    private Menu menu;
    private SearchView searchView;
    private EditText titleField;
    private EditText yearField;
    private EditText ratedField;
    private EditText releasedField;
    private EditText runtimeField;
    private EditText actorsField;
    private EditText plotField;
    private EditText genreSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_movie_details);
        titleField = (EditText) findViewById(R.id.EditTextTitle);
        yearField = (EditText) findViewById(R.id.EditTextYear);
        ratedField = (EditText) findViewById(R.id.EditTextRated);
        releasedField = (EditText) findViewById(R.id.EditTextReleased);
        runtimeField = (EditText) findViewById(R.id.EditTextRuntime);
        actorsField = (EditText) findViewById(R.id.EditTextActors);
        plotField = (EditText) findViewById(R.id.EditTextPlot);
        genreSpinner = (EditText) findViewById(R.id.editText);
    }

    public void updateDetails(View view) {

        String title = titleField.getText().toString();
        String year = yearField.getText().toString();
        String rated = ratedField.getText().toString();
        String released = releasedField.getText().toString();
        String runtime = runtimeField.getText().toString();
        String actors = actorsField.getText().toString();
        String plot = plotField.getText().toString();
        String genre = genreSpinner.getText().toString();


        try{
            MovieDB db = new MovieDB((Context)this);
            SQLiteDatabase crsDB = db.openDB();
            String insert = "insert into movie values('"+title+"','"+
                    year+"','"+released+"','"+
                    runtime+"','"+actors+"','"+plot+"','"+genre+"','"+rated+"');";
            crsDB.execSQL(insert);
            crsDB.close();
            db.close();

            //this.loadFields();
        } catch (Exception ex){
            android.util.Log.w(this.getClass().getSimpleName(),"Exception adding student information: "+
                    ex.getMessage());
        }


        Intent intent = new Intent();
        intent.putExtra("moviedesc", title);
        setResult(2, intent);
        finish();

    }

    public void fillDetails(JSONObject jObj) throws JSONException {

        String ttitle = jObj.getString("Title");
        ttitle = ttitle.replaceAll("'","");
        titleField.setText(ttitle);

        yearField.setText(jObj.getString("Year"));

        ratedField.setText(jObj.getString("Rated"));

        releasedField.setText(jObj.getString("Released"));

        runtimeField.setText(jObj.getString("Runtime"));

        String tactors = jObj.getString("Actors");
        tactors = tactors.replaceAll("'","\\\''");
        actorsField.setText(tactors);

        String tplot = jObj.getString("Plot");
        tplot = tplot.replaceAll("'","\\\''");
        if(tplot.length() > 60){
            tplot = tplot.substring(0,60) + "...";
        }
        plotField.setText(tplot);

        String gen = jObj.getString("Genre");
        String[] allgen = gen.split(",");
        if(allgen[0].equals("N/A")){
            genreSpinner.setText("Action");
        }else{
            genreSpinner.setText(allgen[0]);
        }


    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        android.util.Log.d(this.getClass().getSimpleName(), "in onQueryTextSubmit: " + query);
        this.searchString = query;
        searchString = searchString.replace(' ','+');
        searchString = String.format("http://www.omdbapi.com/?t=%s&y=&plot=short&r=json",searchString);
        //MenuItemCompat.collapseActionView((MenuItem)menu.findItem(R.id.action_search));
        searchView.clearFocus();
        android.util.Log.d(this.getClass().getSimpleName(), "Search string is " + this.searchString);
        //MovieAsyncTask
        new MovieAsyncTask(this).execute(searchString);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Log.d(this.getClass().getSimpleName(), "in onCreateOptionsMenu");
        getMenuInflater().inflate(R.menu.menu_add, menu);
        this.menu = menu;

        // setup the search in action bar
        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (android.widget.SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(this);
        return true;
    }

    public void showErrorMsg(String str){
        Toast.makeText(AddDetails.this, str, Toast.LENGTH_SHORT).show();
    }

}

