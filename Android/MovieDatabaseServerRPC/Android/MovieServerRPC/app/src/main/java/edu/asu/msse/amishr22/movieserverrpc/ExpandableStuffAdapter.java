package edu.asu.msse.amishr22.movieserverrpc;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.LinkedHashMap;


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

public class ExpandableStuffAdapter extends BaseExpandableListAdapter
        implements View.OnTouchListener,
        ExpandableListView.OnGroupExpandListener,
        ExpandableListView.OnGroupCollapseListener, Serializable{

    private TextView currentSelectedTV = null;
    private MainActivity parent;

    public Handler aHandler;

    //linked hash map ensures consistent order for iteration and toarray.
    public LinkedHashMap<String,String[]> model;

    public ExpandableStuffAdapter(MainActivity parent) {
        Log.d(this.getClass().getSimpleName(),"in constructor so creating new model");

        this.model = new LinkedHashMap<String,String[]>();
        this.parent = parent;
        parent.elview.setOnGroupExpandListener(this);
        parent.elview.setOnGroupCollapseListener(this);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        String[] stuffTitles = model.keySet().toArray(new String[] {});
        return model.get(stuffTitles[groupPosition])[childPosition];
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String) getChild(groupPosition, childPosition);
        if (convertView == null) {
            Log.d(this.getClass().getSimpleName(),"in getChildView null so creating new view");
            LayoutInflater inflater = (LayoutInflater) this.parent
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, null);
        }
        TextView txtListChild = (TextView)convertView.findViewById(R.id.lblListItem);
        convertView.setOnTouchListener(this);
        convertView.setBackgroundResource(R.color.light_blue);
        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        String[] stuffTitles = model.keySet().toArray(new String[] {});
        return model.get(stuffTitles[groupPosition]).length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        String[] stuffTitles = model.keySet().toArray(new String[] {});
        return stuffTitles[groupPosition];
    }

    @Override
    public int getGroupCount() {
        String[] stuffTitles = model.keySet().toArray(new String[] {});
        return stuffTitles.length;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String)getGroup(groupPosition);
        if (convertView == null) {
            Log.d(this.getClass().getSimpleName(),"in getGroupView null so creating new view");
            LayoutInflater inflater = (LayoutInflater) this.parent
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        String[] stuffTitles = model.keySet().toArray(new String[] {});
        return true;
    }

    public boolean onTouch(View v, MotionEvent event){
        if (event.getAction()==MotionEvent.ACTION_DOWN){
            if(v instanceof android.widget.LinearLayout){
                android.widget.LinearLayout layView = (android.widget.LinearLayout)v;
                for(int i=0; i<=layView.getChildCount(); i++){
                    if(layView.getChildAt(i) instanceof TextView){
                        if (currentSelectedTV != null){
                            currentSelectedTV.setBackgroundColor(
                                    parent.getResources().getColor(R.color.light_blue));
                        }
                        TextView movie1 = ((TextView)layView.getChildAt(i));
                        movie1.setBackgroundColor(Color.BLUE);
                        currentSelectedTV = movie1;
                        parent.setMovie(movie1.getText().toString());

                    }
                }
            }

            if(v instanceof TextView){
                Log.d(this.getClass().getSimpleName(),"in onTouch called for: " +
                        ((TextView)v).getText());
            }
        }
        return true;
    }

    public void onGroupExpand(int groupPosition){
        Log.d(this.getClass().getSimpleName(),"in onGroupExpand called for: "+
                model.keySet().toArray(new String[] {})[groupPosition]);
        if (currentSelectedTV != null){
            currentSelectedTV.setBackgroundColor(parent.getResources().getColor(R.color.light_blue));
            currentSelectedTV = null;
        }
        for (int i=0; i< this.getGroupCount(); i++) {
            if(i != groupPosition){
                parent.elview.collapseGroup(i);
            }
        }
    }

    public void onGroupCollapse(int groupPosition){
        Log.d(this.getClass().getSimpleName(),"in onGroupCollapse called for: "+
                model.keySet().toArray(new String[] {})[groupPosition]);
        if (currentSelectedTV != null){
            currentSelectedTV.setBackgroundColor(parent.getResources().getColor(R.color.light_blue));
            currentSelectedTV = null;
        }
    }

    public void updateList(String movieName){
        model.remove(movieName);
    }


   }



