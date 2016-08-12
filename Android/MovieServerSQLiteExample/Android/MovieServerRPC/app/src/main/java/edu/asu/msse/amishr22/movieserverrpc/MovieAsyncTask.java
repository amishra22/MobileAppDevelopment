package edu.asu.msse.amishr22.movieserverrpc;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


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



public class MovieAsyncTask extends AsyncTask<String, Void, Boolean> {

    /*
    This class file is used to handle the connection to get the movie details from omdb using asynctask.
     */

    public String link = null;
    private AddDetails parent;
    public JSONObject obj;


    public MovieAsyncTask(AddDetails parent){
        this.parent = parent;
    }
    @Override
    protected Boolean doInBackground(String... params) {

        try {
            URL url = new URL(params[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            /*if (!(urlConnection instanceof HttpURLConnection)) {
                throw new IOException("URL is not an Http URL");
            }*/
            String readStream = readStream(urlConnection.getInputStream());
            obj = new JSONObject(readStream);
            if(obj.getString("Response").equals("False")){
                Log.d(this.getClass().getSimpleName(), " " + obj.getString("Error"));
                return false;
            }else{
                Log.d("The stream is "," " + readStream);
                return true;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(Boolean result) {
       // super.onPostExecute(result);
        if(!result){
            try {
                String temp = obj.getString("Error");
                Log.d(this.getClass().getSimpleName(), " " + temp);
                //Toast.makeText(parent, temp, Toast.LENGTH_SHORT).show();
                parent.showErrorMsg(temp);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }else{
            Log.d("The stream is "," " + "Success");
            try {
                parent.fillDetails(obj);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private String readStream(InputStream in) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String nextLine = "";
            while ((nextLine = reader.readLine()) != null) {
                sb.append(nextLine);
            }
        return sb.toString();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);

    }


}
