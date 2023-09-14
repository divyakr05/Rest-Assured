package userManagement;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.File;
import java.io.IOException;

public class JsonReader {

    public static String getTestData(String key) throws IOException, ParseException {
        String testdata;
        return testdata = (String) getJsonData().get(key);

    }

    public static JSONObject getJsonData() throws IOException, ParseException {
        //convert the file to string, then convert it to object
        File fileName = new File("resources/testData/testData.json");
        //File -> String
        String json = FileUtils.readFileToString(fileName,"UTF-8");
        //Parse the String into Object
        Object obj = new JSONParser().parse(json);
        //convert the Object to jsonObject so that I can return it to the function everytime it called
        JSONObject jsonObject = (JSONObject) obj;
        return jsonObject;
    }
}
