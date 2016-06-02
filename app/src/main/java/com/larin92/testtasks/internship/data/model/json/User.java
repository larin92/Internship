package com.larin92.testtasks.internship.data.model.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("middle_name")
    @Expose
    private String middleName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("birthday")
    @Expose
    private Integer birthday;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("fb_registered")
    @Expose
    private Integer fbRegistered;
    @SerializedName("push_token")
    @Expose
    private String pushToken;
    @SerializedName("device_type")
    @Expose
    private Integer deviceType;

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

    public User withId(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * @return The firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName The first_name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public User withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * @return The lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName The last_name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public User withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     * @return The middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * @param middleName The middle_name
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public User withMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    /**
     * @return The email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public User withEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * @return The birthday
     */
    public Integer getBirthday() {
        return birthday;
    }

    /**
     * @param birthday The birthday
     */
    public void setBirthday(Integer birthday) {
        this.birthday = birthday;
    }

    public User withBirthday(Integer birthday) {
        this.birthday = birthday;
        return this;
    }

    /**
     * @return The image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image The image
     */
    public void setImage(String image) {
        this.image = image;
    }

    public User withImage(String image) {
        this.image = image;
        return this;
    }

    /**
     * @return The phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone The phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    /**
     * @return The address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @param address The address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    public User withAddress(Address address) {
        this.address = address;
        return this;
    }

    /**
     * @return The fbRegistered
     */
    public Integer getFbRegistered() {
        return fbRegistered;
    }

    /**
     * @param fbRegistered The fb_registered
     */
    public void setFbRegistered(Integer fbRegistered) {
        this.fbRegistered = fbRegistered;
    }

    public User withFbRegistered(Integer fbRegistered) {
        this.fbRegistered = fbRegistered;
        return this;
    }

    /**
     * @return The pushToken
     */
    public String getPushToken() {
        return pushToken;
    }

    /**
     * @param pushToken The push_token
     */
    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }

    public User withPushToken(String pushToken) {
        this.pushToken = pushToken;
        return this;
    }

    /**
     * @return The deviceType
     */
    public Integer getDeviceType() {
        return deviceType;
    }

    /**
     * @param deviceType The device_type
     */
    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public User withDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
        return this;
    }

}
