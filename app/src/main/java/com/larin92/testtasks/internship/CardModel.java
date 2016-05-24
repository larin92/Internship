package com.larin92.testtasks.internship;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by larin92 on 19.04.2016.
 */
public class CardModel implements Parcelable {

    public final static String Item = "Item";
    private int mCategory;
    private String mDescription;
    private String mAddress;
    //  i assume Date type will be needed when we'll work with database, not mocks
    private String mDateCreated; //TODO make Date
    private String mDateRegistered;
    private String mDateResolveTo;
    private int mDaysLeft;
    private int mLikes;
    private int mStatus;
    private String mTitle;
    private List<String> mUrls;
    private String mResponsible;

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

        public Builder setCategory(int category) {
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

        public Builder setLikes(int likes) {
            CardModel.this.mLikes = likes;
            return this;
        }

        public Builder setStatus(int status) {
            CardModel.this.mStatus = status;
            return this;
        }

        public Builder setUrls(List<String> urls) {
            CardModel.this.mUrls = urls;
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

    public CardModel(int category, String description, String address, String dateCreated, String dateRegistered, String dateResolveTo, int likes, int status, List<String> urls, String responsible, int daysLeft, String title) {
        mCategory = category;
        mDescription = description;
        mAddress = address;
        mDateCreated = dateCreated;
        mDateRegistered = dateRegistered;
        mDateResolveTo = dateResolveTo;
        mLikes = likes;
        mStatus = status;
        mUrls = urls;
        mResponsible = responsible;
        mDaysLeft = daysLeft;
        mTitle = title;
    }

    private CardModel(Parcel in) {
        mCategory = in.readInt();
        mDescription = in.readString();
        mAddress = in.readString();
        mDateCreated = in.readString();
        mDateRegistered = in.readString();
        mDateResolveTo = in.readString();
        mLikes = in.readInt();
        mStatus = in.readInt();
        mUrls = in.createStringArrayList();
        mResponsible = in.readString();
        mTitle = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mCategory);
        dest.writeString(mDescription);
        dest.writeString(mAddress);
        dest.writeString(mDateCreated);
        dest.writeString(mDateRegistered);
        dest.writeString(mDateResolveTo);
        dest.writeInt(mLikes);
        dest.writeInt(mStatus);
        dest.writeStringList(mUrls);
        dest.writeString(mResponsible);
        dest.writeString(mTitle);
    }

    public static final Creator<CardModel> CREATOR = new Creator<CardModel>() {
        @Override
        public CardModel createFromParcel(Parcel in) {
            return new CardModel(in);
        }

        @Override
        public CardModel[] newArray(int size) {
            return new CardModel[size];
        }
    };

    //  setters to use in future
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public int getCategory() {
        return mCategory;
    }

    public void setCategory(int mCategory) {
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

    public int getDaysLeft() {
        return mDaysLeft;
    }

    public void setDaysLeft(int mDaysLeft) {
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

    public List<String> getUrls() {
        return mUrls;
    }

    public void setUrls(List<String> mUrls) {
        this.mUrls = mUrls;
    }

    public String getResponsible() {
        return mResponsible;
    }

    public void setResponsible(String mResponsible) {
        this.mResponsible = mResponsible;
    }
}
