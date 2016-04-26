package com.larin92.testtasks.internship;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.larin92.testtasks.internship.adapters.CardsFragmentPagerAdapter;
import com.yalantis.phoenix.PullToRefreshView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NavigationActivity extends AppCompatActivity
        implements CardsFragment.OnListFragmentInteractionListener {
    @Bind(R.id.pull_to_refresh)
    PullToRefreshView mPullToRefreshView;
    @Bind(R.id.viewpager)
    ViewPager mViewPager;
    @Bind(R.id.tabs)
    TabLayout mTabLayout;
    @Bind(R.id.nav_view)
    NavigationView mNavigationView;
    @Bind(R.id.appbar)
    AppBarLayout mAppBar;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            ViewCompat.setElevation(mAppBar, 0f);
            ViewCompat.setElevation(mToolbar, 10f);
        }

        // i was thinking about customising it. it will look great with
        // the app theme tho(communal services)
        pullToRefresh();

        initDrawer();

        App app = new App();
        app.createData(this);

        //TODO add Butterknife
        // Get the ViewPager and set it's PagerAdapter so that it can display items
        mViewPager.setAdapter(new CardsFragmentPagerAdapter(this, getSupportFragmentManager()));

        // Give the TabLayout the ViewPager
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initDrawer() {
        if (mNavigationView != null)
            mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
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
        Intent intent = new Intent(this, ItemActivity.class);
        intent.putExtra(CardModel.Item, item);
        startActivity(intent);
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
