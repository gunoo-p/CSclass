package com.example.study_test;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.naver.maps.map.NaverMapSdk;
public class MainActivity extends AppCompatActivity {

    TextView textView;
//    String[] items = {"서울", "경기도","인천", "강원도", "대전", "충청도", "경상도", "울산", "부산", "전라도", "제주"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        Spinner spinnerMenu = (Spinner)findViewById(R.id.Area_spinner);

        final String[] areas = getResources().getStringArray(R.array.area_name);

        ArrayAdapter menuAdapter = ArrayAdapter.createFromResource(this, R.array.area_name, android.R.layout.simple_spinner_item);
        menuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMenu.setAdapter(menuAdapter);

        spinnerMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                textView.setText(areas[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // 네이버맵
        NaverMapSdk.getInstance(this).setClient(
                new NaverMapSdk.NaverCloudPlatformClient("1jsmwgic4r"));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}