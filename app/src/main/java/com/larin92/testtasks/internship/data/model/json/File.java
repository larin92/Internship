package com.larin92.testtasks.internship.data.model.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class File {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("filename")
    @Expose
    private String filename;

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public File withId(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    public File withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @return The filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @param filename The filename
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    public File withFilename(String filename) {
        this.filename = filename;
        return this;
    }

}
