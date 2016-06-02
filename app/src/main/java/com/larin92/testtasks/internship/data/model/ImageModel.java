package com.larin92.testtasks.internship.data.model;

import io.realm.RealmObject;

public class ImageModel extends RealmObject {

    String FILE_BASE_URL = "http://dev-contact.yalantis.com/files/ticket/";

    public ImageModel(String url) {
        mUrl = FILE_BASE_URL + url;
    }

    public ImageModel() {
    }

    public String getUrl() {
        return mUrl;
    }

    private String mUrl;
}
