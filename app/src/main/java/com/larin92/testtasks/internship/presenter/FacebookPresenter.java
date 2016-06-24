package com.larin92.testtasks.internship.presenter;

import com.facebook.AccessToken;
import com.facebook.Profile;
import com.larin92.testtasks.internship.App;
import com.larin92.testtasks.internship.contract.FacebookContract;
import com.larin92.testtasks.internship.model.ProfileFB;

public class FacebookPresenter implements FacebookContract.Presenter {

    private FacebookContract.View mView;

    @Override
    public void attachView(FacebookContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void saveData(Profile profile, AccessToken accessToken) {
        ProfileFB profileFB = ProfileFB.newBuilder()
                .setProfileID(profile.getId())
                .setProfileLink(profile.getLinkUri())
                .setProfileName(profile.getName())
                .setProfilePicture(profile.getProfilePictureUri(ProfileFB.width, ProfileFB.height))
                .setAppID(accessToken.getApplicationId())
                .setToken(accessToken.getToken())
                .setLastRefresh(accessToken.getLastRefresh())
                .setTokenExpires(accessToken.getExpires())
                .build();

        App.getDatabaseManager().add(profileFB);
    }
}
