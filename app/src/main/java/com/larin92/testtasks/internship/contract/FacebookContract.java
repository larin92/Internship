package com.larin92.testtasks.internship.contract;

import com.facebook.AccessToken;
import com.facebook.Profile;

public class FacebookContract {

    public interface Presenter extends BasePresenter<View> {

        void saveData(Profile profile, AccessToken accessToken);
    }

    public interface View extends BaseView {

    }
}
