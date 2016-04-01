package edu.asu.msse.amishr22.movieserverrpc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

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
* @version 1.0 March 2016
*/

public class AddDetails extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_movie_details);
    }

    public void updateDetails(View view) {
        final EditText titleField = (EditText) findViewById(R.id.EditTextTitle);
        String title = titleField.getText().toString();

        final EditText yearField = (EditText) findViewById(R.id.EditTextYear);
        String year = yearField.getText().toString();

        final EditText ratedField = (EditText) findViewById(R.id.EditTextRated);
        String rated = ratedField.getText().toString();

        final EditText releasedField = (EditText) findViewById(R.id.EditTextReleased);
        String released = releasedField.getText().toString();

        final EditText runtimeField = (EditText) findViewById(R.id.EditTextRuntime);
        String runtime = runtimeField.getText().toString();

        final EditText actorsField = (EditText) findViewById(R.id.EditTextActors);
        String actors = actorsField.getText().toString();

        final EditText plotField = (EditText) findViewById(R.id.EditTextPlot);
        String plot = plotField.getText().toString();

        final Spinner genreSpinner = (Spinner) findViewById(R.id.SpinnerFeedbackType);
        String genre = genreSpinner.getSelectedItem().toString();


        MovieDescription mdes = new MovieDescription
                ("{\"Title\":\"" + title + "\",\"Year\":\"" + year + "\",\"Rated\":\"" + rated + "\",\"Released\":\"" + released + "\",\"Runtime\":\"" + runtime + "\",\"Genre\":\"" + genre + "\",\"Actors\":\"" + actors + "\",\"Plot\":\"" + plot + "\"}");

        Intent intent = new Intent();
        intent.putExtra("moviedesc", mdes);
        setResult(2, intent);
        finish();

    }

}

