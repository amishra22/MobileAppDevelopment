package ser598.server.amishr22;

import org.json.JSONObject;

import java.util.LinkedHashMap;

/**
 * Created by Abhinav on 29-02-2016.
 */
public interface MovieCollection {
    public boolean saveToJsonFile();
    public boolean resetFromJsonFile();
    public boolean add(Movies stud);
    public boolean remove(String aName);
    public Movies get(String aName);
    public String[] getNames();
    public LinkedHashMap getMovieList();
}