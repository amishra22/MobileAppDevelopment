package edu.asu.msse.amishr22.listviewsample;


import android.app.Activity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] heroes = {"Batman", "SuperMan", "Flash", "X Men", "Shaktiman"};
        //ListAdapter myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, foods);

        ListAdapter myAdapter = new CustomAdapter(this, heroes);
        ListView myView = (ListView) findViewById(R.id.myView);
        myView.setAdapter(myAdapter);
        myView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String food = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(MainActivity.this, food, Toast.LENGTH_LONG).show();
                    }
                }

        );




    }

}
