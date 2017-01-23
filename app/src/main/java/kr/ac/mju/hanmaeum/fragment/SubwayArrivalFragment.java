package kr.ac.mju.hanmaeum.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.ac.mju.hanmaeum.R;
import kr.ac.mju.hanmaeum.utils.adapter.SubwayArrivalAdapter;
import kr.ac.mju.hanmaeum.utils.object.subway.ArrivalInfo;

public class SubwayArrivalFragment extends Fragment {

    @BindView(R.id.arrivalInfo)
    ListView arrivalList;
    @BindView(R.id.stationName)
    TextView stationName;

    private static String title;

    private static ArrayList<ArrivalInfo> arrivalInfo = new ArrayList<>();

    public SubwayArrivalFragment() {
        // Required empty public constructor
    }

    public static SubwayArrivalFragment newInstance(ArrayList<ArrivalInfo> list, String station) {
        SubwayArrivalFragment fragment = new SubwayArrivalFragment();
        Bundle args = new Bundle();
        arrivalInfo = list;
        title = station;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_subway_arrival, container, false);
        ButterKnife.bind(this, view);

        stationName.setText(title);

        for(ArrivalInfo a : arrivalInfo){
            Log.i("TAG", a.toString());
        }

        SubwayArrivalAdapter adapter = new SubwayArrivalAdapter(getActivity(), arrivalInfo);
        arrivalList.setAdapter(adapter);
        return view;
    }

}
