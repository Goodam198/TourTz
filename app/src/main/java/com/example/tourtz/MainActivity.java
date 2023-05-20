package com.example.tourtz;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    ViewPager pager;
    TabLayout tabLayout;
    TabItem home,favourites,profile,login,about,explore,copyright,settings;
    PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tablayout);
        home = findViewById(R.id.home);
        favourites = findViewById(R.id.favourites);
        profile = findViewById(R.id.profile);
        login = findViewById(R.id.login);
        about = findViewById(R.id.about);
        explore = findViewById(R.id.explore);
        copyright = findViewById(R.id.copy);
        settings = findViewById(R.id.settings);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        adapter = new PagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,tabLayout.getTabCount());
        pager.setAdapter(adapter);

//
//       final View touchView = findViewById(R.id.viewpager);
//
//        touchView.setOnTouchListener(new View.OnTouchListener()
//        {
//            @Override
//            public boolean onTouch(View v, MotionEvent event)
//            {
//                return true;
//            }
//        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.search){
            Toast.makeText(getApplicationContext(), "searching", Toast.LENGTH_SHORT).show();
        }else if (id == R.id.invite){
            Intent intent = new Intent(getApplicationContext(),InviteActivity.class);
            startActivity(intent);
        }else if (id == R.id.out){
            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.explore){
            Intent intent = new Intent(getApplicationContext(),ExploreActivity.class);
            startActivity(intent);
        }

        else if (id == R.id.settings){
            Intent intent = new Intent(getApplicationContext(),SettingsActivity.class);
            startActivity(intent);
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        if (item.getItemId() == R.id.home){
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }

        else if (item.getItemId() == R.id.favourites){
            Intent intent = new Intent(getApplicationContext(),FavouritesActivity.class);
            startActivity(intent);
        }

        else if (item.getItemId() == R.id.profile){
            Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
            startActivity(intent);
        }


        else if (item.getItemId() == R.id.about){
            Intent intent = new Intent(getApplicationContext(),AboutActivity.class);
            startActivity(intent);
        }

        else if (item.getItemId() == R.id.explore){
            Intent intent = new Intent(getApplicationContext(),ExploreActivity.class);
            startActivity(intent);
        }

        else if (item.getItemId() == R.id.copy){
            Intent intent = new Intent(getApplicationContext(),CopyrightActivity.class);
            startActivity(intent);
        }

        else if (item.getItemId() == R.id.settings){
            Intent intent = new Intent(getApplicationContext(),SettingsActivity.class);
            startActivity(intent);
        }

        return false;
    }

//    public class CustomViewPager extends ViewPager {
//        private Boolean disable = false;
//        public CustomViewPager(Context context) {
//            super(context);
//        }
//        public CustomViewPager(Context context, AttributeSet attrs){
//            super(context,attrs);
//        }
//        @Override
//        public boolean onInterceptTouchEvent(MotionEvent event) {
//            return !disable && super.onInterceptTouchEvent(event);
//        }
//
//        @Override
//        public boolean onTouchEvent(MotionEvent event) {
//            return !disable && super.onTouchEvent(event);
//        }
//
//        public void disableScroll(Boolean disable){
//            //When disable = true not work the scroll and when disble = false work the scroll
//            this.disable = disable;
//        }
//    }

    public static class LoginActivity extends AppCompatActivity {


        // creation of variables which holds the texts and buttons in login page
        EditText emailLog, passwordLog;
        Button login,register_now;

        // creation of a variable to hold our database
        DatabaseHelper DB;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);


            // the inserted data to be fetched from the database on login
            emailLog = findViewById(R.id.emailLog);
            passwordLog = findViewById(R.id.passwordLog);
            login = findViewById(R.id.login);
            register_now = findViewById(R.id.register_now);
            DB = new DatabaseHelper(this);


            // code for the login button to fetch and validate  the data on clicking it
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String email = emailLog.getText().toString();
                    String password = passwordLog.getText().toString();

                    if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
                        Toast.makeText(LoginActivity.this, "Fill all the fields to proceed", Toast.LENGTH_SHORT).show();
                    else{
                        Boolean checkuserpass = DB.checkEmailPassword(email,password);
                        if(checkuserpass == true){
                            Toast.makeText(LoginActivity.this, "login successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                        }
                    }


                }
            });

            //go to registration if new tourist
            register_now.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(getApplicationContext(),RegistrationActivity.class);
                    startActivity(intent);

                }
            });
        }
    }
}