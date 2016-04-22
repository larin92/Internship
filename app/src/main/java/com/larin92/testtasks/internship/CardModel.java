package com.larin92.testtasks.internship;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by larin92 on 19.04.2016.
 */
public class CardModel implements Parcelable {
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
    final static String Item = "Item";
    final int ID;
    String mCategory;
    String mDescription;
    String mAddress;
    String mDateCreated; //TODO make Date
    String mDateRegistered;
    String mDateSolveTo;
    int mDaysLeft;
    int mLikes;
    int mStatus;
    String mTitle;
    List<String> mUrls;
    String mResponsible;

    public CardModel(int id, String category, String description, String address, String dateCreated, String dateRegistered, String dateSolveTo, int likes, int status, List<String> urls, String responsible, int daysLeft, String title) {
        ID = id;
        mCategory = category;
        mDescription = description;
        mAddress = address;
        mDateCreated = dateCreated;
        mDateRegistered = dateRegistered;
        mDateSolveTo = dateSolveTo;
        mLikes = likes;
        mStatus = status;
        mUrls = urls;
        mResponsible = responsible;
        mDaysLeft = daysLeft;
        mTitle = title;
    }
    private CardModel(Parcel in) {
        ID = in.readInt();
        mCategory = in.readString();
        mDescription = in.readString();
        mAddress = in.readString();
        mDateCreated = in.readString();
        mDateRegistered = in.readString();
        mDateSolveTo = in.readString();
        mLikes = in.readInt();
        mStatus = in.readInt();
        mUrls = in.createStringArrayList();
        mResponsible = in.readString();
        mTitle = in.readString();
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ID);
        dest.writeString(mCategory);
        dest.writeString(mDescription);
        dest.writeString(mAddress);
        dest.writeString(mDateCreated);
        dest.writeString(mDateRegistered);
        dest.writeString(mDateSolveTo);
        dest.writeInt(mLikes);
        dest.writeInt(mStatus);
        dest.writeStringList(mUrls);
        dest.writeString(mResponsible);
        dest.writeString(mTitle);
    }

    public int getID() {
        return ID;
    }

    public String getmCategory() {
        return mCategory;
    }

    public void setmCategory(String mCategory) {
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

    public String getmDateSolveTo() {
        return mDateSolveTo;
    }

    public void setmDateSolveTo(String mDateSolveTo) {
        this.mDateSolveTo = mDateSolveTo;
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
