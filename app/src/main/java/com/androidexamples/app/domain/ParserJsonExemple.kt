package com.androidexamples.app.domain

import android.content.Context

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

import java.util.ArrayList

/**
 * Created by marcos on 15/01/2017.
 */

class ParserJsonExemple {

    private val listExamples: List<Example>? = null

    fun getExamples(context: Context, json: String): List<Example> {
        val listExamples = ArrayList<Example>()

        try {
            val jsonArray = JSONArray(json)

            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val example = Example()
                example.id = jsonObject.optInt("id")
                example.name = jsonObject.optString("name")
                example.isDeleted = jsonObject.optBoolean("deleted")
                listExamples.add(example)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return listExamples
    }
}

class Example {
    var id: Int = 0
    var name: String? = null
    var isDeleted: Boolean = false
}
