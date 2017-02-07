package kr.ac.mju.hanmaeum.fragment;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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

import java.io.IOException;
import java.io.InterruptedIOException;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.ac.mju.hanmaeum.R;
import kr.ac.mju.hanmaeum.utils.Constants;
import kr.ac.mju.hanmaeum.utils.PreferenceManager;
import kr.ac.mju.hanmaeum.utils.object.shuttle.Shuttle;
import kr.ac.mju.hanmaeum.utils.adapter.ShuttleAdapter;
import kr.ac.mju.hanmaeum.utils.service.ShuttleService;
import kr.ac.mju.hanmaeum.utils.service.database.BookmarkDatabase;
import kr.ac.mju.hanmaeum.utils.service.database.DatabaseHelper;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class ShuttleFragment extends Fragment {

    private ArrayList<Shuttle> shuttleList;
    private ArrayList<Shuttle> bookmark;
    @BindView(R.id.shuttleTime)
    ListView shuttleTime;
    private boolean checkSum = false;

    private OkHttpClient client = new OkHttpClient();
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


        BackRunnable runnable = new BackRunnable();
        Thread thread = new Thread(runnable);
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shuttle, container, false);
        ButterKnife.bind(this, view);

        rb1 = (RadioButton) view.findViewById(R.id.shuttle_rb1);
        rb2 = (RadioButton) view.findViewById(R.id.shuttle_rb2);
        rb3 = (RadioButton) view.findViewById(R.id.shuttle_rb3);

        shuttleList = new ArrayList<>();
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

                int i = 0;
                for (Element e : elements) {
                    String[] split = e.text().split(" ");
                    boolean flag = isNumber(split[0]);

                    if (flag) {
                        i++;
                        if (split.length == 3) {
                            shuttleList.add(new Shuttle(String.valueOf(i), getString(R.string.downtownShuttle), split[1], split[2]));
                        } else {
                            shuttleList.add(new Shuttle(String.valueOf(i), split[1], split[2], split[3]));
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
            setDatabase();
            setShuttleTime();
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

                ShuttleAdapter shuttleAdapter = new ShuttleAdapter(getActivity(), checkShuttleList, bookmark, checkSum);
                shuttleTime.setAdapter(shuttleAdapter);

            } else if (v.getId() == R.id.shuttle_rb3) {
                for (int i = 75; i < 85; i++) {
                    if (flag) break;
                    String[] _startTime = shuttleList.get(i).getStart_time().split(":");
                    if (Integer.parseInt(_nowTime[0]) < Integer.parseInt(_startTime[0])) {
                        for (int j = i; j < 85; j++) {
                            checkShuttleList.add(shuttleList.get(j));
                        }
                        checkSum = true;
                        flag = true;
                    } else if (Integer.parseInt(_nowTime[0]) == Integer.parseInt(_startTime[0])) {
                        if (Integer.parseInt(_nowTime[1]) <= Integer.parseInt(_startTime[1])) {
                            for (int j = i; j < 85; j++) {
                                checkShuttleList.add(shuttleList.get(j));
                            }
                            checkSum = true;
                            flag = true;
                        }
                    }
                }

                ShuttleAdapter shuttleAdapter = new ShuttleAdapter(getActivity(), checkShuttleList, setBookmark(bookmark), checkSum);
                shuttleTime.setAdapter(shuttleAdapter);
            }
        }
    };

    private ArrayList<Shuttle> setBookmark(ArrayList<Shuttle> book) {
        ArrayList<Shuttle> books = new ArrayList<>();
        for (Shuttle s : book) {
            if (Integer.parseInt(s.getNo()) > 75) {
                books.add(s);
            }
        }
        return books;
    }

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
        ShuttleAdapter shuttleAdapter = new ShuttleAdapter(getActivity(), checkShuttleList, bookmark, checkSum);
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
        if (PreferenceManager.getShuttleDatabase(getActivity())) {
            BookmarkDatabase database = new BookmarkDatabase();
            bookmark = database.getBookmarkCheck(getActivity());
        }
    }

    private void setDatabase() {
        if (!PreferenceManager.getShuttleDatabase(getActivity())) {
            DatabaseHelper helper = new DatabaseHelper(getActivity());
            SQLiteDatabase db = helper.getWritableDatabase();

            BookmarkDatabase database = new BookmarkDatabase();
            database.createDatabase(getActivity());
            if (database.openDatabase(getActivity())) {
                int i = 1;
                for (Shuttle s : shuttleList) {
                    ContentValues values = new ContentValues();

                    values.put(Constants.TABLE_COL_ID, i);
                    values.put(Constants.TABLE_COL_TIME, s.getStart_time());
                    values.put(Constants.TABLE_COL_BOOKMARK, false);

                    try {
                        db.beginTransaction();
                        db.insert(Constants.BOOKMARK_TABLE, null, values);
                        db.setTransactionSuccessful();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        db.endTransaction();
                    }
                    i++;
                }
            }

            PreferenceManager.setShuttleDatabase(getActivity());
        }
    }

    class BackRunnable implements Runnable {
        public void run() {
            while(true) {
                if(ShuttleService.getShuttleServiceCheckList(getActivity())) {
                    sendNotification();
                }
                try {
                    Thread.sleep(60000);
                }catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void sendNotification() {
            RequestBody body = new FormBody.Builder()
                    .add("Message", "버스 도착 5분 전")
                    .build();

            //request
            Request request = new Request.Builder()
                    .url("http://183.101.80.77:880/FCM/push_notification.php")
                    .post(body)
                    .build();

            client = new OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .build();

            okhttp3.Call call = client.newCall(request);
            call.enqueue(new okhttp3.Callback() {
                @Override
                public void onFailure(okhttp3.Call call, IOException e) {
                    Log.e("ONFAILURE@@@@@@@", "Registration error: " + e.getMessage());
                }

                @Override
                public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                    try {
                        String resp = response.body().string();
                        Log.v("ONSREPONSE!!!!", resp);
                        if (response.isSuccessful()) {
                        } else {

                        }
                    } catch (IOException e) {
                        // Log.e(TAG_REGISTER, "Exception caught: ", e);
                    }
                }
            });
        }
    }
}

