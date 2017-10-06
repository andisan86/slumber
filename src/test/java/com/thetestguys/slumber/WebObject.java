/*
 * Copyright (c) 2017. The Test Guys
 */

package com.thetestguys.slumber;

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

    public String getObjectName() {
        return objectName;
    }

    public String getObjectNameAttr() {return objectNameAttr; }

    public String getObjectNgIf() { return objectNgIf; }

    public String getObjectNgModel() { return objectNgModel; }

    public String getObjectNgClick() { return objectNgClick; }

    public String getObjectHref() { return objectHref; }

    public String getObjectId() { return objectId; }

    public String getObjectType() { return objectType; }
}
