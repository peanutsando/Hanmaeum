package kr.ac.mju.hanmaeum.utils.gms;

import android.content.Context;
import android.location.LocationManager;

/**
 * Created by Youthink on 2017-01-24.
 */

public class GpsService {
    private Context context;

    public GpsService(Context context) {
        this.context = context;
    }

    public boolean isGetLocation() {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        return isGPSEnabled || isNetworkEnabled;
    }
}
