package com.larin92.testtasks.internship.model;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CardModel extends RealmObject {
    public static final String STATE = "mStatus";
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static String QUERY_INWORK;
    public static String QUERY_DONE;
    public static String QUERY_WAITING;
    public static final String QUERY_ALL = QUERY_INWORK + "," + QUERY_DONE + "," + QUERY_WAITING;
    public static ArrayList<Integer> STATE_INWORK = new ArrayList<>();
    public static ArrayList<Integer> STATE_DONE = new ArrayList<>();
    public static ArrayList<Integer> STATE_WAITING = new ArrayList<>();

    @PrimaryKey
    private int id;
    private String mCategory;
    private String mDescription;
    private String mAddress;
    private String mDateCreated;
    private String mDateRegistered;
    private String mDateResolveTo;
    private String mDaysLeft;
    private int mLikes;
    private int mStatus;
    private String mTitle;
    private RealmList<ImageModel> mImages;
    private String mResponsible;

    public static void initQueries(ArrayList<Integer> INWORK, ArrayList<Integer> DONE, ArrayList<Integer> WAITING) {
        STATE_INWORK.addAll(INWORK);
        STATE_DONE.addAll(DONE);
        STATE_WAITING.addAll(WAITING);
        QUERY_INWORK = ArrayToString(INWORK);
        QUERY_DONE = ArrayToString(DONE);
        QUERY_WAITING = ArrayToString(WAITING);
    }

    private static String ArrayToString(ArrayList<Integer> list) {
        StringBuilder query = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            query.append(list.get(i));
            query.append(",");
        }
        return query.toString();
    }


    public CardModel() {
    }

    public static Builder newBuilder() {
        return new CardModel().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public CardModel build() {
            return CardModel.this;
        }

        public Builder setID(int ID) {
            CardModel.this.id = ID;
            return this;
        }

        public Builder setCategory(String category) {
            CardModel.this.mCategory = category;
            return this;
        }

        public Builder setDescription(String description) {
            CardModel.this.mDescription = description;
            return this;
        }

        public Builder setAddress(String address) {
            CardModel.this.mAddress = address;
            return this;
        }

        public Builder setDateCreated(String dateCreated) {
            CardModel.this.mDateCreated = dateCreated;
            return this;
        }

        public Builder setDateRegistered(String dateRegistered) {
            CardModel.this.mDateRegistered = dateRegistered;
            return this;
        }

        public Builder setDateResolveTo(String dateResolveTo) {
            CardModel.this.mDateResolveTo = dateResolveTo;
            return this;
        }

        public Builder setDaysLeft(String daysLeft) {
            CardModel.this.mDaysLeft = daysLeft;
            return this;
        }

        public Builder setLikes(int likes) {
            CardModel.this.mLikes = likes;
            return this;
        }

        public Builder setStatus(int status) {
            CardModel.this.mStatus = status;
            return this;
        }

        public Builder setImages(RealmList<ImageModel> images) {
            CardModel.this.mImages = images;
            return this;
        }

        public Builder setResponsible(String responsible) {
            CardModel.this.mResponsible = responsible;
            return this;
        }

        public Builder setTitle(String title) {
            CardModel.this.mTitle = title;
            return this;
        }
    }

    public CardModel(int id, String category, String description, String address, String dateCreated, String dateRegistered, String dateResolveTo, int likes, int status, RealmList<ImageModel> images, String responsible, String daysLeft, String title) {
        this.id = id;
        mCategory = category;
        mDescription = description;
        mAddress = address;
        mDateCreated = dateCreated;
        mDateRegistered = dateRegistered;
        mDateResolveTo = dateResolveTo;
        mLikes = likes;
        mStatus = status;
        mImages = images;
        mResponsible = responsible;
        mDaysLeft = daysLeft;
        mTitle = title;
    }

    //  setters to use in future
    public int getID() {
        return id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String mCategory) {
        this.mCategory = mCategory;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getDateCreated() {
        return mDateCreated;
    }

    public void setDateCreated(String mDateCreated) {
        this.mDateCreated = mDateCreated;
    }

    public String getDateRegistered() {
        return mDateRegistered;
    }

    public void setDateRegistered(String mDateRegistered) {
        this.mDateRegistered = mDateRegistered;
    }

    public String getDateResolveTo() {
        return mDateResolveTo;
    }

    public void setDateResolveTo(String mDateResolveTo) {
        this.mDateResolveTo = mDateResolveTo;
    }

    public String getDaysLeft() {
        return mDaysLeft;
    }

    public void setDaysLeft(String mDaysLeft) {
        this.mDaysLeft = mDaysLeft;
    }

    public int getLikes() {
        return mLikes;
    }

    public void setLikes(int mLikes) {
        this.mLikes = mLikes;
    }

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int mStatus) {
        this.mStatus = mStatus;
    }

    public RealmList<ImageModel> getImages() {
        return mImages;
    }

    public void setImages(RealmList<ImageModel> mImages) {
        this.mImages = mImages;
    }

    public String getResponsible() {
        return mResponsible;
    }

    public void setResponsible(String mResponsible) {
        this.mResponsible = mResponsible;
    }
}
