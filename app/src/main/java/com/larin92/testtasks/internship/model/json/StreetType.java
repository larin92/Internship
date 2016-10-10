package com.larin92.testtasks.internship.model.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StreetType {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("short_name")
    @Expose
    private String shortName;

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

    public StreetType withId(Integer id) {
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

    public StreetType withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @return The shortName
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * @param shortName The short_name
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public StreetType withShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

}
