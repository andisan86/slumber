/*
 * Copyright (c) 2017. The Test Guys
 */

package com.thetestguys.slumber.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thetestguys.slumber.pojo.WebObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.*;
import java.util.*;

/**
 * This class handles web objects
 */
public class WebObjects {
    private static Map<String, WebObject> mapWebObjects;

    /**
     * Simple constructor
     */
    public WebObjects() throws Exception {
        mapWebObjects = new HashMap<String, WebObject>();
        readObjectsDefinition();
    }

    /**
     * Read objects definition from JSON catalogue
     */
    private void readObjectsDefinition() throws FileNotFoundException, IOException {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(readJsonToString("/objectsLibrary.json"));
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

    /**
     * Read JSON to String
     * @param filePath Path to JSON file
     * @return String of JSON
     */
    private String readJsonToString(String filePath) throws IOException {
        String message = "";
        InputStream in = getClass().getResourceAsStream(filePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        try {
            message = org.apache.commons.io.IOUtils.toString(reader);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        return message;
    }

    /**
     * Method that returns a web object based on its identifier from JSON library.
     *
     * @param webObjectName object to be found
     * @return web object
     */
    public WebObject getWebObject(String webObjectName) { return mapWebObjects.get(webObjectName); }
}
