package com.larin92.testtasks.internship;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.larin92.testtasks.internship.adapters.CardsFragmentPagerAdapter;
import com.yalantis.phoenix.PullToRefreshView;

public class NavigationActivity extends AppCompatActivity
        implements CardsFragment.OnListFragmentInteractionListener {
    PullToRefreshView mPullToRefreshView;
    DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        // i was thinking about customising it. it will look great with
        // the app theme tho(communal services)
        pullToRefresh();

        initDrawer();

        App app = new App();
        app.createData(this);

        //TODO add Butterknife
        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new CardsFragmentPagerAdapter(this, getSupportFragmentManager()));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null)
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.nav_all_queries:
                            toastInDev();
                        case R.id.nav_map:
                            toastInDev();
                        case R.id.nav_login:
                            toastInDev();
                        default:
                            mDrawerLayout.closeDrawers();
                            return true;
                    }
                }
            });
    }

    public void toastInDev() {
        Toast.makeText(this, getResources().getString(R.string.inDevelopment), Toast.LENGTH_SHORT).show();
    }

    private void pullToRefresh() {
        final int REFRESH_DELAY = 2000;
        mPullToRefreshView = (PullToRefreshView) findViewById(R.id.pull_to_refresh);
        final MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.bird);
        mp.setLooping(true);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mp.start();
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mp.pause();
                        mPullToRefreshView.setRefreshing(false);
                    }
                }, REFRESH_DELAY);
            }
        });
    }

    @Override
    public void onListFragmentInteraction(final CardModel item) {
        Log.v("HEY", "clicked item's id is " + item.getID());
        Intent intent = new Intent(this, ItemActivity.class);
        intent.putExtra(CardModel.Item, item);
        startActivity(intent);
        //TODO make sharedTransition
        overridePendingTransition(R.anim.upslide_enter, R.anim.upslide_exit);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            toastInDev();
            return true;
        }

        if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
