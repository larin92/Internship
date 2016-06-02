package com.larin92.testtasks.internship.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.larin92.testtasks.internship.R;
import com.larin92.testtasks.internship.contract.FacebookContract;
import com.larin92.testtasks.internship.presenter.FacebookPresenter;

import java.util.Arrays;
import java.util.List;

public class FacebookActivity extends AppCompatActivity implements FacebookContract.View {

    private final String TAG = "FacebookHere";
    private final String urlStart = "https://graph.facebook.com/";
    private final String urlEnd = "/picture?type=large";

    private TextView mName;
    private LoginButton mLoginButton;
    private ImageView mProfileImage;
    private Toolbar mToolbar;

    private CallbackManager mCallbackManager;
    private ProfileTracker mProfileTracker;

    private FacebookPresenter mFacebookPresenter;
    private Profile mProfile = null;
    private AccessToken mAccessToken = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCallbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.facebook_activity);
        mName = (TextView) findViewById(R.id.FBtext);
        mLoginButton = (LoginButton) findViewById(R.id.FBbutton);
        mProfileImage = (ImageView) findViewById(R.id.profileImageView);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mFacebookPresenter = new FacebookPresenter();
        mFacebookPresenter.attachView(this);

        List<String> permissions = Arrays.asList(getResources().getStringArray(R.array.FBpermissions));
        mLoginButton.setReadPermissions(permissions);
        callback();
    }

    private void callback() {
        mLoginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                if (Profile.getCurrentProfile() == null) {
                    mProfileTracker = new ProfileTracker() {
                        @Override
                        protected void onCurrentProfileChanged(Profile profile, Profile profile2) {
                            // profile2 is the new profile
                            mName.setText(profile2.getName());
                            mProfile = profile2;
                            mProfileTracker.stopTracking();
                        }
                    };
                    // no need to call startTracking() on mProfileTracker
                    // because it is called by its constructor, internally.
                } else {
                    Profile profile = Profile.getCurrentProfile();
                    mName.setText(profile.getName());
                    mProfile = profile;
                }

                String userId = loginResult.getAccessToken().getUserId();
                mAccessToken = loginResult.getAccessToken();
                String profileImgUrl = urlStart + userId + urlEnd;
                Glide.with(FacebookActivity.this)
                        .load(profileImgUrl)
                        .into(mProfileImage);
            }

            @Override
            public void onCancel() {
                mName.setText(R.string.fbCancel);
            }

            @Override
            public void onError(FacebookException exception) {
                mName.setText(R.string.fbError);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!(mProfile == null || mAccessToken == null)) {
            mFacebookPresenter.saveData(mProfile, mAccessToken);
            Log.v(TAG, "profile saved");
        } else
            Log.v(TAG, "profile wasn't saved");
        mFacebookPresenter.detachView();
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.upslide_enter, R.anim.upslide_exit);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}
