package edu.asu.msse.amishr22.listviewsample;

/**
 * Created by Abhinav on 26-03-2016.
 */

import android.content.Context;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;


public class CustomAdapter extends ArrayAdapter<String>{

    public CustomAdapter(Context context, String[] heroes) {
        super(context,R.layout.custom_row ,heroes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater myInflater = LayoutInflater.from(getContext());
        View customview = myInflater.inflate(R.layout.custom_row, parent, false);

        String singleFoodItem = getItem(position);
        TextView myText = (TextView) customview.findViewById(R.id.myText);
        ImageView myImage = (ImageView) customview.findViewById(R.id.myImage);

        myText.setText(singleFoodItem);
        myImage.setImageResource(R.drawable.batman);
        return customview;
    }
}
