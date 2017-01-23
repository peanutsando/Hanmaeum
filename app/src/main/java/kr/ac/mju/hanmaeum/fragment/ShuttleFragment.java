package kr.ac.mju.hanmaeum.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.ac.mju.hanmaeum.R;
import kr.ac.mju.hanmaeum.utils.Constants;
import kr.ac.mju.hanmaeum.utils.shuttle.Shuttle;
import kr.ac.mju.hanmaeum.utils.adapter.ShuttleAdapter;

public class ShuttleFragment extends Fragment {

    private ArrayList<Shuttle> shuttleList;
    private ImageView imageView;
    @BindView(R.id.shuttleTime)
    ListView shuttleTime;

    public ShuttleFragment() {
        // Required empty public constructor
    }

    public static ShuttleFragment newInstance() {
        ShuttleFragment fragment = new ShuttleFragment();
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
        View view = inflater.inflate(R.layout.fragment_shuttle, container, false);
        ButterKnife.bind(this, view);

        shuttleList = new ArrayList<Shuttle>();
        GetShuttleTime getShuttleTime = new GetShuttleTime();
        getShuttleTime.execute();

        return view;
    }

    class GetShuttleTime extends AsyncTask<Void, Void, ArrayList<Shuttle>> {

        // 처리하는 곳
        @Override protected ArrayList<Shuttle> doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect(Constants.SHUTTLE_URL).get();

                Elements elements = doc.select(Constants.SHUTTLE_TABLE_ELEMENT);

                for (Element e : elements) {
                    String[] split = e.text().split(" ");
                    boolean flag = isNumber(split[0]);

                    if (flag) {
                        Log.i("TAG", e.text());
                        if (split.length == 3) {
                            shuttleList.add(new Shuttle(split[0], getString(R.string.downtownShuttle), split[1], split[2]));
                        } else {
                            shuttleList.add(new Shuttle(split[0], split[1], split[2], split[3]));
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }


        @Override protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override protected void onPostExecute(ArrayList<Shuttle> shuttles) {
            ShuttleAdapter shuttleAdapter = new ShuttleAdapter(getActivity(), shuttleList);
            shuttleTime.setAdapter(shuttleAdapter);
            super.onPostExecute(shuttleList);
        }
    }

    private boolean isNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
