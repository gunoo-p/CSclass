package com.example.study_test;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.naver.maps.map.NaverMapSdk;
import android.view.Window;
import android.view.WindowManager;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationview;

    Fragment homeFragment;
    Fragment searchFragment;
    Fragment locationFragment;
    Fragment bookmarkFragment;
    Fragment mypageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        bottomNavigationview = findViewById(R.id.bottomNavigationView);

        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(this, android.R.color.white));
        }
        // 네이버맵
        NaverMapSdk.getInstance(this).setClient(
                new NaverMapSdk.NaverCloudPlatformClient("1jsmwgic4r"));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        homeFragment = new HomeFragment();
        searchFragment = new SearchFragment();
        locationFragment = new LocationFragment();
        bookmarkFragment = new BookmarkFragment();
        mypageFragment = new MypageFragment();

        // 초기 화면을 homeFragment로 설정
        if (savedInstanceState == null) {
            loadFragment(homeFragment);
        }

        bottomNavigationview.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            Fragment fragment = homeFragment;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();

                if(itemId == R.id.navigation_home){
                    fragment = homeFragment;

                }else if(itemId == R.id.navigation_search){
                    fragment = searchFragment;

                }else if(itemId == R.id.navigation_location){
                    fragment = locationFragment;

                }else if(itemId == R.id.navigation_bookmark){
                    fragment = bookmarkFragment;

                }else if(itemId == R.id.navigation_mypage) {
                    fragment = mypageFragment;

                }

                return loadFragment(fragment) ;
            }
        });
    }

    boolean loadFragment(Fragment fragment){
        if(fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }else{

            return false;
        }
    }

        public void hideNavigationBar () {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_FULLSCREEN);
        }

        @Override
        public void onWindowFocusChanged ( boolean hasFocus){
            super.onWindowFocusChanged(hasFocus);
            if (hasFocus) {
                hideNavigationBar();
            }
        }
    }
