package kr.ac.mju.hanmaeum.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import kr.ac.mju.hanmaeum.R;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        },500);//0.5초 후 스플래시 이미지 닫기.

    }
}
