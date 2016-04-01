package ser598.server.amishr22;

/**
 * Created by Abhinav on 29-02-2016.
 */
import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;

import org.json.JSONObject;
import org.json.JSONTokener;


class MovieCollectionImpl extends Object implements MovieCollection{

    public Hashtable<String,Movies> movies;
    private static final boolean debugOn = false;
    private static final String studentJsonFileName = "movie.json";

    public MovieCollectionImpl() {
        debug("creating a new collection");
        movies = new Hashtable<String,Movies>();
        this.resetFromJsonFile();
    }

    private void debug(String message) {
        if (debugOn)
            System.out.println("debug: "+message);
    }

    public boolean resetFromJsonFile(){
        boolean ret = true;
        try{
            movies.clear();
            String fileName = studentJsonFileName;
            File f = new File(fileName);
            FileInputStream is = new FileInputStream(f);
            JSONObject studentMap = new JSONObject(new JSONTokener(is));
            Iterator<String> it = studentMap.keys();
            while (it.hasNext()){
                String mType = it.next();
                JSONObject studentJson = studentMap.optJSONObject(mType);
                Movies stud = new Movies(studentJson);
                movies.put(stud.title, stud);
                debug("added " + stud.title + " : " + stud.toJsonString() +
                        "\nmovies.size() is: " + movies.size());
            }
        }catch (Exception ex){
            System.out.println("Exception reading json file: "+ex.getMessage());
            ret = false;
        }
        return ret;
    }

    public boolean saveToJsonFile(){
        boolean ret = true;
        try {
            String jsonStr;
            JSONObject obj = new JSONObject();
            for (Enumeration<String> e = movies.keys(); e.hasMoreElements();){
                Movies aStud = movies.get((String)e.nextElement());
                obj.put(aStud.title,aStud.toJson());
            }
            PrintWriter out = new PrintWriter(studentJsonFileName);
            out.println(obj.toString(2));
            out.close();
        }catch(Exception ex){
            ret = false;
        }
        return ret;
    }

    public boolean add(Movies aStud){
        boolean ret = true;
        debug("adding movie named: "+((aStud==null)?"unknown":aStud.title));
        try{
            movies.put(aStud.title, aStud);
        }catch(Exception ex){
            ret = false;
        }
        this.saveToJsonFile();
        return ret;
    }


    public boolean remove(String aName){
        debug("removing student named: "+aName);
        return ((movies.remove(aName)==null)?false:true);
    }

    public LinkedHashMap getMovieList(){
        LinkedHashMap<String, String[]> model = new LinkedHashMap<>();
        debug("generating movie lists based on genre");
        for(Map.Entry<String, Movies> entry : movies.entrySet()) {
            if(model.containsKey(entry.getValue().genre)){
                int length = model.get(entry.getValue().genre).length;
                String[] tmp = new String[length+1];
                for(int i=0 ;i<length;i++){
                    tmp[i] = model.get(entry.getValue().genre)[i];
                }
                tmp[length] = entry.getKey();
                model.put(entry.getValue().genre, tmp);
            }
            else {
                model.put(entry.getValue().genre, new String[]{entry.getKey()});
            }
        }

        return model;
    }

    public String[] getNames(){
        String[] ret = {};
        debug("getting "+ movies.size()+" student names.");
        if(movies.size()>0){
            ret = (String[])(movies.keySet()).toArray(new String[0]);
        }
        return ret;
    }

    public Movies get(String aName){
        Movies ret = new Movies("unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown");
        Movies aStud = movies.get(aName);
        if (aStud != null) {
            ret = aStud;
        }
        return ret;
    }


}

