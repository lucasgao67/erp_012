package com.gk.erp012.entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ke.gao on 2017/8/3.
 */

public class DepartTypeEntry {
    private String name;
    private List<DepartEntry> departs = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DepartEntry> getDeparts() {
        return departs;
    }


    public static DepartTypeEntry getFormJson(JSONObject json) throws JSONException {
        DepartTypeEntry departType = new DepartTypeEntry();
        departType.setName(json.getString("name"));
        JSONArray departs = json.getJSONArray("departs");
        for(int i =0;i<departs.length();i++){
            departType.getDeparts().add(DepartEntry.getFormJson(departs.getJSONObject(i)));
        }
        return departType;
    }

}