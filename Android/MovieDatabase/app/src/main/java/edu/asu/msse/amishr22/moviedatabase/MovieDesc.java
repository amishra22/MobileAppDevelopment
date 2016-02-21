package edu.asu.msse.amishr22.moviedatabase;

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



public class MovieDesc extends Object {

    public String title;
    public String year;
    public String rated;
    public String released;
    public String runtime;
    public String genre;
    public String actors;
    public String plot;
    public String director;



    MovieDesc(String jsonStr){

        try{
            JSONObject jo = new JSONObject(jsonStr);
            title = jo.getString("Title");
            year = jo.getString("Year");
            rated = jo.getString("Rated");
            released = jo.getString("Released");
            runtime = jo.getString("Runtime");
            genre = jo.getString("Genre");
            actors = jo.getString("Actors");
            plot = jo.getString("Plot");
            director = jo.getString("Director");
        }catch (Exception ex){
            android.util.Log.w(this.getClass().getSimpleName(),
                    "error converting to/from json");
        }
    }

    public String getTitle() {
        return title;
    }


    public String getYear() {
        return year;
    }


    public String getRated() {
        return rated;
    }


    public String getReleased() {
        return released;
    }


    public String getRuntime() {
        return runtime;
    }


    public String getGenre() {
        return genre;
    }


    public String getActors() {
        return actors;
    }


    public String getPlot() {
        return plot;
    }


    public String getDirector() {
        return director;
    }


}
