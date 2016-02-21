package edu.asu.msse.amishr22.moviedatabase;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
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
* @version 1.0 Feburary 2016
*/


public class ExpandableStuffAdapter extends BaseExpandableListAdapter
        implements View.OnTouchListener,
        ExpandableListView.OnGroupExpandListener,
        ExpandableListView.OnGroupCollapseListener{
    private TextView currentSelectedTV = null;
    private MainActivity parent;

    //linked hash map ensures consistent order for iteration and toarray.
    private HashMap<String,ArrayList<String>> model;
    private String[][] webReferences;

    public ExpandableStuffAdapter(MainActivity parent) {
        android.util.Log.d(this.getClass().getSimpleName(),"in constructor so creating new model");
        // create a model for this expandible list view. Usually this info would be in a separate class,
        // app, database, or obtained via the network.
        this.model = new HashMap<String,ArrayList<String>>();

         model = parent.genreParent;
        //model.put("Animation", new String[]{"Frozen","Up","Kung Fu Panda","WALL E","How to Train Your Dragon"});
        //model.put("Comedy", new String[]{"The Ugly Truth","Internship","50 First Dates","Movie 43"});
        //model.put("Drama", new String[]{"Forrest Gump","Fight Club","The Shawshank Redemption","The Martian"});
        this.parent = parent;
        parent.elview.setOnGroupExpandListener(this);
        parent.elview.setOnGroupCollapseListener(this);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        //String[] genre = mlib.getGenreName();
        String[] stuffTitles = model.keySet().toArray(new String[]{});
        return model.get(stuffTitles[groupPosition]).get(childPosition);
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
            android.util.Log.d(this.getClass().getSimpleName(),"in getChildView null so creating new view");
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
        //android.util.Log.d(this.getClass().getSimpleName(),"in getChildrenCount for: "+groupPosition+" returning: "+
        //                   model.get(stuffTitles[groupPosition]).length);
        return model.get(stuffTitles[groupPosition]).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        String[] stuffTitles = model.keySet().toArray(new String[] {});
        //android.util.Log.d(this.getClass().getSimpleName(),"in getGroup returning: "+stuffTitles[groupPosition]);
        return stuffTitles[groupPosition];
    }

    @Override
    public int getGroupCount() {
        String[] stuffTitles = model.keySet().toArray(new String[] {});
        //android.util.Log.d(this.getClass().getSimpleName(),"in getGroupCount returning: "+stuffTitles.length);
        return stuffTitles.length;
    }

    @Override
    public long getGroupId(int groupPosition) {
        //android.util.Log.d(this.getClass().getSimpleName(),"in getGroupPosition returning: "+groupPosition);
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String)getGroup(groupPosition);
        if (convertView == null) {
            android.util.Log.d(this.getClass().getSimpleName(),"in getGroupView null so creating new view");
            LayoutInflater inflater = (LayoutInflater) this.parent
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
        //android.util.Log.d(this.getClass().getSimpleName(),"in getGroupView text is: "+lblListHeader.getText());
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
        ImageView img = (ImageView)convertView.findViewById(R.id.group_image);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        //android.util.Log.d(this.getClass().getSimpleName(),"in hasStableIds returning false");
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        String[] stuffTitles = model.keySet().toArray(new String[] {});
        //android.util.Log.d(this.getClass().getSimpleName(),"in isChildSelectable?  "+
        //                   model.get(stuffTitles[groupPosition])[childPosition]);
        return true;
    }

    // handle touches on list items by changing color to yellow, and creating an intent to
    // request an appropriate web page to be loaded into a web view of the WebViewActivity
    // have the WebViewActivity return information back to the main activity.
    public boolean onTouch(View v, MotionEvent event){
        // when the user touches an item, onTouch is called for action down and again for action up
        // we only want to do something on one of those actions. event tells us which action.
        if (event.getAction()==MotionEvent.ACTION_DOWN){
            // onTouch is passed the textview's parent view, a linearlayout - what we set the
            // event on. Look at its children to find the textview
            if(v instanceof android.widget.LinearLayout){
                android.widget.LinearLayout layView = (android.widget.LinearLayout)v;
                // the layout (from list_item.xml should only have one child, but, here's how
                // you find the children of a layout or other view group.
                for(int i=0; i<=layView.getChildCount(); i++){
                    if(layView.getChildAt(i) instanceof TextView){
                        // keep track of TV stuff was most recently touched to un-highlighted
                        if (currentSelectedTV != null){
                            currentSelectedTV.setBackgroundColor(
                                    parent.getResources().getColor(R.color.dark_blue));
                        }
                        TextView tmp = ((TextView)layView.getChildAt(i));
                        tmp.setBackgroundColor(Color.YELLOW);
                        currentSelectedTV = tmp;
                        parent.setSelectedStuff(tmp.getText().toString());
                        // create an intent (in the name of the parent activity) to start the WebViewActivity
                        // pass the web view activity two strings: the url and the name of the selected item.
                        // expect the WebViewActivity to return a result, which will be picked up in the
                        // requesting activity -- MainActivity.

                        MovieLib m = parent.mlib;
                        MovieDesc mdes = m.movies.get(tmp.getText().toString());

                        Intent web = new Intent(parent, MovieDisplay.class);
                        //String aURL = findURL(((TextView) layView.getChildAt(i)).getText().toString());
                        //android.util.Log.d(this.getClass().getSimpleName(), "in onTouch, TextView " +
                        //      ((TextView)layView.getChildAt(i)).getText()+" selected loading URL "+aURL);
                       //web.putExtra("title", parent.mlib.movies.get(tmp.getText().toString()).toJsonString());
                        web.putExtra("Title", mdes.getTitle());
                        web.putExtra("Genre",mdes.getGenre());
                        web.putExtra("Runtime",mdes.getRuntime());
                        web.putExtra("Rated",mdes.getRated());
                        web.putExtra("Released",mdes.getReleased());
                        web.putExtra("Actors",mdes.getActors());
                        web.putExtra("Year",mdes.getYear());
                        web.putExtra("Plot",mdes.getPlot());
                        web.putExtra("Director",mdes.getDirector());
                        parent.startActivity(web);
                    }
                }
            }
            // code below should never executes. onTouch is called for textview's linearlayout parent
            if(v instanceof TextView){
                android.util.Log.d(this.getClass().getSimpleName(),"in onTouch called for: " +
                        ((TextView)v).getText());
            }
        }
        return true;
    }

    // method to get the url corresponding to the item name selected. this should be part of the
    // model and usually not included in the controller. Same goes for the LinkedHashMap and webreferences
    // array of url's.





    public void onGroupExpand(int groupPosition){
        android.util.Log.d(this.getClass().getSimpleName(),"in onGroupExpand called for: "+
                model.keySet().toArray(new String[] {})[groupPosition]);
        if (currentSelectedTV != null){
            currentSelectedTV.setBackgroundColor(parent.getResources().getColor(R.color.light_blue));
            currentSelectedTV = null;
        }
        for (int i=0; i< this.getGroupCount(); i++) {
            if(i != groupPosition){
                //parent.elview.collapseGroup(i);
            }
        }
    }

    public void onGroupCollapse(int groupPosition){
        android.util.Log.d(this.getClass().getSimpleName(),"in onGroupCollapse called for: "+
                model.keySet().toArray(new String[] {})[groupPosition]);
        if (currentSelectedTV != null){
            currentSelectedTV.setBackgroundColor(parent.getResources().getColor(R.color.light_blue));
            currentSelectedTV = null;
        }
    }
}
