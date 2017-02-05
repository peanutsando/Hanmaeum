package kr.ac.mju.hanmaeum.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import kr.ac.mju.hanmaeum.R;
import kr.ac.mju.hanmaeum.utils.object.subway.shortestRoute;
import kr.ac.mju.hanmaeum.utils.service.SubwaySearchService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubwaySearchFragment extends Fragment {

    @BindView(R.id.search)
    ImageView search;

    @BindView(R.id.start_station)
    EditText start_station;

    @BindView(R.id.fin_station)
    EditText fin_station;

    @BindView(R.id.start_point)
    TextView start_point;
    @BindView(R.id.fin_point)
    TextView fin_point;

    private shortestRoute shortestRoute;
    private String start, fin;

    @BindView(R.id.shtStat)
    TextView shtStat;
    @BindView(R.id.shtTrans)
    TextView shtTrans;

    @BindView(R.id.minStat)
    TextView minStat;
    @BindView(R.id.minTrans)
    TextView minTrans;

    @BindView(R.id.min_time)
    TextView min_time;
    @BindView(R.id.min_trans)
    TextView min_trans;

    public SubwaySearchFragment() {
        // Required empty public constructor
    }

    public static SubwaySearchFragment newInstance() {
        SubwaySearchFragment fragment = new SubwaySearchFragment();
        Bundle args = new Bundle();
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
        View view = inflater.inflate(R.layout.fragment_subway_search, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.search)
    public void setSearch() {
        start = start_station.getText().toString();
        fin = fin_station.getText().toString();
        SweetAlertDialog alertDialog;

        if (start.length() != 0 && fin.length() != 0) {
            callSubwaySearchInfo();
        } else {
            if (start.length() == 0 && fin.length() == 0) {
                alertDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                        .setTitleText(getString(R.string.all_text_null));

                alertDialog.show();
            } else if (start.length() == 0) {
                alertDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                        .setTitleText(getString(R.string.start_text_null));

                alertDialog.show();
            } else if (fin.length() == 0) {
                alertDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                        .setTitleText(getString(R.string.fin_text_null));

                alertDialog.show();
            }
        }
    }

    private void callSubwaySearchInfo() {
        final Call<shortestRoute> subwaySearchInfo = SubwaySearchService.subwaySearchInfoAPI().getSubwaySearchInfo(start, fin);
        subwaySearchInfo.enqueue(new Callback<shortestRoute>() {
            @Override
            public void onResponse(Call<shortestRoute> call, Response<shortestRoute> response) {
                if (response.isSuccessful()) {
                    shortestRoute = response.body();
                    setShortestRoute();
                } else {
                    Toast.makeText(getActivity(), getString(R.string.not_success), Toast.LENGTH_SHORT).show();
                }
            }

            @Override public void onFailure(Call<shortestRoute> call, Throwable t) {
                Toast.makeText(getActivity(), getString(R.string.on_Failure), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setShortestRoute() {
        min_time.setText(getString(R.string.min_time));
        min_trans.setText(getString(R.string.min_trans));

        start_point.setText(start);
        fin_point.setText(fin);

        shortestRoute.ShortestRouteList route = shortestRoute.getShortestRouteList().get(0);

        shtStat.setText(route.getShtTransferMsg());
        shtTrans.setText(setReplaceSpace(route.getShtStatnNm()));

        minStat.setText(route.getMinTransferMsg());
        minTrans.setText(setReplaceSpace(route.getMinStatnNm()));
    }

    private String setReplaceSpace(String text) {
        return text.replace("    , ", " → ");
    }
}
