package kr.ac.mju.hanmaeum.fragment;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import kr.ac.mju.hanmaeum.R;
import kr.ac.mju.hanmaeum.utils.Constants;
import kr.ac.mju.hanmaeum.utils.adapter.InterCityAdapter;
import kr.ac.mju.hanmaeum.utils.object.InterCity.BusInfomation;
import kr.ac.mju.hanmaeum.utils.object.InterCity.Page;
import kr.ac.mju.hanmaeum.utils.service.InterCityService;
import okhttp3.ConnectionPool;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class InterCityFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    @BindView(R.id.search)
    ImageView search;

    @BindView(R.id.search_text)
    EditText search_text;

    @BindView(R.id.busInfo)
    ListView busInfo;

    @BindView(R.id.search_data)
    TextView search_data;

    private String searchBus;
    private List<BusInfomation> bus;

    private boolean flag = false;

    public InterCityFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static InterCityFragment newInstance() {
        InterCityFragment fragment = new InterCityFragment();
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
        View view = inflater.inflate(R.layout.fragment_inter_city, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.search)
    public void setSearch() {
        searchBus = search_text.getText().toString();
        int length = searchBus.length();

        SweetAlertDialog alertDialog;


        if (length == 0) {
            alertDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                    .setTitleText(getString(R.string.search_hint));

            alertDialog.show();
        } else {
            /*alertDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
            alertDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            alertDialog.setTitleText(getString(R.string.loading));
            alertDialog.show();
*/
            bus = new ArrayList<BusInfomation>();
            GetBusInfo getBusInfo = new GetBusInfo();
            getBusInfo.execute();
        }
    }

    private void setBusInfos() {
        InterCityAdapter adapter = new InterCityAdapter(getActivity(), bus);
        busInfo.setAdapter(adapter);
    }

    class GetBusInfo extends AsyncTask<Void, Void, List<BusInfomation>> {
        @Override protected List<BusInfomation> doInBackground(Void... voids) {
            try {
                String url = Constants.INTERCITY_INFO_URL + searchBus + ",0";
                Document doc = Jsoup.connect(url).get();

                List<String> infoName = new ArrayList<String>();
                List<String> valueName = new ArrayList<String>();

                Elements nameElements = doc.select("th");

                for (Element e : nameElements) {
                    infoName.add(e.text());
                }

                Elements valueElements = doc.select("td");

                for (Element e : valueElements) {
                    valueName.add(e.text());
                }

                if (!valueName.get(1).equals(getString(R.string.search_data_null))) {
                    for (int i = 2; i < 7; i++) {
                        bus.add(new BusInfomation(infoName.get(i - 1), valueName.get(i)));
                    }
                    flag = true;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override protected void onPostExecute(List<BusInfomation> busInfomations) {
            if (flag) {
                search_data.setText("");
                setBusInfos();
            } else {
                search_data.setText(getString(R.string.search_data_null));
            }
            super.onPostExecute(busInfomations);
        }
    }

}
