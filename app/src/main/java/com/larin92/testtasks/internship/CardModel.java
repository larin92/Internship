package com.larin92.testtasks.internship;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by larin92 on 19.04.2016.
 */
public class CardModel implements Parcelable {

    final static String Item = "Item";
    int mCategory;
    String mDescription;
    String mAddress;
    //  i assume Date type will be needed when we'll work with database, not mocks
    String mDateCreated; //TODO make Date
    String mDateRegistered;
    String mDateResolveTo;
    int mDaysLeft;
    int mLikes;
    int mStatus;
    String mTitle;
    List<String> mUrls;
    String mResponsible;

    public CardModel() {

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

    //  cool readable initialisers
    CardModel setCategory(int category) {
        mCategory = category;
        return this;
    }

    CardModel setDescription(String description) {
        mDescription = description;
        return this;
    }

    CardModel setAddress(String address) {
        mAddress = address;
        return this;
    }

    CardModel setDateCreated(String dateCreated) {
        mDateCreated = dateCreated;
        return this;
    }

    CardModel setDateRegistered(String dateRegistered) {
        mDateRegistered = dateRegistered;
        return this;
    }

    CardModel setDateResolveTo(String dateResolveTo) {
        mDateResolveTo = dateResolveTo;
        return this;
    }

    CardModel setLikes(int likes) {
        mLikes = likes;
        return this;
    }

    CardModel setStatus(int status) {
        mStatus = status;
        return this;
    }

    CardModel setUrls(List<String> urls) {
        mUrls = urls;
        return this;
    }

    CardModel setResponsible(String responsible) {
        mResponsible = responsible;
        return this;
    }

    CardModel setTitle(String title) {
        mTitle = title;
        return this;
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
    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public int getmCategory() {
        return mCategory;
    }

    public void setmCategory(int mCategory) {
        this.mCategory = mCategory;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getmDateCreated() {
        return mDateCreated;
    }

    public void setmDateCreated(String mDateCreated) {
        this.mDateCreated = mDateCreated;
    }

    public String getmDateRegistered() {
        return mDateRegistered;
    }

    public void setmDateRegistered(String mDateRegistered) {
        this.mDateRegistered = mDateRegistered;
    }

    public String getmDateResolveTo() {
        return mDateResolveTo;
    }

    public void setmDateResolveTo(String mDateResolveTo) {
        this.mDateResolveTo = mDateResolveTo;
    }

    public int getmDaysLeft() {
        return mDaysLeft;
    }

    public void setmDaysLeft(int mDaysLeft) {
        this.mDaysLeft = mDaysLeft;
    }

    public int getmLikes() {
        return mLikes;
    }

    public void setmLikes(int mLikes) {
        this.mLikes = mLikes;
    }

    public int getmStatus() {
        return mStatus;
    }

    public void setmStatus(int mStatus) {
        this.mStatus = mStatus;
    }

    public List<String> getmUrls() {
        return mUrls;
    }

    public void setmUrls(List<String> mUrls) {
        this.mUrls = mUrls;
    }

    public String getmResponsible() {
        return mResponsible;
    }

    public void setmResponsible(String mResponsible) {
        this.mResponsible = mResponsible;
    }
}
