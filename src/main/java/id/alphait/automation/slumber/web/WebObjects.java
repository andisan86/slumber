package id.alphait.automation.slumber.web;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Table;
import com.paulhammant.ngwebdriver.ByAngular;
import id.alphait.automation.slumber.pojo.ObjectsPojo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class handles web objects
 */
public class WebObjects {
    private WebDriver webDriver;
    private String objectName;
    private String objectType;
    private String objectId;
    private String objectNgModel;
    private String objectNgClick;
    private String objectNgIf;
    private String objectHref;
    private String objectNameAttr;
    private List<ObjectsPojo.Objects> allWebObjectsList;

    /**
     * Simple constructor
     */
    public WebObjects() {
        readObjectsDefinition();
    }

    /**
     * Constructor
     * @param webDriver WebDriver
     */
    public WebObjects(WebDriver webDriver) {
        this.webDriver = webDriver;
        readObjectsDefinition();
    }

    /**
     * Read objects definition from JSON catalogue
     */
    private void readObjectsDefinition() {
        ObjectsPojo objectsPojo = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            objectsPojo = mapper.readValue(readJsonToString("/objectsLibrary.json"), ObjectsPojo.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObjectsPojo.Objects[] objects = objectsPojo.getObjects();
        List<ObjectsPojo.Objects> webObjectsList = new ArrayList<ObjectsPojo.Objects>(Arrays.asList(objects));
        Stream<ObjectsPojo.Objects> stream = Stream.of(webObjectsList.stream()).flatMap(x->x);
        allWebObjectsList = new ArrayList<ObjectsPojo.Objects>(Arrays.asList(stream.toArray(ObjectsPojo.Objects[]::new)));
    }

    /**
     * Read JSON to String
     * @param filePath Path to JSON file
     * @return String of JSON
     */
    private String readJsonToString(String filePath) {
        String message = "";
        InputStream in = getClass().getResourceAsStream(filePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        try {
            message = org.apache.commons.io.IOUtils.toString(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }

    /**
     * Set web object
     * @param webObjectName WebObject's name
     */
    public void setWebObject(String webObjectName) {
        List<ObjectsPojo.Objects> result = allWebObjectsList.stream()
                .filter(a -> a.getObjectName().equals(webObjectName))
                .collect(Collectors.toList());
        ObjectsPojo.Objects found = result.get(0);
        if (found.equals(null)) throw new IllegalArgumentException("No object found with this name: " + webObjectName);
        setObjectName(found.getObjectName());
        setObjectType(found.getObjectType());
        setObjectId(found.getObjectId());
        setObjectNgModel(found.getObjectNgModel());
        setObjectNgClick(found.getObjectNgClick());
        setObjectNgIf(found.getObjectNgIf());
        setObjectHref(found.getObjectHref());
        setObjectNameAttr(found.getObjectNameAttr());
    }

    /**
     * Method that returns a web object based on its identifier from JSON library.
     *
     * @param webObjectName object to be found
     * @return web object
     */
    public WebElement getWebObject(String webObjectName) {
        WebElement webObject = null;
        setWebObject(webObjectName);
        if (!getObjectId().isEmpty()) {
            webObject = webDriver.findElement(By.id(getObjectId()));
        } else if (!getObjectNameAttr().isEmpty()) {
            webObject = webDriver.findElement(By.name(getObjectNameAttr()));
        } else if (!getObjectNgModel().isEmpty()) {
            webObject = webDriver.findElement(ByAngular.model(getObjectNgModel()));
        } else if (!getObjectNgClick().isEmpty()) {
            webObject = webDriver.findElement(By.xpath("//" + getObjectType() + "[contains(@ng-click, '" + getObjectNgClick() + "')]"));
        } else if (!getObjectNgIf().isEmpty()) {
            webObject = webDriver.findElement(By.xpath("//" + getObjectType() + "[contains(@ng-if, '" + getObjectNgIf() + "')]"));
        } else if (!getObjectHref().isEmpty()) {
            webObject = webDriver.findElement(By.xpath("//" + getObjectType() + "[contains(@href, '" + getObjectHref() + "')]"));
        } else {    // raw xpath form
            webObject = webDriver.findElement(By.xpath(webObjectName));
        }
        return webObject;
    }

    /**
     * Getter for object name.
     *
     * @return Object name.
     */
    public String getObjectName() {
        return objectName;
    }

    /**
     * Setter for object name.
     *
     * @param objectName New object name.
     */
    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    /**
     * Getter for object type.
     *
     * @return Object type.
     */
    public String getObjectType() {
        return objectType;
    }

    /**
     * Setter for object type.
     *
     * @param objectType New object type.
     */
    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    /**
     * Getter for object id.
     *
     * @return Object id.
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     * Setter for object id.
     *
     * @param objectId New object id.
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    /**
     * Getter for object ngmodel.
     *
     * @return Object ngmodel.
     */
    public String getObjectNgModel() {
        return objectNgModel;
    }

    /**
     * Setter for object ngmodel.
     *
     * @param objectNgModel New ngmodel.
     */
    public void setObjectNgModel(String objectNgModel) {
        this.objectNgModel = objectNgModel;
    }

    /**
     * Getter for object ngclick.
     *
     * @return Object ngclick.
     */
    public String getObjectNgClick() {
        return objectNgClick;
    }

    /**
     * Setter for object ngclick.
     *
     * @param objectNgClick New ngclick.
     */
    public void setObjectNgClick(String objectNgClick) {
        this.objectNgClick = objectNgClick;
    }

    /**
     * Getter for object ngif.
     *
     * @return Object ngif.
     */
    public String getObjectNgIf() {
        return objectNgIf;
    }

    /**
     * Setter for object ngif.
     *
     * @param objectNgIf New object ngif.
     */
    public void setObjectNgIf(String objectNgIf) {
        this.objectNgIf = objectNgIf;
    }

    /**
     * Getter for object href.
     *
     * @return Object href.
     */
    public String getObjectHref() {
        return objectHref;
    }

    /**
     * Setter for object href.
     *
     * @param objectHref Object href.
     */
    public void setObjectHref(String objectHref) {
        this.objectHref = objectHref;
    }

    /**
     * Getter for object name attribute.
     *
     * @return Object name attribute.
     */
    public String getObjectNameAttr() {
        return objectNameAttr;
    }

    /**
     * Setter for object name attribute.
     *
     * @param objectNameAttr Object name attribute.
     */
    public void setObjectNameAttr(String objectNameAttr) {
        this.objectNameAttr = objectNameAttr;
    }
}
