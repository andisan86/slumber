/*
 * Copyright (c) 2017. The Test Guys
 */

package com.thetestguys.slumber;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WebObjects {
    private static Map<String, WebObject> mapWebObjects;

    public WebObjects() {
        mapWebObjects = new HashMap<String, WebObject>();
        readObjectsLibrary();
    }

    private void readObjectsLibrary() {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("C:/Users/user/Work/slumber/src/main/resources/objectsLibrary.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray objectList = (JSONArray) jsonObject.get("objects");
            Iterator<JSONObject> iterator = objectList.iterator();
            while (iterator.hasNext()) {
                ObjectMapper mapper = new ObjectMapper();
                WebObject webObject = mapper.readValue(iterator.next().toJSONString(), WebObject.class);
                mapWebObjects.put(webObject.getObjectName(), webObject);
            }
        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WebObject getWebObjectByName(String objectName) {
        return mapWebObjects.get(objectName);
    }
}
