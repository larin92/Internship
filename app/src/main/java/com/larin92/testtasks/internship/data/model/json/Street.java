package com.larin92.testtasks.internship.data.model.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Street {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("ru_name")
    @Expose
    private String ruName;
    @SerializedName("street_type")
    @Expose
    private StreetType streetType;

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

    public Street withId(Integer id) {
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

    public Street withName(String name) {
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

    public Street withRuName(String ruName) {
        this.ruName = ruName;
        return this;
    }

    /**
     * @return The streetType
     */
    public StreetType getStreetType() {
        return streetType;
    }

    /**
     * @param streetType The street_type
     */
    public void setStreetType(StreetType streetType) {
        this.streetType = streetType;
    }

    public Street withStreetType(StreetType streetType) {
        this.streetType = streetType;
        return this;
    }

}
