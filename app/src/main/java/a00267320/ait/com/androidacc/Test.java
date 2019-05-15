package a00267320.ait.com.androidacc;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        InsertRequest req = new InsertRequest();
        req.setType("insert");
        List<Map<String,String>> valuesList = new ArrayList<>();
        Map<String,String> values = new HashMap<>();
        values.put(Constants.TIME, System.currentTimeMillis() + "");
        values.put("xcoor", "0");
        values.put("ycoor", "3");
        values.put("zcoor", "45");
        values.put("xaxis", "6");
        values.put("yaxis", "7");
        values.put("zaxis", "9");
valuesList.add(values);
req.setRows(valuesList);
        System.out.println(req.getJsonString());
        Gson gson = new Gson();

        Map<String, String> values2 = new HashMap<>();
        values2.put("key1","val1");
        values2.put("key2","value2");
        System.out.print(gson.toJson(values2));
    }
}
