package com.example.study_test;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_intro);

        ImageView rabbit = (ImageView) findViewById(R.id.airplane);
        Glide.with(this).load(R.drawable.test2).into(rabbit);

        // 애니메이션이 끝난 후 메인 액티비티로 이동
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(IntroActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }, 3000); // 애니메이션 시간과 동일하게 설정 (3000ms)
    }
}