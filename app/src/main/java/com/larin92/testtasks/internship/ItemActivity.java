package com.larin92.testtasks.internship;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.media.MediaPlayer;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.larin92.testtasks.internship.adapters.ItemImageRecyclerAdapter;
import com.larin92.testtasks.internship.particlesys.ParticleSystemRenderer;
import com.yalantis.starwars.TilesFrameLayout;
import com.yalantis.starwars.interfaces.TilesFrameLayoutListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ItemActivity extends AppCompatActivity {

    @Bind(R.id.tiles_frame_layout)
    TilesFrameLayout mTilesFrameLayout;
    @Bind(R.id.gl_surface_view)
    GLSurfaceView mGlSurfaceView;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.recycler_images)
    RecyclerView mRecycler;
    @Bind(R.id.scrollview)
    ScrollView mScrollView;

    @Bind(R.id.item_category)
    TextView mCategory;
    @Bind(R.id.item_status)
    TextView mStatus;
    @Bind(R.id.item_creation)
    TextView mCreation;
    @Bind(R.id.item_registration)
    TextView mRegistration;
    @Bind(R.id.item_solveTo)
    TextView mSolveTo;
    @Bind(R.id.item_responsible)
    TextView mResponsible;
    @Bind(R.id.item_description)
    TextView mDescription;

    MediaPlayer mp;
    List<String> mUrls = null;

    public static void sendViewToBack(final View child) {
        final ViewGroup parent = (ViewGroup) child.getParent();
        if (null != parent) {
            parent.removeView(child);
            parent.addView(child, 0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_activity);
        ButterKnife.bind(this);
        mScrollView.requestFocus();

        //more funk
        initFunk();
        backGroundStars();

        //sets up the Back Button
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        receiveAndSetData();
        
        //populate our recyclerView
        populateRecycler();

        //funky stuff
        starWars();
    }

    private void receiveAndSetData() {
        CardModel cardModel = getIntent().getParcelableExtra(CardModel.Item);
        if (cardModel != null) {
            if (cardModel.getmUrls() != null)
                mUrls = cardModel.mUrls;
            if (cardModel.getmCategory() != null)
                mCategory.setText(cardModel.getmCategory());
            String status;
            switch (cardModel.getmStatus()) {
                case 0:
                    status = getResources().getString(R.string.status0);
                    break;
                case 1:
                    status = getResources().getString(R.string.status1);
                    break;
                case 2:
                    status = getResources().getString(R.string.status2);
                    break;
                default:
                    status = getResources().getString(R.string.statusWut);
            }
            mStatus.setText(status);
            if (cardModel.getmTitle() != null)
                this.setTitle(cardModel.getmTitle());
            if (cardModel.getmDateCreated() != null)
                mCreation.setText(cardModel.getmDateCreated());
            if (cardModel.getmDateRegistered() != null)
                mRegistration.setText(cardModel.getmDateRegistered());
            if (cardModel.getmDateSolveTo() != null)
                mSolveTo.setText(cardModel.getmDateSolveTo());
            if (cardModel.getmResponsible() != null)
                mResponsible.setText(cardModel.getmResponsible());
            if (cardModel.getmDescription() != null)
                mDescription.setText(cardModel.getmDescription());
        }
    }

    //more funk
    private void initFunk() {
        mp = MediaPlayer.create(getApplicationContext(), R.raw.start);
        mp.start();
        mp.setLooping(true);
    }

    //populate our recyclerView
    protected void populateRecycler() {
        if (mUrls == null) {
            mUrls = new ArrayList<>(Arrays.
                    asList(getResources().
                            getStringArray(R.array.urls)));
        }

        mRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        ItemImageRecyclerAdapter adapter = new ItemImageRecyclerAdapter(this, mUrls);
        mRecycler.setAdapter(adapter);
    }

    //funky stuff
    private void starWars() {
        final int ENDSCREEN_DELAY = 1000;
        TilesFrameLayoutListener listener = new TilesFrameLayoutListener() {
            @Override
            public void onAnimationFinished() {
                mTilesFrameLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mp.stop();
                        finish();
                    }
                }, ENDSCREEN_DELAY);
            }
        };
        mTilesFrameLayout.setOnAnimationFinishedListener(listener);
    }

    public void onClickToast(View view) {
        Toast.makeText(this, view.getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        mTilesFrameLayout.startAnimation();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            //mGlSurfaceView.setVisibility(View.VISIBLE);
            mTilesFrameLayout.startAnimation();
        }

        return super.onOptionsItemSelected(item);
    }

    private void backGroundStars() {
        // Check if the system supports OpenGL ES 2.0.
        final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
        final boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x20000;

        sendViewToBack(mGlSurfaceView);

        if (supportsEs2) {
            // Request an OpenGL ES 2.0 compatible context.
            mGlSurfaceView.setEGLContextClientVersion(2);

            // Set the renderer to our demo renderer, defined below.
            ParticleSystemRenderer mRenderer = new ParticleSystemRenderer(mGlSurfaceView, this);
            mGlSurfaceView.setRenderer(mRenderer);
            mGlSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        } else {
            throw new UnsupportedOperationException();
        }
    }
}
