package com.automation.framework.utils;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JsonUtils {

    public static JSONObject jsonObject;

    public static void loadJsonFile(String filename) throws IOException, ParseException {

        File filepath = new File(System.getProperty("user.dir") + "/src/main/resources/" + filename);
        JSONParser jsonParser = new JSONParser();
        jsonObject = (JSONObject) jsonParser.parse(new FileReader(filepath));
    }

    public static JSONObject getObjFromJsonObj(JSONObject obj, String objName) {
        return (JSONObject) jsonObject.get(objName);
    }

    public static JSONArray getJsonArray(JSONObject obj, String objName) {
        return (JSONArray) jsonObject.get(objName);
    }

    public static String getValueFromJsonObj(JSONObject obj, String value) {
        return (String) obj.get(value);
    }

}
