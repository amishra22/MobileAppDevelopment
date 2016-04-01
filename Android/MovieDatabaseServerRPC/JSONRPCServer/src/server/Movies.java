package ser598.server.amishr22;

/**
 * Created by Abhinav on 29-02-2016.
 */



import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Vector;
import java.util.Arrays;

public class Movies {
    public String title;
    public String year;
    public String genre;
    public String runtime;
    public String released;
    public String plot;
    public String actor;
    public String rated;


    Movies(String title,String year,String genre,String runtime,String released,String plot,String actor,String rated){
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.runtime = runtime;
        this.released = released;
        this.plot = plot;
        this.actor = actor;
        this.rated = rated;

    }

    Movies(String jsonStr){
        try{
            JSONObject jo = new JSONObject(jsonStr);
            title = jo.getString("Title");
            year = jo.getString("Year");
            runtime = jo.getString("Runtime");
            released = jo.getString("Released");
            plot = jo.getString("Plot");
            actor = jo.getString("Actors");
            genre = jo.getString("Genre");
            rated = jo.getString("Rated");
        }catch (Exception ex){
            System.out.println(this.getClass().getSimpleName()+
                    ": error converting from json string");
        }
    }

    public Movies(JSONObject jsonObj){
        try{
            System.out.println("constructor from json received: "+
                    jsonObj.toString());
            title = jsonObj.getString("Title");
            year = jsonObj.getString("Year");
            runtime = jsonObj.getString("Runtime");
            released = jsonObj.getString("Released");
            plot = jsonObj.getString("Plot");
            actor = jsonObj.getString("Actors");
            genre = jsonObj.getString("Genre");
            rated = jsonObj.getString("Rated");
        }catch(Exception ex){
            System.out.println(this.getClass().getSimpleName()+
                    ": error converting from json string1");
        }
    }

    public JSONObject toJson(){
        JSONObject jo = new JSONObject();
        try{
            jo.put("Title",title);
            jo.put("Year",year);
            jo.put("Genre",genre);
            jo.put("Released",released);
            jo.put("Runtime",runtime);
            jo.put("Rated",rated);
            jo.put("Plot",plot);
            jo.put("Actors",actor);
        }catch (Exception ex){
            System.out.println(this.getClass().getSimpleName()+
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

