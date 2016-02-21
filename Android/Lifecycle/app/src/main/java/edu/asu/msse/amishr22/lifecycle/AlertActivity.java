package edu.asu.msse.amishr22.lifecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
* @author Abhinav Mishra mailto:amishr22@asu.edu
* MS Software Engineering, CIDSE
* @version January 2016
*/


public class AlertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

        Button buttonAlert = (Button) findViewById(R.id.button2);

        buttonAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //android.util.Log.w("DialogActivity:", " called finishDialog");
                AlertActivity.this.finish();
            }
        });
    }

    public void onStart()
    {
        super.onStart();
        android.util.Log.w(this.getClass().getSimpleName(), "Alert OnStart()");
    }
    public void onRestart()
    {
        super.onRestart();
        android.util.Log.w(this.getClass().getSimpleName(), "Alert OnRestart()");
    }

    public void onResume()
    {
        super.onResume();
        android.util.Log.w(this.getClass().getSimpleName(), "Alert OnResume()");
    }

    public void onPause()
    {
        super.onPause();
        android.util.Log.w(this.getClass().getSimpleName(), "Alert OnPause()");
    }

    public void onStop()
    {
        super.onStop();
        android.util.Log.w(this.getClass().getSimpleName(), "Alert OnStop()");
    }

    public void onDestroy()
    {
        super.onDestroy();
        android.util.Log.w(this.getClass().getSimpleName(), "Alert OnDestroy()");
    }

}
