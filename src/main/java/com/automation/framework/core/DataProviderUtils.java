package com.automation.framework.core;

import com.automation.framework.utils.JsonUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class DataProviderUtils {


    @DataProvider(name = "jsonData")
    public static Object[][] getDataFromJson(Method method) throws IOException, ParseException {
        String key = method.getAnnotation(DataProviderArgument.class).value().split("=")[0];
        String values[] = method.getAnnotation(DataProviderArgument.class).value().split("=")[1].split(",");
        JsonUtils.loadJsonFile("testdata.json");
        JSONArray jsonArr = JsonUtils.getJsonArray(JsonUtils.jsonObject, key);
        int counter = 0;
        Object[][] jObj = new Object[jsonArr.size()][values.length];
        List<String> fields = Arrays.asList(values);
        for (int i = 0; i < jsonArr.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArr.get(i);
            for (int j = 0; j < fields.size(); j++) {
                jObj[counter][j] = JsonUtils.getValueFromJsonObj(jsonObject, fields.get(j));
                System.out.println(jObj[counter][j]);
            }
            counter++;
        }
        return jObj;
    }


    @DataProvider(name = "registrationJson")
    public static Object[][] getRegistrationData(Method method) throws IOException, ParseException {
        String key = method.getAnnotation(DataProviderArgument.class).value().split("=")[0];
        String values[] = method.getAnnotation(DataProviderArgument.class).value().split("=")[1].split(",");
        JsonUtils.loadJsonFile("testdata.json");
        JSONObject jObj = JsonUtils.getJsonObject(key);
        System.out.println(jObj);
        int counter = 0;
        Object[][] object = new Object[1][values.length];
        List<String> field = Arrays.asList(values);
        System.out.println(field);
        for (int i = 0; i < field.size(); i++) {
            object[counter][i] = jObj.get(field.get(i));
        }
        System.out.println(object.toString());
        return object;
    }

}
