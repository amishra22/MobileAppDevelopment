package edu.asu.msse.amishr22.moviedatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;


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


public class MovieDisplay extends AppCompatActivity {


    MovieDesc md;
    protected MainActivity parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_display);



        TextView title = (TextView) findViewById(R.id.textMTitle);
        TextView year = (TextView) findViewById(R.id.textMYear);
        TextView genre = (TextView) findViewById(R.id.textMGenre);
        TextView actors = (TextView) findViewById(R.id.textMActors);
        TextView runtime = (TextView) findViewById(R.id.textMRuntime);
        TextView released = (TextView) findViewById(R.id.textMReleased);
        TextView plot = (TextView) findViewById(R.id.textMPlot);
        TextView rated = (TextView) findViewById(R.id.textMRated);

        title.setText(getIntent().getStringExtra("Title"));
        year.setText(getIntent().getStringExtra("Year"));;
        genre.setText(getIntent().getStringExtra("Genre"));
        actors.setText(getIntent().getStringExtra("Actors"));
        runtime.setText(getIntent().getStringExtra("Runtime"));
        released.setText(getIntent().getStringExtra("Released"));
        plot.setText(getIntent().getStringExtra("Plot"));
        rated.setText(getIntent().getStringExtra("Rated"));

    }
    public void removeMovie(View view){
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);


    }

    public void addDesc(View view){

        Intent intnt = new Intent(this, MovieUpdate.class);
        startActivity(intnt);
    }
}
