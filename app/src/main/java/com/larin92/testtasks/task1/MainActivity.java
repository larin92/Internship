package com.larin92.testtasks.task1;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.yalantis.starwars.TilesFrameLayout;
import com.yalantis.starwars.interfaces.TilesFrameLayoutListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @Bind(R.id.tiles_frame_layout)
    TilesFrameLayout mTilesFrameLayout;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.recycler)
    RecyclerView mRecycler;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //more funk
        init_funk();

        //sets up the Back Button
        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //populate our recyclerView
        populateRecycler();

        //funky stuff
        starWars();
    }

    //more funk
    private void init_funk() {
        mp = MediaPlayer.create(getApplicationContext(), R.raw.start);
        mp.start();
        mp.setLooping(true);
    }

    //populate our recyclerView
    protected void populateRecycler() {
        List<String> mUrls = new ArrayList<>(Arrays.
                asList(getResources().
                        getStringArray(R.array.urls)));

        mRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        RecyclerAdapter adapter = new RecyclerAdapter(this, mUrls);
        mRecycler.setAdapter(adapter);
    }

    //funky stuff
    private void starWars() {
        TilesFrameLayoutListener listener = new TilesFrameLayoutListener() {
            @Override
            public void onAnimationFinished() {
                finish();
            }
        };
        mTilesFrameLayout.setOnAnimationFinishedListener(listener);
    }

    public void onClickToast(View view) {
        Toast.makeText(this, view.getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            mTilesFrameLayout.startAnimation();
        }

        return super.onOptionsItemSelected(item);
    }
}
