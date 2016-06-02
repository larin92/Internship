package com.larin92.testtasks.internship.data.model.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.larin92.testtasks.internship.App;
import com.larin92.testtasks.internship.R;
import com.larin92.testtasks.internship.data.model.ImageModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.realm.RealmList;

public class Model {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("geo_address")
    @Expose
    private GeoAddress geoAddress;
    @SerializedName("category")
    @Expose
    private Category category;
    @SerializedName("type")
    @Expose
    private Type type;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("created_date")
    @Expose
    private Integer createdDate;
    @SerializedName("start_date")
    @Expose
    private Integer startDate;
    @SerializedName("state")
    @Expose
    private State state;
    @SerializedName("ticket_id")
    @Expose
    private String ticketId;
    @SerializedName("files")
    @Expose
    private List<File> files = new ArrayList<File>();
    @SerializedName("performers")
    @Expose
    private List<Performer> performers = new ArrayList<Performer>();
    @SerializedName("deadline")
    @Expose
    private Integer deadline;
    @SerializedName("likes_counter")
    @Expose
    private Integer likesCounter;

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

    public Model withId(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * @return The user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user The user
     */
    public void setUser(User user) {
        this.user = user;
    }

    public Model withUser(User user) {
        this.user = user;
        return this;
    }

    /**
     * @return The geoAddress
     */
    public GeoAddress getGeoAddress() {
        return geoAddress;
    }

    public String getAddress() {
        String address = "";
        if (geoAddress != null)
            address = geoAddress.getAddress();
        return address;
    }

    /**
     * @param geoAddress The geo_address
     */
    public void setGeoAddress(GeoAddress geoAddress) {
        this.geoAddress = geoAddress;
    }

    public Model withGeoAddress(GeoAddress geoAddress) {
        this.geoAddress = geoAddress;
        return this;
    }

    /**
     * @return The category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @param category The category
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    public Model withCategory(Category category) {
        this.category = category;
        return this;
    }

    /**
     * @return The type
     */
    public Type getType() {
        return type;
    }

    /**
     * @param type The type
     */
    public void setType(Type type) {
        this.type = type;
    }

    public Model withType(Type type) {
        this.type = type;
        return this;
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public Model withTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * @return The body
     */
    public String getBody() {
        return body;
    }

    /**
     * @param body The body
     */
    public void setBody(String body) {
        this.body = body;
    }

    public Model withBody(String body) {
        this.body = body;
        return this;
    }

    /**
     * @return The createdDate
     */
    public String getCreatedDate() {
        return format(createdDate);
    }

    /**
     * @param createdDate The created_date
     */
    public void setCreatedDate(Integer createdDate) {
        this.createdDate = createdDate;
    }

    public Model withCreatedDate(Integer createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    /**
     * @return The startDate
     */
    public String getStartDate() {
        return format(startDate);
    }

    /**
     * @param startDate The start_date
     */
    public void setStartDate(Integer startDate) {
        this.startDate = startDate;
    }

    public Model withStartDate(Integer startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     * @return The state
     */
    public State getState() {
        return state;
    }

    /**
     * @param state The state
     */
    public void setState(State state) {
        this.state = state;
    }

    public Model withState(State state) {
        this.state = state;
        return this;
    }

    /**
     * @return The ticketId
     */
    public String getTicketId() {
        return ticketId;
    }

    /**
     * @param ticketId The ticket_id
     */
    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public Model withTicketId(String ticketId) {
        this.ticketId = ticketId;
        return this;
    }

    /**
     * @return The files
     */
    public List<File> getFiles() {
        return files;
    }

    public RealmList<ImageModel> getFilesList() {
        RealmList<ImageModel> images = new RealmList<>();
        if (files != null)
            for (int j = 0; j < files.size(); j++) {
                images.add(new ImageModel(files.get(j).getFilename()));
            }
        return images;
    }

    /**
     * @param files The files
     */
    public void setFiles(List<File> files) {
        this.files = files;
    }

    public Model withFiles(List<File> files) {
        this.files = files;
        return this;
    }

    /**
     * @return The performers
     */
    public String getPerformers() {
        String performersString = "";
        if (performers != null)
            for (int j = 0; j < performers.size(); j++) {
                if (j > 0)
                    performersString += ", ";
                performersString += performers.get(j).getOrganization();
            }
        return performersString;
    }

    /**
     * @param performers The performers
     */
    public void setPerformers(List<Performer> performers) {
        this.performers = performers;
    }

    public Model withPerformers(List<Performer> performers) {
        this.performers = performers;
        return this;
    }

    /**
     * @return The deadline
     */
    public String getDeadline() {
        return format(deadline);
    }

    public String getDaysLeft() {
        String daysLeft = "";
        if (deadline != null && startDate != null) {
            int days = (deadline - startDate) / (24 * 60 * 60 * 1000);
            daysLeft = String.valueOf(days);
        }
        return daysLeft;
    }

    private String format(Integer dateInt) {
        if (dateInt == null)
            return "";
        DateFormat formatter = new SimpleDateFormat(App.getContext()
                .getString(R.string.date_pattern),
                Locale.getDefault());
        Date date = new Date(dateInt);

        return formatter.format(date);
    }

    /**
     * @param deadline The deadline
     */
    public void setDeadline(Integer deadline) {
        this.deadline = deadline;
    }

    public Model withDeadline(Integer deadline) {
        this.deadline = deadline;
        return this;
    }

    /**
     * @return The likesCounter
     */
    public Integer getLikesCounter() {
        return likesCounter;
    }

    /**
     * @param likesCounter The likes_counter
     */
    public void setLikesCounter(Integer likesCounter) {
        this.likesCounter = likesCounter;
    }

    public Model withLikesCounter(Integer likesCounter) {
        this.likesCounter = likesCounter;
        return this;
    }

}
