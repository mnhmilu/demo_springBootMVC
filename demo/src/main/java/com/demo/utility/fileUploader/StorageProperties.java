package com.demo.utility.fileUploader;

import java.io.File;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    //private String location = "src/main/resources/static/images/"+"upload-dir";
   // private String location = "static/images";
	
	String location = new File("static\\images").toPath()+ "\\" + "upload-dir";

    public String getLocation() {
        return location;
    }

    
    public void setLocation(String location) {
        this.location = location;
    }

}
