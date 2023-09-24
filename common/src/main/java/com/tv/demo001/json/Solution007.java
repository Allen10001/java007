package com.tv.demo001.json;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**

 {
    "id" : "xxx",
    "works": [
        {
        "aa": "bb",
        "cc": "dd"
        },
        {
        "ee": "fff",
        "gg": "hh"
        }
    ],
    "award" : {
        "id" : "xxx",
        "awardName" : "最佳演员"
    }
 }
 */
public class Solution007 {

    private static final Gson gson = new Gson();
    private static Map<List<String>, String> res = new HashMap<>();

    public static void main(String[] args) {
        String jsonStr = "{\n"
            + "\t\"id\" : \"xxx\",\n"
            + "\t\"works\": [\n"
            + "\t\t{\n"
            + "\t\t\t\"aa\": \"bb\",\n"
            + "\t\t\t\"cc\": \"dd\"\n"
            + "\t\t},\n"
            + "\t\t{\n"
            + "\t\t\t\"ee\": \"fff\",\n"
            + "\t\t\t\"gg\": \"hh\"\n"
            + "\t\t}\n"
            + "\t],\n"
            + "\t\"award\" : {\n"
            + "\t\t\"id\" : \"xxx\",\n"
            + "\t\t\"awardName\" : \"最佳演员\" \n"
            + "\t}\n"
            + "}";
        JsonObject target = gson.fromJson(jsonStr, JsonObject.class);
        Solution007 solution007 = new Solution007();
        solution007.solution(target, new ArrayList<>());

        for (Map.Entry<List<String>, String> entry : res.entrySet()) {
            StringBuffer sb = new StringBuffer();
            entry.getKey().forEach(item -> sb.append(item).append("."));

            System.out.println(sb.subSequence(0, sb.length()-1).toString().concat("=").concat(entry.getValue()));
        }
    }

    public void solution(JsonObject jsonObject, List<String> path) {

        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            if (entry.getValue().isJsonPrimitive()) {
                path.add(entry.getKey());
                res.put(new ArrayList<>(path), entry.getValue().getAsString());
                path.remove(path.size()-1);
            } else if (entry.getValue().isJsonObject()) {
                path.add(entry.getKey());
                solution(entry.getValue().getAsJsonObject(), path);
                path.remove(path.size()-1);
            } else if (entry.getValue().isJsonArray()) {
                String arrayKey = entry.getKey();
                JsonArray jsonArray = entry.getValue().getAsJsonArray();
                for (int i=0; i<jsonArray.size(); i++) {
                    path.add(arrayKey.concat("[").concat(String.valueOf(i)).concat("]"));
                    solution(jsonArray.get(i).getAsJsonObject(), path);
                    path.remove(path.size()-1);
                }
            }
        }

    }

}
