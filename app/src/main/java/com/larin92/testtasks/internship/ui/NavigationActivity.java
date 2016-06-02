package com.larin92.testtasks.internship.ui;

import android.content.Intent;
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

import com.larin92.testtasks.internship.App;
import com.larin92.testtasks.internship.R;
import com.larin92.testtasks.internship.data.Database;
import com.larin92.testtasks.internship.data.model.CardModel;
import com.larin92.testtasks.internship.ui.adapters.CardsFragmentPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class NavigationActivity extends AppCompatActivity
        implements NavigationFragment.OnListFragmentInteractionListener {

    private Unbinder mUnbinder;

    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    @BindView(R.id.appbar)
    AppBarLayout mAppBar;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);
        mUnbinder = ButterKnife.bind(this);
        initToolbar();

        initDrawer();

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        mViewPager.setAdapter(new CardsFragmentPagerAdapter(this, getSupportFragmentManager()));

        // Give the TabLayout the ViewPager
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    public void toastInDev() {
        Toast.makeText(this, getResources().getString(R.string.inDevelopment), Toast.LENGTH_SHORT).show();
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            ViewCompat.setElevation(mAppBar, 0f);
            ViewCompat.setElevation(mToolbar, 10f);
        }
    }

    private void initDrawer() {
        if (mNavigationView != null)
            mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.nav_all_queries:
                            toastInDev();
                            mDrawerLayout.closeDrawers();
                            return false;
                        case R.id.nav_map:
                            toastInDev();
                            mDrawerLayout.closeDrawers();
                            return false;
                        case R.id.nav_login:
                            Intent intent = new Intent(App.getContext(), FacebookActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.downslide_enter, R.anim.downslide_exit);

                            mDrawerLayout.closeDrawers();
                            return false;
                        default:
                            mDrawerLayout.closeDrawers();
                            return false;
                    }
                }
            });
    }

    @Override
    public void onListFragmentInteraction(final CardModel item) {
        Intent intent = new Intent(this, ItemActivity.class);
        intent.putExtra(CardModel.ID, item.getID());
        if (item.getTitle() != null)
            intent.putExtra(CardModel.TITLE, item.getTitle());
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
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Database.please().deleteAll();
            Toast.makeText(this, getResources().getString(R.string.wipe), Toast.LENGTH_SHORT).show();
            return true;
        }

        if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
