package kr.ac.mju.hanmaeum.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.ac.mju.hanmaeum.R;
import kr.ac.mju.hanmaeum.fragment.InterCityFragment;
import kr.ac.mju.hanmaeum.fragment.ShuttleFragment;
import kr.ac.mju.hanmaeum.utils.Constants;

public class SubActivity extends AppCompatActivity implements View.OnClickListener {

    /*@BindView(R.id.toolbar_home)
    ImageView home_btn;

    @BindView(R.id.toolbar_back)
    ImageView back_btn;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        TextView title = (TextView) findViewById(R.id.toolbar_title);
        ImageView home_btn = (ImageView) findViewById(R.id.toolbar_home);
        ImageView back_btn = (ImageView) findViewById(R.id.toolbar_back);

        ButterKnife.bind(this);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        int index = getIntent().getExtras().getInt(Constants.FRAGMENT_KEY);

        if (index == Constants.SHUTTLE_BUS) {
            title.setText(getString(R.string.shuttle));
            Fragment fragment = ShuttleFragment.newInstance();
            fragment.setArguments(savedInstanceState);
            getSupportFragmentManager().beginTransaction().replace(R.id.sub_container , fragment).addToBackStack(null).commit();
        } else if (index == Constants.INTERCITY_BUS) {
            title.setText(getString(R.string.intercity));
            Fragment fragment = InterCityFragment.newInstance();
            fragment.setArguments(savedInstanceState);
            getSupportFragmentManager().beginTransaction().replace(R.id.sub_container , fragment).addToBackStack(null).commit();
        } else if (index == Constants.KOBUS) {
            title.setText(getString(R.string.kobus));

        } else if (index == Constants.LOAD_SEARCH) {
            title.setText(getString(R.string.load_search));

        } else if (index == Constants.WITH_TAXI) {
            title.setText(getString(R.string.with_taxi));

        } else if (index == Constants.SUBWAY) {
            title.setText(getString(R.string.subway));

        } else if (index == Constants.SEARCH_LOG) {
            title.setText(getString(R.string.search_log));

        }

        back_btn.setOnClickListener(this);
        home_btn.setOnClickListener(this);
    }

    @Override public void onClick(View view) {
        if (view.getId() == R.id.toolbar_back) {
            onBackPressed();
        } else if (view.getId() == R.id.toolbar_home) {
            finish();
        }
    }

    /*@OnClick(R.id.toolbar_home)
    public void setHome_btn() {
        finish();
    }

    @OnClick(R.id.toolbar_back)
    public void setBack_btn() {
        onBackPressed();
    }*/

    @Override public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }
}
