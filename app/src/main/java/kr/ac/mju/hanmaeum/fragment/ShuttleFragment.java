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

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.ac.mju.hanmaeum.R;
import kr.ac.mju.hanmaeum.utils.Constants;
import kr.ac.mju.hanmaeum.utils.PreferenceManager;
import kr.ac.mju.hanmaeum.utils.object.shuttle.Shuttle;
import kr.ac.mju.hanmaeum.utils.adapter.ShuttleAdapter;
import kr.ac.mju.hanmaeum.utils.service.database.BookmarkDatabase;

public class ShuttleFragment extends Fragment {

    private ArrayList<Shuttle> shuttleList;
    private ArrayList<Shuttle> bookmark;
    @BindView(R.id.shuttleTime)
    ListView shuttleTime;
    public RadioButton rb1;
    public RadioButton rb2;
    public RadioButton rb3;

    //현재시간 가져오는 부분
    long now = System.currentTimeMillis();
    Time time = new Time(now);

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
    String nowTime = simpleDateFormat.format(time);


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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shuttle, container, false);
        ButterKnife.bind(this, view);

        rb1 = (RadioButton) view.findViewById(R.id.shuttle_rb1);
        rb2 = (RadioButton) view.findViewById(R.id.shuttle_rb2);
        rb3 = (RadioButton) view.findViewById(R.id.shuttle_rb3);

        shuttleList = new ArrayList<Shuttle>();
        bookmark = new ArrayList<>();
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
        @Override
        protected ArrayList<Shuttle> doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect(Constants.SHUTTLE_URL).get();

                Elements elements = doc.select(Constants.SHUTTLE_TABLE_ELEMENT);

                for (Element e : elements) {
                    String[] split = e.text().split(" ");
                    boolean flag = isNumber(split[0]);

                    if (flag) {
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


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<Shuttle> shuttles) {
            setShuttleTime();
            setDatabase();
            super.onPostExecute(shuttleList);
        }
    }

    RadioButton.OnClickListener optionClickListener = new RadioButton.OnClickListener() {
        @Override
        public void onClick(View v) {
            ArrayList<Shuttle> checkShuttleList = new ArrayList<>();
            String[] _nowTime = nowTime.split(":");
            boolean flag = false;

            if (v.getId() == R.id.shuttle_rb1) {
                setShuttleTime();
            } else if (v.getId() == R.id.shuttle_rb2) {
                for (int i = 0; i < 65; i++) {
                    if (flag) break;
                    String[] _startTime = shuttleList.get(i).getStart_time().split(":");
                    if (Integer.parseInt(_nowTime[0]) < Integer.parseInt(_startTime[0])) {
                        for (int j = i; j < 65; j++) {
                            checkShuttleList.add(shuttleList.get(j));
                        }
                        flag = true;
                    } else if (Integer.parseInt(_nowTime[0]) == Integer.parseInt(_startTime[0])) {
                        if (Integer.parseInt(_nowTime[1]) <= Integer.parseInt(_startTime[1])) {
                            for (int j = i; j < 65; j++) {
                                checkShuttleList.add(shuttleList.get(j));
                            }
                            flag = true;
                        }
                    }
                }
                ShuttleAdapter shuttleAdapter = new ShuttleAdapter(getActivity(), checkShuttleList, bookmark);
                shuttleTime.setAdapter(shuttleAdapter);
            } else if (v.getId() == R.id.shuttle_rb3) {
                for (int i = 75; i < 85; i++) {
                    if (flag) break;
                    String[] _startTime = shuttleList.get(i).getStart_time().split(":");
                    if (Integer.parseInt(_nowTime[0]) < Integer.parseInt(_startTime[0])) {
                        for (int j = i; j < 85; j++) {
                            checkShuttleList.add(shuttleList.get(j));
                        }
                        flag = true;
                    } else if (Integer.parseInt(_nowTime[0]) == Integer.parseInt(_startTime[0])) {
                        if (Integer.parseInt(_nowTime[1]) <= Integer.parseInt(_startTime[1])) {
                            for (int j = i; j < 85; j++) {
                                checkShuttleList.add(shuttleList.get(j));
                            }
                            flag = true;
                        }
                    }
                }
                ShuttleAdapter shuttleAdapter = new ShuttleAdapter(getActivity(), checkShuttleList, bookmark);
                shuttleTime.setAdapter(shuttleAdapter);
            }
        }
    };

    private void setShuttleTime() {
        ArrayList<Shuttle> checkShuttleList = new ArrayList<>();
        String[] _nowTime = nowTime.split(":");
        boolean flag = false;
        for (int i = 0; i < 75; i++) {
            if (flag) break;
            String[] _startTime = shuttleList.get(i).getStart_time().split(":");
            if (Integer.parseInt(_nowTime[0]) < Integer.parseInt(_startTime[0])) {
                for (int j = i; j < 75; j++) {
                    checkShuttleList.add(shuttleList.get(j));
                }
                flag = true;
            } else if (Integer.parseInt(_nowTime[0]) == Integer.parseInt(_startTime[0])) {
                if (Integer.parseInt(_nowTime[1]) <= Integer.parseInt(_startTime[1])) {
                    for (int j = i; j < 75; j++) {
                        checkShuttleList.add(shuttleList.get(j));
                    }
                    flag = true;
                }
            }
        }
        setBookmark();
        ShuttleAdapter shuttleAdapter = new ShuttleAdapter(getActivity(), checkShuttleList, bookmark);
        shuttleTime.setAdapter(shuttleAdapter);

        shuttleAdapter.notifyDataSetChanged();
    }


    private boolean isNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void setBookmark() {
        BookmarkDatabase database = new BookmarkDatabase();
        bookmark = database.getBookmarkCheck(getActivity());

        for (int i = 0; i < bookmark.size(); i++) {
            Shuttle s = bookmark.get(i);
        }
    }

    private void setDatabase(){
        if (!PreferenceManager.getShuttleDatabase(getActivity())) {
            BookmarkDatabase database = new BookmarkDatabase();
            for (Shuttle s : shuttleList) {
                database.insertBookmark(getActivity(), s.getNo(), s.getStart_time(), false);
            }
            PreferenceManager.setShuttleDatabase(getActivity());
        }
    }
}
