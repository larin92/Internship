package com.larin92.testtasks.internship;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.larin92.testtasks.internship.adapters.CardsFragmentPagerAdapter;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
                   CardsFragment.OnListFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            //supportActionBar.setHomeAsUpIndicator(R.drawable.ic_drawer_menu);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        //TODO:
        //

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        //.setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //toggle.setHomeAsUpIndicator(R.drawable.ic_drawer_menu);
        drawer.setDrawerListener(toggle);
        //toggle.setHomeAsUpIndicator(R.drawable.ic_drawer_menu);
        //TODO change this BS toggle
        toggle.syncState();
        //toggle.setHomeAsUpIndicator(R.drawable.ic_drawer_menu);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        App app = new App();
        app.createData(this);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new CardsFragmentPagerAdapter(this, getSupportFragmentManager()));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onListFragmentInteraction(CardModel item) {
        Log.v("HEY", "clicked item's id is " + item.getID());
        Intent intent = new Intent(this, ItemActivity.class);
        intent.putExtra(CardModel.Item, item);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Log.v("!!!!!!!", "onNavigationItemSelected: camera");
            //item.setChecked(false);
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            //item.setChecked(false);
            Log.v("!!!!!!!", "onNavigationItemSelected: gallery");
        } else if (id == R.id.nav_slideshow) {
            Log.v("!!!!!!!", "onNavigationItemSelected: slideshow");
        } else if (id == R.id.nav_manage) {
            Log.v("!!!!!!!", "onNavigationItemSelected: manage");
        } else if (id == R.id.nav_share) {
            Log.v("!!!!!!!", "onNavigationItemSelected: share");
        } else if (id == R.id.nav_send) {
            Log.v("!!!!!!!", "onNavigationItemSelected: send");
        }
        //item.setChecked(false);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //drawer.closeDrawers();
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
