package kr.ac.mju.hanmaeum.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import kr.ac.mju.hanmaeum.R;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try{
            Thread.sleep(1000); // 스플래시 화면 대기 시간 1000 = 1초
        }catch(Exception e){
            e.printStackTrace();
        }
        setContentView(R.layout.activity_splash);

        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}
