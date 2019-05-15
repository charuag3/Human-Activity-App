package a00267320.ait.com.androidacc;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class InsertRequest {
    private String type;
    private List<Map<String,String>> rows;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Map<String, String>> getRows() {
        return rows;
    }

    public void setRows(List<Map<String, String>> rows) {
        this.rows = rows;
    }

    @JsonIgnore
    public String getJsonString(){
/*        JSONObject json = new JSONObject();
        JSONArray arr = new JSONArray();
        arr.put(rows);
        try {
            json.put("type",type);
            json.put("rows", arr);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json.toString();*/

        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
