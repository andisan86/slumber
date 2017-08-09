package com.thetestguys.slumber.pojo;

/**
 * This class describes POJO for web objects. Each web object is defined in objectsLibrary.json and described by its web element charateristics (ID, NGModel, NGClick, NGIf, Href, Name)
 * 
 * @author Andi Santoso
 * 
 */
public class ObjectsPojo {
    private Objects[] objects;

    /**
     * Getter objects
     * 
     * @return objects
     */
    public Objects[] getObjects() {
        return objects;
    }

    /**
     * Setter objects
     * 
     * @param objects objects
     */
    public void setObjects (Objects[] objects) {
        this.objects = objects;
    }

    /**
     * Objects itself
     * 
     * @author Andi Santoso
     *
     */
    public static class Objects {
        private String objectName;
        private String objectType;
        private String objectId;
        private String objectNgModel;
        private String objectNgClick;
        private String objectNgIf;
        private String objectHref;
        private String objectNameAttr;

        /**
         * Getter object name
         * 
         * @return object name
         */
        public String getObjectName() {
            return objectName;
        }

        /**
         * Setter object name
         * 
         * @param objectName objectName
         */
        public void setObjectName(String objectName) {
            this.objectName = objectName;
        }

        /**
         * Getter object type
         * 
         * @return object type
         */
        public String getObjectType() {
            return objectType;
        }

        /**
         * Setter object type
         * 
         * @param objectType objectType
         */
        public void setObjectType(String objectType) {
            this.objectType = objectType;
        }

        /**
         * Getter object ID
         * 
         * @return object ID
         */
        public String getObjectId() {
            return objectId;
        }

        /**
         * Setter object ID
         * 
         * @param objectId objectID
         */
        public void setObjectId(String objectId) {
            this.objectId = objectId;
        }
        
        /**
         * Getter object NGModel
         * 
         * @return object NGModel
         */
        public String getObjectNgModel() {
            return objectNgModel;
        }

        /**
         * Setter object NGModel
         * 
         * @param objectNgModel objectNgModel
         */
        public void setObjectNgModel(String objectNgModel) {
            this.objectNgModel = objectNgModel;
        }

        /**
         * Getter object NGClick
         * 
         * @return object NGClick
         */
        public String getObjectNgClick() {
            return objectNgClick;
        }

        /**
         * Setter object NGClick
         * 
         * @param objectNgClick objectNgClick
         */
        public void setObjectNgClick(String objectNgClick) {
            this.objectNgClick = objectNgClick;
        }

        /**
         * Getter object NGIf
         * 
         * @return object NGIf
         */
        public String getObjectNgIf() {
            return objectNgIf;
        }

        /**
         * Setter object NGIf
         * 
         * @param objectNgIf objectNgIf
         */
        public void setObjectNgIf(String objectNgIf) {
            this.objectNgIf = objectNgIf;
        }

        /**
         * Getter object href
         * 
         * @return object href
         */
        public String getObjectHref() {
            return objectHref;
        }

        /**
         * Setter object href
         * 
         * @param objectHref objecthref
         */
        public void setObjectHref(String objectHref) {
            this.objectHref = objectHref;
        }

        /**
         * Getter object name attribute
         * 
         * @return object name attribute
         */
        public String getObjectNameAttr() {
            return objectNameAttr;
        }

        /**
         * Setter object name attribute
         * 
         * @param objectNameAttr objectNameAttr
         */
        public void setObjectNameAttr(String objectNameAttr) {
            this.objectNameAttr = objectNameAttr;
        }
    }
}
