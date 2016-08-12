package edu.asu.msse.amishr22.movieserverrpc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

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

public class DisplayMovDetails extends AppCompatActivity {

    /*
        This particular class is used to display the data of selected movie in expandable list view.
        There is an option of deleting the movie from here. Once the button is clicked an intent is created
        and the title is passed. Once it reaches the main activity the movie is deleted from the list view

     */

    private TextView textView10;
    private TextView textView11;
    private TextView textView12;
    private TextView textView13;
    private TextView textView14;
    private TextView textView15;
    private TextView textView16;
    private TextView textView17;

    public String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        textView10 = (TextView)findViewById(R.id.textView10);
        textView11 = (TextView)findViewById(R.id.textView11);
        textView12 = (TextView)findViewById(R.id.textView12);
        textView13 = (TextView)findViewById(R.id.textView13);
        textView14 = (TextView)findViewById(R.id.textView14);
        textView15 = (TextView)findViewById(R.id.textView15);
        textView16 = (TextView)findViewById(R.id.textView16);
        textView17 = (TextView)findViewById(R.id.textView17);

        Intent intent = getIntent();
        //MovieDescription n = (MovieDescription)getIntent'().getSerializableExtra("MyClass");
        //String title = (String) intent.getSerializableExtra("title");
        String title = intent.getStringExtra("title");
        String year = intent.getStringExtra("year");
        String runtime = intent.getStringExtra("runtime");
        String rated = intent.getStringExtra("rated");
        String released = intent.getStringExtra("released");
        String actors = intent.getStringExtra("actor");
        String plot = intent.getStringExtra("plot");
        String genre = intent.getStringExtra("genre");
        this.title = title;
        //Log.d("Title"," hai : " + title);
        textView10.setText(title);
        textView11.setText(year);
        textView12.setText(rated);
        textView13.setText(released);
        textView14.setText(runtime);
        textView16.setText(actors);
        textView15.setText(genre);
        textView17.setText(plot);
    }

    public void deleteMovie(View view){
       // Log.d("DELETE", "EXIST" + " " + title);
        Intent i1 = new Intent();
        i1.putExtra("mName",this.title);
        setResult(4,i1);
        finish();

    }
}

