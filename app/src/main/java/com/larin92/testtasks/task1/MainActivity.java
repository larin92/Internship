package com.larin92.testtasks.task1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
        //Studio says it may produce NPE
        try{ getSupportActionBar().setDisplayHomeAsUpEnabled(true); }
        catch(NullPointerException npe){ Log.e("NPE", "error:" + npe); }

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
        RecyclerAdapter adapter = new RecyclerAdapter(this, mUrls);
        mRecycler.setAdapter(adapter);
    }

    //funky stuff
    private void starWars()
    {
        TilesFrameLayoutListener listener = new TilesFrameLayoutListener() {
            @Override
            public void onAnimationFinished() {
                finish();
            }
        };
        mTilesFrameLayout.setOnAnimationFinishedListener(listener);
    }

    public void onClickToast(View view) {
        //I know, it's hardcode, but it's only "cosmetic" stuff,
        //haven't found a better way to do it
        //excluding writing "bicycles" and making it even more weird
        String name = view.getClass().getSimpleName().replace("AppCompat", "");
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /** flag to avoid double-activating Star Wars animation.
     *  Maybe I should suggest implementing this flag into the lib,
     *  there are few ways it could be useful and adds extra security,
     *  but maybe it's just a weird idea and can be implemented by anyone who needs it.
    */
    boolean flag=true;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        String settings_toast = item.getTitle()+" ("+item.getClass().getSimpleName()+")";

        if (id == R.id.action_settings) {
            Toast.makeText(this, settings_toast, Toast.LENGTH_SHORT).show();
            return true;
        }

        if ((id == android.R.id.home) && flag) {
            flag=false;
            mTilesFrameLayout.startAnimation();
        }

        return super.onOptionsItemSelected(item);
    }
}