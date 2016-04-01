package edu.asu.msse.amishr22.movieserverrpc;

import org.json.JSONObject;

import java.io.Serializable;

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

public class MovieDescription implements Serializable {
    public String title;
    public String year;
    public String rated;
    public String released;
    public String runtime;
    public String genre;
    public String actors;
    public String plot;

    public MovieDescription(String jsonStr){
        try {
            JSONObject j = new JSONObject(jsonStr);
            title = j.getString("Title");
            year = j.getString("Year");
            rated = j.getString("Rated");
            released = j.getString("Released");
            runtime = j.getString("Runtime");
            genre = j.getString("Genre");
            actors = j.getString("Actors");
            plot = j.getString("Plot");

        }
        catch (Exception ex){
            android.util.Log.w(this.getClass().getSimpleName(),
                    "error converting to/from JSON");
        }
    }

    public MovieDescription(JSONObject jsonObj){
        try{
            System.out.println("constructor from json received: "+
                    jsonObj.toString());
            title = jsonObj.getString("Title");
            year = jsonObj.getString("Year");
            rated = jsonObj.getString("Rated");
            released = jsonObj.getString("Released");
            runtime = jsonObj.getString("Runtime");
            actors = jsonObj.getString("Actors");
            genre = jsonObj.getString("Genre");
            plot = jsonObj.getString("Plot");

        }catch(Exception ex) {
            System.out.println(this.getClass().getSimpleName() +
                    ": error converting from json string");
        }
    }

    public JSONObject toJson(){
        JSONObject jo = new JSONObject();
        try{
            jo.put("Title",title);
            jo.put("Year",year);
            jo.put("Rated",rated);
            jo.put("Runtime",runtime);
            jo.put("Released",released);
            jo.put("Actors",actors);
            jo.put("Genre",genre);
            jo.put("Plot",plot);
        }catch (Exception ex){
            System.out.println(this.getClass().getSimpleName() +
                    ": error converting to json");
        }
        return jo;
    }

    public String toJsonString(){
        String ret = "";
        try{
            ret = this.toJson().toString();
        }catch (Exception ex){
            System.out.println(this.getClass().getSimpleName()+
                    ": error converting to json string");
        }
        return ret;
    }
}
