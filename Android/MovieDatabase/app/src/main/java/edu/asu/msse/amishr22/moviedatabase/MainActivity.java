package edu.asu.msse.amishr22.moviedatabase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;



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


public class MainActivity extends AppCompatActivity {

    public String selectedStuff;
    public ExpandableListView elview;
    public ExpandableStuffAdapter myListAdapter;
    public HashMap<String,ArrayList<String>> genreParent;
    MovieLib mlib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        genreParent = new HashMap<String, ArrayList<String>>();
        mlib = new MovieLib(this);
        elview = (ExpandableListView)findViewById(R.id.lvExp);
        myListAdapter = new ExpandableStuffAdapter(this);
        elview.setAdapter(myListAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        android.util.Log.d(this.getClass().getSimpleName(), "called onCreateOptionsMenu()");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /*
     * Implement onOptionsItemSelected(MenuItem item){} to handle clicks of buttons that are
     * in the action bar.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        android.util.Log.d(this.getClass().getSimpleName(), "called onOptionsItemSelected()");
        Intent i = new Intent(this, MovieUpdate.class);
        switch (item.getItemId()) {
            case R.id.action_gimp:
               // i.putExtra("message", "This is a search dialog");
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setSelectedStuff(String aStuff){
        this.selectedStuff = aStuff;
    }

    // this method is called when the web view activity when the action bar back button is clicked.
    // the web view activity sets the result of the intent and finishes the web view activiy.
    // You could use this mechanism to communicate any serializable object between activities.
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                String itemname=data.getStringExtra("myresult");
                android.util.Log.d(this.getClass().getSimpleName(),"Returned list item name: "+itemname);
            }
        }
    }
}
