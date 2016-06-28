package com.larin92.testtasks.internship.ui.activities;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.media.MediaPlayer;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.larin92.testtasks.internship.R;
import com.larin92.testtasks.internship.model.CardModel;
import com.larin92.testtasks.internship.ui.fragments.ItemFragment;
import com.larin92.testtasks.internship.ui.stars.particlesys.ParticleSystemRenderer;
import com.yalantis.starwars.TilesFrameLayout;
import com.yalantis.starwars.interfaces.TilesFrameLayoutListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ItemActivity extends AppCompatActivity {

    static final int FRAGMENT_DELAY = 500;

    private Unbinder mUnbinder;
    private MediaPlayer mp;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.gl_surface_view)
    GLSurfaceView mGlSurfaceView;
    @BindView(R.id.tiles_frame_layout)
    TilesFrameLayout mTilesFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_activity);
        mUnbinder = ButterKnife.bind(this);
        //sets up the Back Button
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        setWindowTitle();

        //funky stuff
        initFunk();
        starWars();

        backGroundStars();

        if (savedInstanceState == null) {
            showFragment();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    private void setWindowTitle() {
        String title = getIntent().getStringExtra(CardModel.TITLE);
        if (title != null) {
            setTitle(title);
        }
    }

    private void showFragment() {
        final ItemFragment mItemFragment = ItemFragment.newInstance(this);
        mGlSurfaceView.postDelayed(new Runnable() {     //  makes animation smooth
            @Override
            public void run() {
                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.upslide_enter, 0)
                        .add(R.id.container, mItemFragment)
                        .commit();
            }
        }, FRAGMENT_DELAY);
    }

    //funky stuff
    private void starWars() {
        TilesFrameLayoutListener listener = new TilesFrameLayoutListener() {
            @Override
            public void onAnimationFinished() {
                mp.stop();
                mp.release();
                finish();
                overridePendingTransition(R.anim.downslide_enter, R.anim.downslide_exit);
            }
        };
        mTilesFrameLayout.setOnAnimationFinishedListener(listener);
    }

    //more funk
    private void initFunk() {
        mp = MediaPlayer.create(getApplicationContext(), R.raw.start);
        mp.start();
        mp.setLooping(true);
    }

    @Override
    public void onBackPressed() {
        mTilesFrameLayout.startAnimation();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
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

    private static void sendViewToBack(final View child) {
        final ViewGroup parent = (ViewGroup) child.getParent();
        if (null != parent) {
            parent.removeView(child);
            parent.addView(child, 0);
        }
    }
}