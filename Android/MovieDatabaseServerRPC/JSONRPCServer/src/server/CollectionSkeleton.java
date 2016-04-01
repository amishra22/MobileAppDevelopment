package ser598.server.amishr22;

//import java.net.*;
//import java.io.*;
import java.util.*;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONString;

/**
 * Created by Abhinav on 29-02-2016.
 */

public class CollectionSkeleton extends Object {

    MovieCollection mLibrary;

    public CollectionSkeleton (MovieCollection mLibrary){

        this.mLibrary = mLibrary;
    }

    public String callMethod(String request){
        JSONObject result = new JSONObject();
        try{
            JSONObject theCall = new JSONObject(request);
            String method = theCall.getString("method");
            int id = theCall.getInt("id");
            JSONArray params = null;
            if(!theCall.isNull("params")){
                params = theCall.getJSONArray("params");
            }
            result.put("id",id);
            result.put("jsonrpc","2.0");

            if(method.equals("resetFromJsonFile")){
                mLibrary.resetFromJsonFile();
                result.put("result",true);
            }else if(method.equals("saveToJsonFile")){
                boolean saved = mLibrary.saveToJsonFile();
                result.put("result",saved);
            }else if(method.equals("remove")){
                String sName = params.getString(0);
                boolean removed = mLibrary.remove(sName);
                if(removed == true){
                    mLibrary.saveToJsonFile();
                }
                result.put("result",removed);
            }else if(method.equals("add")){
                JSONObject studJson = params.getJSONObject(0);
                Movies stud = new Movies(studJson);
                boolean added = mLibrary.add(stud);
                result.put("result",added);
            }else if(method.equals("get")){
                String sName = params.getString(0);
                Movies stud = mLibrary.get(sName);
                result.put("result",stud.toJson());
            }else if(method.equals("getMovieList")){
                LinkedHashMap names = mLibrary.getMovieList();
                result.put("result",new JSONObject(names));
            }else if(method.equals("getNames")){
                String[] names = mLibrary.getNames();
                result.put("result", names);
            }else if(method.equals("addiOS")){
                String studJson = params.getString(0);
                Movies stud = new Movies(studJson);
                boolean added = mLibrary.add(stud);
                result.put("result", added);
            }
        }
        catch(Exception ex){
            System.out.println("exception in callMethod: "+ ex.getMessage());
        }

        System.out.println("returning: "+result.toString());
        return "HTTP/1.0 200 Data follows\nServer:localhost:8080\nContent-Type:text/plain\nContent-Length:"+(result.toString()).length()+"\n\n"+result.toString();
    }
}


