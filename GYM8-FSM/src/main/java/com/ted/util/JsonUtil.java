package com.ted.util;

import com.google.gson.*;

import java.util.ArrayList;
import java.util.List;

public class JsonUtil {
    private static final Gson gson = new Gson();

    public static String get(String key, String json) {
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        JsonElement element = jsonObject.get(key);

        // 檢查 key 是否存在，避免 NullPointerException
        if (element == null || element.isJsonNull()) {
            return null;
        }

        // 如果是單純的字串，getAsString() 會去掉雙引號
        // 如果是物件或陣列，toString() 會保留 JSON 結構
        return element.isJsonPrimitive() ? element.getAsString() : element.toString();
    }

    public static List<String> getArray(String key, String json) {
        List<String> list = new ArrayList<>();
        try {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
            JsonElement element = jsonObject.get(key);

            if (element != null && element.isJsonArray()) {
                JsonArray jsonArray = element.getAsJsonArray();
                for (JsonElement e : jsonArray) {
                    list.add(e.isJsonPrimitive() ? e.getAsString() : e.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static JsonArray getJsonArray(String key, String json) {
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        return jsonObject.getAsJsonArray(key);
    }

    public static JsonArray toJsonArray(List<String> list) {
        return new Gson().toJsonTree(list).getAsJsonArray();
    }

    public static JsonObject toJsonObject(String json) {
        return gson.fromJson(json, JsonObject.class);
    }

    public static JsonObject toJsonObject(Object object) {
        JsonElement jsonElement = gson.toJsonTree(object);
        return jsonElement.getAsJsonObject();
    }

    public static String toJson(Object object) {
        return gson.toJson(object);
    }
}
