package com.larin92.testtasks.task1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
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

    @Bind(R.id.tiles_frame_layout) TilesFrameLayout mTilesFrameLayout;
    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.rv) RecyclerView mRecycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //sets up the Back Button
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //populate our recyclerView
        populateRecycler();

        //funky stuff
        starWars();
    }

    //populate our recyclerView
    protected void populateRecycler(){
        List<String> mUrls = new ArrayList<>(Arrays.
                asList(getResources().
                        getStringArray(R.array.urls)));

        mRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        MyRecyclerAdapter adapter = new MyRecyclerAdapter(this, mUrls);
        mRecycler.setAdapter(adapter);
    }

    //funky stuff
    private void starWars()
    {
        TilesFrameLayoutListener listener = new TilesFrameLayoutListener() {
            @Override
            public void onAnimationFinished() {
                finish();
                System.exit(0);
            }
        };
        mTilesFrameLayout.setOnAnimationFinishedListener(listener);
    }

    public void onClickToast(View view) {
        String name = view.getClass().getSimpleName().replace("AppCompat", "");
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
    }

    public void onClickToastR(View view) {
        Toast.makeText(this, "RecyclerView", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //flag to avoid double-activating Star Wars animation
    boolean flag=true;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Toast.makeText(this, "action_settings", Toast.LENGTH_SHORT).show();
            return true;
        }

        if ((id == android.R.id.home) && flag) {
            flag=false;
            mTilesFrameLayout.startAnimation();
        }

        return super.onOptionsItemSelected(item);
    }
}