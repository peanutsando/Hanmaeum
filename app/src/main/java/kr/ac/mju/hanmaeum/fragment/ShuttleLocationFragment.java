package kr.ac.mju.hanmaeum.fragment;

import android.content.Context;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.ButterKnife;
import kr.ac.mju.hanmaeum.R;
import kr.ac.mju.hanmaeum.utils.Constants;
import kr.ac.mju.hanmaeum.utils.object.shuttle.ShuttleLocation;

public class ShuttleLocationFragment extends Fragment implements OnMapReadyCallback {

    private double lat;
    private double lon;

    private GoogleMap map;

    public ShuttleLocationFragment() {
        // Required empty public constructor
    }

    public static ShuttleLocationFragment newInstance() {
        ShuttleLocationFragment fragment = new ShuttleLocationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*Bundle args = getArguments();
        lat = args.getDouble(Constants.LOCATION_LAT_KEY);
        lon = args.getDouble(Constants.LOCATION_LON_KEY);*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shuttle_location, container, false);
        ButterKnife.bind(this, view);

        Bundle args = getArguments();
        lat = args.getDouble(Constants.LOCATION_LAT_KEY);
        lon = args.getDouble(Constants.LOCATION_LON_KEY);

        setLocation();
        return view;
    }

    @Override public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;
        setMyMarker();

        setRampMarker();
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 13));
    }

    private void setLocation() {
        GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity());
        MapFragment mapFragment = ((MapFragment) getActivity().getFragmentManager().findFragmentById(R.id.map));
        mapFragment.getMapAsync(this);
    }

    private void setMyMarker() {
        MarkerOptions options = new MarkerOptions();
        options.position(new LatLng(lat, lon));
        options.title(getString(R.string.now_location));
        options.icon(BitmapDescriptorFactory.fromResource(R.drawable.pin));
        map.addMarker(options);
    }

    private void setRampMarker() {
        for (int i = 0; i < Constants.RAMP_SHUTTLE_LOCATION_LIST.length; i++) {
            MarkerOptions options = new MarkerOptions();
            ShuttleLocation location = Constants.RAMP_SHUTTLE_LOCATION_LIST[i];
            options.position(new LatLng(location.getLat(), location.getLon()));
            options.icon(BitmapDescriptorFactory.fromResource(R.drawable.bus));
            map.addMarker(options);
        }
    }
}
