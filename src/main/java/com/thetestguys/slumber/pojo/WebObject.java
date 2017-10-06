/*
 * Copyright (c) 2017. The Test Guys
 */

package com.thetestguys.slumber.pojo;

/**
 * This class describes POJO for web object. Each web object is defined in objectsLibrary.json and described by its web element charateristics (ID, NGModel, NGClick, NGIf, Href, Name)
 *
 * @author Andi Santoso
 *
 */

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WebObject {
    public final String objectNameAttr;
    public final String objectNgIf;
    public final String objectNgModel;
    public final String objectNgClick;
    public final String objectName;
    public final String objectHref;
    public final String objectId;
    public final String objectType;

    /**
     * WebObject POJO attributes
     *
     * @param objectNameAttr Object attribute
     * @param objectNgIf Object ng-if
     * @param objectNgModel Object ng-model
     * @param objectNgClick Object ng-click
     * @param objectName Object name
     * @param objectHref Object href
     * @param objectId Object id
     * @param objectType Object type
     */
    @JsonCreator
    public WebObject(@JsonProperty("objectNameAttr") String objectNameAttr, @JsonProperty("objectNgIf") String objectNgIf, @JsonProperty("objectNgModel") String objectNgModel, @JsonProperty("objectNgClick") String objectNgClick, @JsonProperty("objectName") String objectName, @JsonProperty("objectHref") String objectHref, @JsonProperty("objectId") String objectId, @JsonProperty("objectType") String objectType){
        this.objectNameAttr = objectNameAttr;
        this.objectNgIf = objectNgIf;
        this.objectNgModel = objectNgModel;
        this.objectNgClick = objectNgClick;
        this.objectName = objectName;
        this.objectHref = objectHref;
        this.objectId = objectId;
        this.objectType = objectType;
    }

    /**
     * Get object name value
     *
     * @return object name value
     */
    public String getObjectName() {
        return objectName;
    }

    /**
     * Get object attribute value
     *
     * @return object attribute value
     */
    public String getObjectNameAttr() {return objectNameAttr; }

    /**
     * Get object ng-if value
     *
     * @return ng-if value
     */
    public String getObjectNgIf() { return objectNgIf; }

    /**
     * Get object ng-model value
     *
     * @return ng-model value
     */
    public String getObjectNgModel() { return objectNgModel; }

    /**
     * Get object ng-click value
     *
     * @return ng-click value
     */
    public String getObjectNgClick() { return objectNgClick; }

    /**
     * Get object href value
     *
     * @return href value
     */
    public String getObjectHref() { return objectHref; }

    /**
     * Get object id value
     *
     * @return id value
     */
    public String getObjectId() { return objectId; }

    /**
     * Get object type value
     *
     * @return type value
     */
    public String getObjectType() { return objectType; }
}
