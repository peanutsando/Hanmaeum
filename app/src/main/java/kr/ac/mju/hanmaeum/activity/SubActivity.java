package kr.ac.mju.hanmaeum.activity;

import android.location.Location;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.ac.mju.hanmaeum.R;
import kr.ac.mju.hanmaeum.fragment.InterCityFragment;
import kr.ac.mju.hanmaeum.fragment.ShuttleFragment;
import kr.ac.mju.hanmaeum.fragment.ShuttleLocationFragment;
import kr.ac.mju.hanmaeum.fragment.SubwayFragment;
import kr.ac.mju.hanmaeum.fragment.SubwaySearchFragment;
import kr.ac.mju.hanmaeum.fragment.TerminalFragment;
import kr.ac.mju.hanmaeum.utils.Constants;

public class SubActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_home)
    ImageView home_btn;

    @BindView(R.id.toolbar_back)
    ImageView back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        TextView title = (TextView) findViewById(R.id.toolbar_title);
        /*ImageView home_btn = (ImageView) findViewById(R.id.toolbar_home);
        ImageView back_btn = (ImageView) findViewById(R.id.toolbar_back);*/

        ButterKnife.bind(this);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        int index = getIntent().getExtras().getInt(Constants.FRAGMENT_KEY);

        if (index == Constants.SHUTTLE_BUS) {
            title.setText(getString(R.string.shuttle));
            Fragment fragment = ShuttleFragment.newInstance();
            fragment.setArguments(savedInstanceState);
            getSupportFragmentManager().beginTransaction().replace(R.id.sub_container, fragment).addToBackStack(null).commit();
        } else if(index == Constants.SHUTTLE_LOCATION){
            title.setText(getString(R.string.where_shuttle));

            Double lat = getIntent().getExtras().getDouble(Constants.LOCATION_LAT_KEY);
            Double lon = getIntent().getExtras().getDouble(Constants.LOCATION_LON_KEY);

            Fragment fragment = ShuttleLocationFragment.newInstance();
            Bundle bundle = new Bundle();
            bundle.putDouble(Constants.LOCATION_LAT_KEY, lat);
            bundle.putDouble(Constants.LOCATION_LON_KEY, lon);

            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.sub_container, fragment).addToBackStack(null).commit();
        } else if (index == Constants.INTERCITY_BUS) {
            title.setText(getString(R.string.intercity));
            Fragment fragment = InterCityFragment.newInstance();
            fragment.setArguments(savedInstanceState);
            getSupportFragmentManager().beginTransaction().replace(R.id.sub_container, fragment).addToBackStack(null).commit();
        } else if (index == Constants.KOBUS) {
            title.setText(getString(R.string.kobus));
            Fragment fragment = TerminalFragment.newInstance();
            fragment.setArguments(savedInstanceState);
            getSupportFragmentManager().beginTransaction().replace(R.id.sub_container, fragment).addToBackStack(null).commit();
        } else if (index == Constants.LOAD_SEARCH) {
            title.setText(getString(R.string.load_search));
            Fragment fragment = SubwaySearchFragment.newInstance();
            fragment.setArguments(savedInstanceState);
            getSupportFragmentManager().beginTransaction().replace(R.id.sub_container, fragment).addToBackStack(null).commit();
        }  else if (index == Constants.SUBWAY) {
            title.setText(getString(R.string.subway));
            Fragment fragment = SubwayFragment.newInstance();
            fragment.setArguments(savedInstanceState);
            getSupportFragmentManager().beginTransaction().replace(R.id.sub_container, fragment).addToBackStack(null).commit();
        }
    }

    @OnClick(R.id.toolbar_home)
    public void setHome_btn() {
        finish();
    }

    @OnClick(R.id.toolbar_back)
    public void setBack_btn() {
        onBackPressed();
    }

    @Override public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }
}
