package com.androidexamples.app.domain;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcos on 15/01/2017.
 */

public class ParserJsonExemple {

    private List<Example> listExamples;

    public List<Example> getExamples(Context context, String json) {
        List<Example> listExamples = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(json);

            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Example example = new Example();
                example.setId(jsonObject.optInt("id"));
                example.setName(jsonObject.optString("name"));
                example.setDeleted(jsonObject.optBoolean("deleted"));
                listExamples.add(example);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listExamples;
    }
}

class Example {
    private int id;
    private String name;
    private boolean deleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
