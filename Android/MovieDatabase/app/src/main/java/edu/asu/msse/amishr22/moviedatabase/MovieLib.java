package edu.asu.msse.amishr22.moviedatabase;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;


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



public class MovieLib  extends Object{

        protected Hashtable<String, MovieDesc> movies;

        private MainActivity parent;

        public MovieLib(MainActivity parent){
            movies = new Hashtable<String, MovieDesc>();
            this.parent = parent;
            this.resetMovies();
           // genreParent = new HashMap<String, ArrayList<String>>();
            this.getGenre();
        }

        /*private void debug(String message){
            if(debugOn){
                android.util.Log.d(this.getClass().getSimpleName(), "debug: "+message);
            }
        }*/

        void getGenre(){

            String filename = "movie.json";
            InputStream is = parent.getApplicationContext().getResources().openRawResource(R.raw.movie);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            JSONObject movieJson = null;
            try {
                movieJson = new JSONObject(new JSONTokener(br.readLine()));
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {

                e.printStackTrace();
            }

            Iterator<String> it = movieJson.keys();
            while(it.hasNext()){
                String mTitle = it.next();
                JSONObject aMovie = movieJson.optJSONObject(mTitle);
                MovieDesc md = new MovieDesc(aMovie.toString());
                String gen = md.getGenre();
                if(parent.genreParent.containsKey(gen)){
                    ArrayList<String> l = (ArrayList<String >) parent.genreParent.get(gen);
                    if(l == null){
                        l = new ArrayList<>();
                    }
                    l.add(md.getTitle());
                    parent.genreParent.put(gen,l);
            }else{
                    ArrayList<String> l1 = new ArrayList<>();
                    l1.add(md.getTitle());
                    parent.genreParent.put(gen, l1);
                }
        }

        }

        void resetMovies(){
            movies.clear();

            String filename = "movie.json";
            InputStream is = parent.getApplicationContext().getResources().openRawResource(R.raw.movie);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            JSONObject movieJson = null;
            try {
                movieJson = new JSONObject(new JSONTokener(br.readLine()));
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Iterator<String> it = movieJson.keys();
            while(it.hasNext()){
                String mTitle = it.next();
                JSONObject aMovie = movieJson.optJSONObject(mTitle);
                if(aMovie != null){
                    MovieDesc md = new MovieDesc(aMovie.toString());
                    movies.put(mTitle, md);
                }
            }
        }
}
