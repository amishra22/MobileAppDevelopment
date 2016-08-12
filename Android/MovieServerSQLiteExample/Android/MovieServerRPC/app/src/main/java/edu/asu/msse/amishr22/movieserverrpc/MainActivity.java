package edu.asu.msse.amishr22.movieserverrpc;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,
        DialogInterface.OnClickListener, TextView.OnEditorActionListener  {

    public String selectedStuff;
    public ExpandableListView elview;
    public ExpandableStuffAdapter myListAdapter;
    public MovieDescription newMovie;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        elview = (ExpandableListView)findViewById(R.id.lvExp);
        myListAdapter = new ExpandableStuffAdapter(this);
        elview.setAdapter(myListAdapter);

        try{
            MovieDB db = new MovieDB((Context)this);
            SQLiteDatabase crsDB = db.openDB();
            //android.util.Log.w(this.getClass().getSimpleName(),"Here 1");
            Cursor cur = crsDB.rawQuery("select title,genre from movie;", new String[]{});
            android.util.Log.w(this.getClass().getSimpleName(),"Here 2");


            while(cur.moveToNext()){
                try{
                    String tit = cur.getString(0);
                    String gen = cur.getString(1);
                    if(myListAdapter.model.containsKey(gen)){
                        ArrayList<String> l = myListAdapter.model.get(gen);
                        l.add(tit);
                        myListAdapter.model.put(gen, l);
                    }else{
                        ArrayList<String> l = new ArrayList<>();
                        l.add(tit);
                        myListAdapter.model.put(gen, l);
                    }

                    myListAdapter.notifyDataSetChanged();
                }catch(Exception ex){
                    android.util.Log.w(this.getClass().getSimpleName(),"exception stepping through cursor"+ex.getMessage());
                }
            }
        }catch(Exception ex){
            android.util.Log.w(this.getClass().getSimpleName(),"unable to setup student spinner");
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
                this.recreate();
                myListAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setMovie(String aStuff){
        this.selectedStuff = aStuff;
        try{

            MovieDB db = new MovieDB((Context)this);
            SQLiteDatabase crsDB = db.openDB();
            //android.util.Log.w(this.getClass().getSimpleName(),"Here 1");
            Cursor cur = crsDB.rawQuery("select * from movie where title = '"+this.selectedStuff+"';", new String[]{});

            while(cur.moveToNext()){
                try{
                    String tit = cur.getString(0);
                    String year = cur.getString(1);
                    String rel = cur.getString(2);
                    String runt = cur.getString(3);
                    String act = cur.getString(4);
                    String pt = cur.getString(5);
                    String gen = cur.getString(6);
                    String rtd = cur.getString(7);

                    Intent it = new Intent(this, DisplayMovDetails.class);

                    it.putExtra("title", tit);
                    it.putExtra("year", year);
                    it.putExtra("released", rel);
                    it.putExtra("runtime", runt);
                    it.putExtra("actor", act);
                    it.putExtra("plot", pt);
                    it.putExtra("genre", gen);
                    it.putExtra("rated", rtd);
                    startActivityForResult(it, 4);
                    //startActivity(it);
                }catch(Exception ex){
                    android.util.Log.w(this.getClass().getSimpleName(),"exception stepping through cursor"+ex.getMessage());
                }
            }
        }catch(Exception ex){
            android.util.Log.w(this.getClass().getSimpleName(),"unable to setup student spinner");
        }

    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            if (resultCode == 2) {
                //MovieDescription itemname = (MovieDescription) data.getSerializableExtra("moviedesc");
                //Log.d("in ADD : "," hai");
                //android.util.Log.d(this.getClass().getSimpleName(), itemname.toJsonString());
                this.recreate();
            }
        }

        if (requestCode == 4) {
            if (resultCode == 4) {

                Intent intent = getIntent();

                String ttl2 = (String) data.getSerializableExtra("mName");

                try{
                    MovieDB db = new MovieDB((Context)this);
                    SQLiteDatabase crsDB = db.openDB();
                    String mDelete = "DELETE FROM movie where title = '"+ttl2+"';";
                    crsDB.execSQL(mDelete);
                    crsDB.close();
                    db.close();

                }catch(Exception ex){
                    android.util.Log.w(this.getClass().getSimpleName(),"unable to setup movie thing");
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


    @Override
    public void onClick(DialogInterface dialog, int which) {

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
