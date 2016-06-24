package com.larin92.testtasks.internship.model.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Performer {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("organization")
    @Expose
    private String organization;
    @SerializedName("person")
    @Expose
    private String person;
    @SerializedName("deadline")
    @Expose
    private Integer deadline;

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

    /**
     * @return The organization
     */
    public String getOrganization() {
        return organization;
    }

    /**
     * @param organization The organization
     */
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    /**
     * @return The person
     */
    public String getPerson() {
        return person;
    }

    /**
     * @param person The person
     */
    public void setPerson(String person) {
        this.person = person;
    }

    /**
     * @return The deadline
     */
    public Integer getDeadline() {
        return deadline;
    }

    /**
     * @param deadline The deadline
     */
    public void setDeadline(Integer deadline) {
        this.deadline = deadline;
    }
}
