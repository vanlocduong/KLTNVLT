package com.example.vietdang.foodappversion2.view.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.vietdang.foodappversion2.R;
import com.example.vietdang.foodappversion2.helper.BottomNavigationViewHelper;
import com.example.vietdang.foodappversion2.view.account.AccountFragment;
import com.example.vietdang.foodappversion2.view.history.HistoryFragment;
import com.example.vietdang.foodappversion2.view.overview.OverViewFragment;
import com.example.vietdang.foodappversion2.view.recommend.RecommendFragment;
import com.example.vietdang.foodappversion2.view.searchfood.SearchFoodActivity;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener{
    private Toolbar toolBar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private String userId="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //luu id user
        Intent intent = this.getIntent();
        userId =intent.getStringExtra("userId");
        //luu user id vao preferences
        SharedPreferences sharedPreferences= this.getSharedPreferences("USer", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("UserID", Integer.valueOf(userId));
        editor.commit();
        //
        toolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        //drawer layout
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //bottom bar
        final BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        changeFragment(0);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_home:
                                changeFragment(0);
                                break;
                            case R.id.action_recommend:
                                changeFragment(1);
                                break;

                            case R.id.action_history:
                                changeFragment(2);
                                break;

                            case R.id.action_account:
                                changeFragment(3);
                                break;
                        }
                        return true;
                    }
                });

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_about_us) {

        } else if (id == R.id.nav_respone) {

        } else if (id == R.id.nav_configuration) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    private void changeFragment(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = new OverViewFragment();
        } else if (position == 1) {
            fragment = new RecommendFragment();
        } else if (position == 2) {
            fragment = new HistoryFragment();
        } else {
            fragment = new AccountFragment();
        }
        Bundle bundle = new Bundle();
        bundle.putInt("UserID",Integer.valueOf(userId));
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_main_search) {
            startActivity(new Intent(this, SearchFoodActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
