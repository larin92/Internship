package com.larin92.testtasks.internship.model.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("district")
    @Expose
    private District district;
    @SerializedName("city")
    @Expose
    private City city;
    @SerializedName("street")
    @Expose
    private Street street;
    @SerializedName("house")
    @Expose
    private House house;
    @SerializedName("flat")
    @Expose
    private String flat;

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

    public Address withId(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * @return The district
     */
    public District getDistrict() {
        return district;
    }

    /**
     * @param district The district
     */
    public void setDistrict(District district) {
        this.district = district;
    }

    public Address withDistrict(District district) {
        this.district = district;
        return this;
    }

    /**
     * @return The city
     */
    public City getCity() {
        return city;
    }

    /**
     * @param city The city
     */
    public void setCity(City city) {
        this.city = city;
    }

    public Address withCity(City city) {
        this.city = city;
        return this;
    }

    /**
     * @return The street
     */
    public Street getStreet() {
        return street;
    }

    /**
     * @param street The street
     */
    public void setStreet(Street street) {
        this.street = street;
    }

    public Address withStreet(Street street) {
        this.street = street;
        return this;
    }

    /**
     * @return The house
     */
    public House getHouse() {
        return house;
    }

    /**
     * @param house The house
     */
    public void setHouse(House house) {
        this.house = house;
    }

    public Address withHouse(House house) {
        this.house = house;
        return this;
    }

    /**
     * @return The flat
     */
    public String getFlat() {
        return flat;
    }

    /**
     * @param flat The flat
     */
    public void setFlat(String flat) {
        this.flat = flat;
    }

    public Address withFlat(String flat) {
        this.flat = flat;
        return this;
    }

}
