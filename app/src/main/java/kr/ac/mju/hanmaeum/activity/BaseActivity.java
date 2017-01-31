package kr.ac.mju.hanmaeum.activity;

import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.ac.mju.hanmaeum.R;
import kr.ac.mju.hanmaeum.utils.Constants;
import kr.ac.mju.hanmaeum.utils.object.weather.Info;
import kr.ac.mju.hanmaeum.utils.service.WeatherService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseActivity extends AppCompatActivity
        implements Drawer.OnDrawerItemClickListener,  GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{

    private String TAG = getClass().getSimpleName();

    private View headerLayout;
    private Drawer drawer;
    private long currentPosition = 0;

    public double temp = 0.0;
    public String weather;
    public String loc;

    private Location location;
    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_header);
    }

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

        buildGoogleApiClient();

        if (googleApiClient != null) {
            googleApiClient.connect();
        }

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        headerLayout = getLayoutInflater().inflate(R.layout.title_header, null);


        drawer = new DrawerBuilder().withActivity(this).withTranslucentNavigationBar(false).withToolbar(toolbar)
                .withHeader(headerLayout)
                .addDrawerItems(
                        new SecondaryDrawerItem().withIcon(R.drawable.ic_drawer_bus).withName(R.string.shuttle).withIdentifier(Constants.SHUTTLE_BUS),
                        new SecondaryDrawerItem().withIcon(R.drawable.ic_drawer_bus).withName(R.string.where_shuttle).withIdentifier(Constants.SHUTTLE_BUS),
                        new SecondaryDrawerItem().withIcon(R.drawable.ic_drawer_bus).withName(R.string.intercity).withIdentifier(Constants.INTERCITY_BUS),
                        new SecondaryDrawerItem().withIcon(R.drawable.ic_drawer_bus).withName(R.string.kobus).withIdentifier(Constants.KOBUS),
                        new SecondaryDrawerItem().withIcon(R.drawable.ic_drawer_map).withName(R.string.load_search).withIdentifier(Constants.LOAD_SEARCH),
//                        new SecondaryDrawerItem().withName(R.string.with_taxi).withIdentifier(Constants.WITH_TAXI),
                        new SecondaryDrawerItem().withIcon(R.drawable.ic_drawer_train).withName(R.string.subway).withIdentifier(Constants.SUBWAY)
                ).withOnDrawerItemClickListener(this).withSelectedItem(-1)
                .withSavedInstance(args).withShowDrawerOnFirstLaunch(true)
                .build();
    }

    @Override public void onConnected(Bundle bundle) {
        try {
            location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
            if (location != null) {
                Log.i("TAG", location.getLatitude() + " " + location.getLongitude());
                getWeather(location.getLatitude(), location.getLongitude());
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }

    }

    @Override public void onConnectionSuspended(int i) {

    }

    @Override public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    protected synchronized void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }


    private void getWeather(double lat, double lon) {
        final Call<Info> subwayArrivalInfo = WeatherService.subwayInfoApi().getWeatherInfo(lat, lon, Constants.WEATHER_KEY);
        subwayArrivalInfo.enqueue(new Callback<Info>() {
            @Override public void onResponse(Call<Info> call, Response<Info> response) {
                if (response.isSuccessful()) {
                    Info info = response.body();
                    temp = (info.getMain().getTemp() - 273.15);
                    weather = info.getWeather().get(0).getIcon();
                    loc = info.getName();

                    ImageView weatherImage = (ImageView) headerLayout.findViewById(R.id.weather);
                    TextView degree = (TextView) headerLayout.findViewById(R.id.degree);
                    TextView location = (TextView) headerLayout.findViewById(R.id.location);

                    Glide.with(BaseActivity.this)
                            .load(Constants.WEATHER_ICON + weather + ".png")
                            .into(weatherImage);

                    degree.setText(String.valueOf(temp));
                    location.setText(loc);
                } else {
                    Toast.makeText(BaseActivity.this, getString(R.string.not_success), Toast.LENGTH_SHORT).show();
                }
            }

            @Override public void onFailure(Call<Info> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(BaseActivity.this, getString(R.string.on_Failure), Toast.LENGTH_SHORT).show();
            }
        });
    }



}
