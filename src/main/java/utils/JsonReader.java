package utils;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.File;
import java.io.IOException;

public class JsonReader {

    public static String getTestData(String fileName, String key) throws IOException, ParseException {
        String testdata;
        return testdata = (String) getJsonData(fileName).get(key);

    }

    public static JSONObject getJsonData(String nameOfFile) throws IOException, ParseException {
        //convert the file to string, then convert it to object
        File fileName = new File(System.getProperty("user.dir") + "/resources/testData/" + nameOfFile);
        //File -> String
        String json = FileUtils.readFileToString(fileName,"UTF-8");
        //Parse the String into Object
        Object obj = new JSONParser().parse(json);
        //convert the Object to jsonObject so that I can return it to the function everytime it called
        JSONObject jsonObject = (JSONObject) obj;
        return jsonObject;
    }

    public static JSONArray getJsonArray(String fileName,String key) throws IOException, ParseException {
        JSONObject jsonObject = getJsonData(fileName);
        JSONArray jsonArray = (JSONArray) jsonObject.get(key);
        return jsonArray;
    }
    public static Object getJsonArrayData(String fileName, String Key) throws IOException, ParseException {
        JSONArray jsonArray = getJsonArray(fileName,Key);
        return jsonArray;
    }

}
