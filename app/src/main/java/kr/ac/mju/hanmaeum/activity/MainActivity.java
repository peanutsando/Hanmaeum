package kr.ac.mju.hanmaeum.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import butterknife.OnClick;
import kr.ac.mju.hanmaeum.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setNavigationDrawer(savedInstanceState);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
