package com.larin92.testtasks.internship;

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
import android.widget.Toast;

import com.larin92.testtasks.internship.particlesys.ParticleSystemRenderer;
import com.yalantis.starwars.TilesFrameLayout;
import com.yalantis.starwars.interfaces.TilesFrameLayoutListener;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ItemActivity extends AppCompatActivity {

    final int FRAGMENT_START_DELAY = 0;
    final int FRAGMENT_END_DELAY = 0;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.gl_surface_view)
    GLSurfaceView mGlSurfaceView;
    @Bind(R.id.tiles_frame_layout)
    TilesFrameLayout mTilesFrameLayout;
    MediaPlayer mp;
    CardModel mCardModel = null;

    private static void sendViewToBack(final View child) {
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

        mGlSurfaceView.postDelayed(new Runnable() {
            @Override
            public void run() {
                showFragment();
            }
        }, FRAGMENT_START_DELAY);
    }

    private void setWindowTitle() {
        mCardModel = getIntent().getParcelableExtra(CardModel.Item);
        if (mCardModel.getmTitle() != null)
            setTitle(mCardModel.getmTitle());
    }

    private void showFragment() {
        ItemFragment mItemFragment = ItemFragment.newInstance(mCardModel, this);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.upslide_enter, 0)
                .add(R.id.container, mItemFragment, "greetings")
                .commit();
    }

    //funky stuff
    private void starWars() {
        TilesFrameLayoutListener listener = new TilesFrameLayoutListener() {
            @Override
            public void onAnimationFinished() {
                mTilesFrameLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mp.stop();
                        finish();
                        overridePendingTransition(R.anim.downslide_enter, R.anim.downslide_exit);
                    }
                }, FRAGMENT_END_DELAY);
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
