package com.larin92.testtasks.internship.model.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class District {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("ru_name")
    @Expose
    private String ruName;

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

    public District withId(Integer id) {
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

    public District withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @return The ruName
     */
    public String getRuName() {
        return ruName;
    }

    /**
     * @param ruName The ru_name
     */
    public void setRuName(String ruName) {
        this.ruName = ruName;
    }

    public District withRuName(String ruName) {
        this.ruName = ruName;
        return this;
    }

}
