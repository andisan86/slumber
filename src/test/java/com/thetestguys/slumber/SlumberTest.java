/*
 * Copyright (c) 2017. The Test Guys
 */

package com.thetestguys.slumber;

public class SlumberTest {
        public static void main(String args[]) {
                WebObjects webObjects = new WebObjects();
                System.out.println(webObjects.getWebObjectByName("Google Home Logo").getObjectId());
        }
}
