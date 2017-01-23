package kr.ac.mju.hanmaeum.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.ac.mju.hanmaeum.R;
import kr.ac.mju.hanmaeum.utils.Constants;
import kr.ac.mju.hanmaeum.utils.Encoding;
import kr.ac.mju.hanmaeum.utils.adapter.SubwayAdapter;
import kr.ac.mju.hanmaeum.utils.service.SubwayInfoService;
import kr.ac.mju.hanmaeum.utils.subway.ArrivalInfo;
import kr.ac.mju.hanmaeum.utils.subway.SearchSTNBySubwayLineService;
import kr.ac.mju.hanmaeum.utils.service.SubwayService;
import kr.ac.mju.hanmaeum.utils.subway.Subway;
import kr.ac.mju.hanmaeum.utils.subway.realtimeStationArrival;
import kr.ac.mju.hanmaeum.utils.subway.SubwayImages;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubwayFragment extends Fragment {

    @BindView(R.id.subway_search)
    Button subway_search;

    private SearchSTNBySubwayLineService subways;
    @BindView(R.id.subwayList)
    ListView subwayList;

    public SubwayFragment() {
        // Required empty public constructor
    }

    public static SubwayFragment newInstance() {
        SubwayFragment fragment = new SubwayFragment();
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
        View view = inflater.inflate(R.layout.fragment_subway, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.subway_search)
    public void setSubway_search() {
        setAlertAdapter();
    }

    private void setAlertAdapter() {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setIcon(R.drawable.subway);
        alert.setTitle(getString(R.string.select_to_subway));

        final ArrayAdapter<String> alertAdapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.select_dialog_item
        );

        // AlertDialog List Settings
        alertAdapter.addAll(getResources().getStringArray(R.array.subway));

        alert.setNegativeButton(
                getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }
        );

        // Select List Column
        alert.setAdapter(alertAdapter, new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialogInterface, int i) {
                callSubwayInfo(i);
            }
        });

        alert.show();
    }

    // Start RestAPI
    private void callSubwayInfo(int x) {
        final Call<SearchSTNBySubwayLineService> subwayInfo = SubwayService.subwayInfoApi().getSubwayInfo(Constants.SUBWAY_LINE_KEY[x]);
        subwayInfo.enqueue(new Callback<SearchSTNBySubwayLineService>() {
            @Override
            public void onResponse(Call<SearchSTNBySubwayLineService> call, Response<SearchSTNBySubwayLineService> response) {
                if (response.isSuccessful()) {
                    subways = response.body();
                    setSubwayList();
                } else {
                    Toast.makeText(getActivity(), getString(R.string.not_success), Toast.LENGTH_SHORT).show();
                }
            }

            @Override public void onFailure(Call<SearchSTNBySubwayLineService> call, Throwable t) {
                Toast.makeText(getActivity(), getString(R.string.on_Failure), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setSubwayList() {
        final ArrayList<Subway> list = subways.getSubway();
        for (Subway s : list) {
            int drawable = SubwayImages.getSubwayImages(s.getLINE_NUM());
            s.setSubway_images(drawable);
        }
        SubwayAdapter subwayAdapter = new SubwayAdapter(getActivity(), list);
        subwayList.setAdapter(subwayAdapter);

        subwayList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                String stationName = Encoding.decodeResult(list.get(i).getSTATION_NM());

                final Call<realtimeStationArrival> subwayArrivalInfo = SubwayInfoService.subwayInfoApi().getSubwayArrivalInfo(stationName);
                subwayArrivalInfo.enqueue(new Callback<realtimeStationArrival>() {
                    @Override
                    public void onResponse(Call<realtimeStationArrival> call, Response<realtimeStationArrival> response) {
                        if (response.isSuccessful()) {
                            realtimeStationArrival info = response.body();
                            setArrivalInfo(info.getRealtimeArrivalList(), list.get(i).getSTATION_NM());
                        } else {
                            Toast.makeText(getActivity(), getString(R.string.not_success), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<realtimeStationArrival> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(getActivity(), getString(R.string.on_Failure), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void setArrivalInfo(ArrayList<ArrivalInfo> arrivalInfo, String title){
        Fragment fragment = SubwayArrivalFragment.newInstance(arrivalInfo, title);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.sub_container, fragment).addToBackStack(null).commit();
    }
}
