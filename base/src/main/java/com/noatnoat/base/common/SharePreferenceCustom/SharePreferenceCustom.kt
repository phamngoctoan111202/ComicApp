package com.noatnoat.base.common.SharePreferenceCustom

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object SharePreferenceCustom {
    fun saveMap(context: Context, key: String, value: String) {
        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("MyMap", null)
        val type = object : TypeToken<Map<String, String>>() {}.type
        var map: MutableMap<String, String> = gson.fromJson(json, type) ?: mutableMapOf()

        if (!map.containsKey(key)) {
            map[key] = value

            val editor = sharedPreferences.edit()
            val newJson = gson.toJson(map)
            editor.putString("MyMap", newJson)
            editor.apply()
        }
    }


    fun getMap(context: Context): Map<String, String>? {
        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("MyMap", null)
        val type = object : TypeToken<Map<String, String>>() {}.type
        return gson.fromJson(json, type)
    }
}