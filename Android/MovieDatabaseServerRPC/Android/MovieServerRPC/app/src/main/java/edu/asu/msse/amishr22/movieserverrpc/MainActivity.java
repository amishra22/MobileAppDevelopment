package edu.asu.msse.amishr22.movieserverrpc;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import java.net.URL;
import java.util.Map;
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

public class MainActivity extends AppCompatActivity {

    public String url = "";

    public String selectedStuff;
    public ExpandableListView elview;
    public ExpandableStuffAdapter myListAdapter;
    public MovieDescription newMovie;
    public Handler aHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        elview = (ExpandableListView)findViewById(R.id.lvExp);
        myListAdapter = new ExpandableStuffAdapter(this);
        elview.setAdapter(myListAdapter);

        url = getString(R.string.url);
        //Make a Request to Server to get the details
        try {
            aHandler = new Handler();
            JsonRPCClientViaThread names = new JsonRPCClientViaThread(new URL(url),
                    aHandler, this, "getMovieList","[ ]");
            names.start();
        }catch(Exception ex){
            android.util.Log.w(this.getClass().getSimpleName(),"Exception constructing URL"+
                    " "+url+" message "+ex.getMessage());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        Intent add = new Intent(this, AddDetails.class);
        switch(id){
            case R.id.action_settings:
                startActivityForResult(add, 2);
                return true;
            case R.id.refresh_list:
                try {
                    aHandler = new Handler();
                    JsonRPCClientViaThread names = new JsonRPCClientViaThread(new URL(url),
                            aHandler, this, "getMovieList","[ ]");
                    names.start();
                }catch(Exception ex){
                    android.util.Log.w(this.getClass().getSimpleName(),"Exception constructing URL"+
                            " "+url+" message "+ex.getMessage());
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setMovie(String aStuff){
        this.selectedStuff = aStuff;
        try {
            aHandler = new Handler();
            JsonRPCClientViaThread names = new JsonRPCClientViaThread(new URL(url),
                    aHandler, this, "get","[ \""+aStuff+"\" ]");
            names.start();
        }catch(Exception ex){
            android.util.Log.w(this.getClass().getSimpleName(),"Exception constructing URL"+
                    " "+url+" message "+ex.getMessage());
        }
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            if(resultCode == 2){
                MovieDescription itemname= (MovieDescription) data.getSerializableExtra("moviedesc");
                android.util.Log.d(this.getClass().getSimpleName(), itemname.toJsonString());

                try {
                    aHandler = new Handler();
                    JsonRPCClientViaThread names = new JsonRPCClientViaThread(new URL(url),
                            aHandler, this, "add", "[ " + itemname.toJsonString() +" ]");
                    names.start();
                } catch (Exception ex) {
                    android.util.Log.w(this.getClass().getSimpleName(), "Exception constructing URL" +
                            " " + url + " message " + ex.getMessage());
                }
                this.recreate();
               }
        }

        if (requestCode == 4) {
            if(resultCode == 4) {
                String movie = (String) data.getSerializableExtra("name");
                try {
                    aHandler = new Handler();
                    JsonRPCClientViaThread names = new JsonRPCClientViaThread(new URL(url),
                            aHandler, this, "remove", "[ \"" + movie + "\" ]");
                    names.start();
                } catch (Exception ex) {
                    android.util.Log.w(this.getClass().getSimpleName(), "Exception constructing URL" +
                            " " + url + " message " + ex.getMessage());
                }
                this.recreate();
                myListAdapter.notifyDataSetChanged();
            }
        }
    }

    public void changeActivity(View view){
        Intent intent = new Intent(MainActivity.this, AddDetails.class);
        startActivityForResult(intent, 2);
    }


}
