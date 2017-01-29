package kr.ac.mju.hanmaeum.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import android.widget.RadioButton;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.ac.mju.hanmaeum.R;
import kr.ac.mju.hanmaeum.utils.Constants;
import kr.ac.mju.hanmaeum.utils.object.shuttle.Shuttle;
import kr.ac.mju.hanmaeum.utils.adapter.ShuttleAdapter;

public class ShuttleFragment extends Fragment {

    private ArrayList<Shuttle> shuttleList;
    @BindView(R.id.shuttleTime)
    ListView shuttleTime;
    public RadioButton rb1;
    public RadioButton rb2;
    public RadioButton rb3;


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

        rb1=(RadioButton)view.findViewById(R.id.shuttle_rb1);
        rb2=(RadioButton)view.findViewById(R.id.shuttle_rb2);
        rb3=(RadioButton)view.findViewById(R.id.shuttle_rb3);

        shuttleList = new ArrayList<Shuttle>();
        GetShuttleTime getShuttleTime = new GetShuttleTime();
        getShuttleTime.execute();

        rb1.setOnClickListener(optionClickListener);
        rb2.setOnClickListener(optionClickListener);
        rb3.setOnClickListener(optionClickListener);

        rb1.setChecked(true);

        return view;
    }

    class GetShuttleTime extends AsyncTask<Void, Void, ArrayList<Shuttle>> {

        // 처리하는 곳
        // 셔틀정보를가져오는 부분.
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

    RadioButton.OnClickListener optionClickListener = new RadioButton.OnClickListener(){
        @Override
        public void onClick(View v) {
            ArrayList<Shuttle> checkShuttleList = new ArrayList<>();
            if(v.getId() == R.id.shuttle_rb1){
                for(int i=0; i<75; i++){
                    checkShuttleList.add(shuttleList.get(i));
                    Log.i("TAG", checkShuttleList.get(i).toString());
                }
                ShuttleAdapter shuttleAdapter = new ShuttleAdapter(getActivity(), checkShuttleList);
                shuttleTime.setAdapter(shuttleAdapter);
            }
            else if(v.getId() == R.id.shuttle_rb2){
                for(int i=0;i<65;i++){
                    checkShuttleList.add(shuttleList.get(i));
                }
                ShuttleAdapter shuttleAdapter = new ShuttleAdapter(getActivity(), checkShuttleList);
                shuttleTime.setAdapter(shuttleAdapter);
            }
            else if(v.getId()==R.id.shuttle_rb3){
                for(int i=75;i<85;i++){
                    checkShuttleList.add(shuttleList.get(i));
                }
                ShuttleAdapter shuttleAdapter = new ShuttleAdapter(getActivity(), checkShuttleList);
                shuttleTime.setAdapter(shuttleAdapter);
            }
        }
    };

    private boolean isNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
