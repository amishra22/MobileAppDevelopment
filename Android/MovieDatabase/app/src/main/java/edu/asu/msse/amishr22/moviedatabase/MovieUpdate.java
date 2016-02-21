package edu.asu.msse.amishr22.moviedatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;


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
* @version 1.0 Feburary 2016
*/


public class MovieUpdate extends AppCompatActivity {

    private String title;
    private String year;
    private String genre;
    private String runtime;
    private String actors;
    private String plot;
    private String rated;
    Spinner rSpinner;
    ArrayAdapter<CharSequence> adapter;
    public MovieUpdate(){ ;}



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_update);
        rSpinner = (Spinner) findViewById(R.id.gReleased);
        adapter = ArrayAdapter.createFromResource(this,R.array.genreValues,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        android.util.Log.d(this.getClass().getSimpleName(), "called onCreateOptionsMenu()");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.movieinfo_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void goMain(View view){


        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
/*
        EditText t = (EditText) findViewById(R.id.title);
        EditText a = (EditText) findViewById(R.id.actor);
        EditText rt = (EditText) findViewById(R.id.runtime);
        EditText pt = (EditText) findViewById(R.id.plot);
        EditText g = (EditText) findViewById(R.id.genre);
        EditText rat = (EditText) findViewById(R.id.rated);

        title = t.getText().toString();
        rated = rat.getText().toString();
        genre = g.getText().toString();
        plot = pt.getText().toString();
        actors = a.getText().toString();
        runtime = rt.getText().toString();
        year = rSpinner.getSelectedItem().toString();


        JSONObject json =  new JSONObject();
        try {
            json.put("Title",title);
            json.put("Year",year);
            json.put("Rated",rated);
            json.put("Runtime",runtime);
            json.put("Genre",genre);
            json.put("Plot",plot);
            json.put("Actors",actors);
        } catch (JSONException e) {
            e.printStackTrace();
        }


*/
    }


}
