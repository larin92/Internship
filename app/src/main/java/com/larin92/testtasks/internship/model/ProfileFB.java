package com.larin92.testtasks.internship.model;

import android.net.Uri;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ProfileFB extends RealmObject {

    @PrimaryKey
    private String mID;
    private int mProfile;
    private String mAccessToken;
    private String mName;
    private String mLink;
    private String mPicture;
    private String mToken;
    private String mAppID;
    private Date mTokenExpires;
    private Date mLastRefresh;
    public static final int width = 140;
    public static final int height = 140;

    public ProfileFB() {
    }

    public static Builder newBuilder() {
        return new ProfileFB().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public ProfileFB build() {
            return ProfileFB.this;
        }

        public Builder setProfileID(String ID) {
            ProfileFB.this.mID = ID;
            return this;
        }

        public Builder setProfileName(String name) {
            ProfileFB.this.mName = name;
            return this;
        }

        public Builder setProfileLink(Uri link) {
            ProfileFB.this.mLink = link.toString();
            return this;
        }

        public Builder setProfilePicture(Uri picture) {
            ProfileFB.this.mPicture = picture.toString();
            return this;
        }

        public Builder setToken(String token) {
            ProfileFB.this.mToken = token;
            return this;
        }

        public Builder setAppID(String appID) {
            ProfileFB.this.mAppID = appID;
            return this;
        }

        public Builder setTokenExpires(Date expires) {
            ProfileFB.this.mTokenExpires = expires;
            return this;
        }

        public Builder setLastRefresh(Date lastRefresh) {
            ProfileFB.this.mLastRefresh = lastRefresh;
            return this;
        }
    }

    public String getID() {
        return mID;
    }

    public void setID(String mID) {
        this.mID = mID;
    }

    public int getProfile() {
        return mProfile;
    }

    public void setProfile(int mProfile) {
        this.mProfile = mProfile;
    }

    public String getAccessToken() {
        return mAccessToken;
    }

    public void setAccessToken(String mAccessToken) {
        this.mAccessToken = mAccessToken;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getLink() {
        return mLink;
    }

    public void setLink(String mLink) {
        this.mLink = mLink;
    }

    public String getPicture() {
        return mPicture;
    }

    public void setPicture(String mPicture) {
        this.mPicture = mPicture;
    }

    public String getToken() {
        return mToken;
    }

    public void setToken(String mToken) {
        this.mToken = mToken;
    }

    public String getAppID() {
        return mAppID;
    }

    public void setAppID(String mAppID) {
        this.mAppID = mAppID;
    }

    public Date getTokenExpires() {
        return mTokenExpires;
    }

    public void setTokenExpires(Date mTokenExpires) {
        this.mTokenExpires = mTokenExpires;
    }

    public Date getLastRefresh() {
        return mLastRefresh;
    }

    public void setLastRefresh(Date mLastRefresh) {
        this.mLastRefresh = mLastRefresh;
    }
}
