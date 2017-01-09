package kr.ac.mju.hanmaeum.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.content.Intent;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import kr.ac.mju.hanmaeum.R;
import kr.ac.mju.hanmaeum.utils.Constants;

public class BaseActivity extends AppCompatActivity implements Drawer.OnDrawerItemClickListener {

    private String TAG = getClass().getSimpleName();

    private View headerLayout;
    private Drawer drawer;
    private long currentPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.title_header);
    }

    // 아이템 클릭시


    @Override public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

        Bundle args = new Bundle();

        Intent intent = new Intent(BaseActivity.this, SubActivity.class);
        if (drawerItem != null) {
            long index = drawerItem.getIdentifier();
            Log.i(TAG, "current position : " + currentPosition + " clicked position : " + index);
            if (currentPosition == index) {
                currentPosition = 0;
                return false;
            }
            switch ((int) index) {
                case Constants.SHUTTLE_BUS:
                    args.putInt(Constants.FRAGMENT_KEY, Constants.SHUTTLE_BUS);
                    intent.putExtras(args);
                    startActivity(intent, args);
                    break;
                case Constants.INTERCITY_BUS:
                    args.putInt(Constants.FRAGMENT_KEY, Constants.INTERCITY_BUS);
                    intent.putExtras(args);
                    startActivity(intent, args);
                    break;
                case Constants.KOBUS:
                    args.putInt(Constants.FRAGMENT_KEY, Constants.KOBUS);
                    intent.putExtras(args);
                    startActivity(intent, args);
                    break;
                case Constants.LOAD_SEARCH:
                    args.putInt(Constants.FRAGMENT_KEY, Constants.LOAD_SEARCH);
                    intent.putExtras(args);
                    startActivity(intent, args);
                    break;
                case Constants.WITH_TAXI:
                    args.putInt(Constants.FRAGMENT_KEY, Constants.WITH_TAXI);
                    intent.putExtras(args);
                    startActivity(intent, args);
                    break;
                case Constants.SUBWAY:
                    args.putInt(Constants.FRAGMENT_KEY, Constants.SUBWAY);
                    intent.putExtras(args);
                    startActivity(intent, args);
                    break;
                case Constants.SEARCH_LOG:
                    args.putInt(Constants.FRAGMENT_KEY, Constants.SEARCH_LOG);
                    intent.putExtras(args);
                    startActivity(intent, args);
                    break;
                default:
                    drawer.closeDrawer();
            }
        }

        return false;
    }

    // 네비게이션 드로워 세팅
    protected void setNavigationDrawer(Bundle args) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        View layout = getLayoutInflater().inflate(R.layout.title_header, null);
        headerLayout = layout;

        drawer = new DrawerBuilder().withActivity(this).withTranslucentNavigationBar(false).withToolbar(toolbar)
                .withHeader(layout)
                .addDrawerItems(
                        new SecondaryDrawerItem().withName(R.string.shuttle).withIdentifier(Constants.SHUTTLE_BUS),
                        new SecondaryDrawerItem().withName(R.string.intercity).withIdentifier(Constants.INTERCITY_BUS),
                        new SecondaryDrawerItem().withName(R.string.kobus).withIdentifier(Constants.KOBUS),
                        new SecondaryDrawerItem().withName(R.string.load_search).withIdentifier(Constants.LOAD_SEARCH),
                        new SecondaryDrawerItem().withName(R.string.with_taxi).withIdentifier(Constants.WITH_TAXI),
                        new SecondaryDrawerItem().withName(R.string.subway).withIdentifier(Constants.SUBWAY),
                        new SecondaryDrawerItem().withName(R.string.search_log).withIdentifier(Constants.SEARCH_LOG)
                ).withOnDrawerItemClickListener(this).withSelectedItem(-1)
                .withSavedInstance(args).withShowDrawerOnFirstLaunch(true)
                .build();
    }
}
