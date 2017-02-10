package kr.ac.mju.hanmaeum.fragment;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import kr.ac.mju.hanmaeum.R;
import kr.ac.mju.hanmaeum.utils.Constants;
import kr.ac.mju.hanmaeum.utils.PreferenceManager;
import kr.ac.mju.hanmaeum.utils.object.shuttle.Shuttle;
import kr.ac.mju.hanmaeum.utils.adapter.ShuttleAdapter;
import kr.ac.mju.hanmaeum.utils.service.ShuttleService;
import kr.ac.mju.hanmaeum.utils.service.database.BookmarkDatabase;
import kr.ac.mju.hanmaeum.utils.service.database.DatabaseHelper;
import okhttp3.OkHttpClient;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;


public class ShuttleFragment extends Fragment {
    private OkHttpClient client;
    private ArrayList<Shuttle> shuttleList;
    private ArrayList<Shuttle> bookmark;
    @BindView(R.id.shuttleTime)
    ListView shuttleTime;

    private Context context;
    private boolean flag = false;

    private ShuttleAdapter shuttleAdapter;


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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    public void onPause() {
        super.onPause();

        /*Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(ShuttleService.getShuttleServiceCheckList(context)) {
                    Log.d("CALL#########", "check");
                    sendNotification();
                }
            }
        }, 20000);*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shuttle, container, false);
        ButterKnife.bind(this, view);

        shuttleList = new ArrayList<>();
        bookmark = new ArrayList<>();
        GetShuttleTime getShuttleTime = new GetShuttleTime();
        getShuttleTime.execute();

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
            setAdapter();
            super.onPostExecute(shuttleList);
        }
    }

    private void setAdapter() {
        setBookmark();

        shuttleAdapter = new ShuttleAdapter(shuttleList, bookmark);
        shuttleTime.setAdapter(shuttleAdapter);

        shuttleTime.deferNotifyDataSetChanged();
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

    public class ShuttleAdapter extends BaseAdapter {

        private ArrayList<Shuttle> shuttles;
        private ArrayList<Shuttle> bookmark;
        private BookmarkDatabase database;

        // 어떤페이지에서 가져왔는지, 어떤 자료를 가져왔는지
        public ShuttleAdapter(ArrayList<Shuttle> shuttles, ArrayList<Shuttle> bookmark) {
            this.shuttles = shuttles;
            this.bookmark = bookmark;

            database = new BookmarkDatabase();
        }

        // 리스트뷰(어레이리스트)의 갯수
        @Override
        public int getCount() {
            return shuttles.size();
        }

        // 해당되는 오브젝트의 값
        @Override
        public Object getItem(int i) {
            return shuttles.get(i);
        }

        // 해당되는 아이템의 번호
        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            final ViewHolder holder;

            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                view = inflater.inflate(R.layout.shuttle_content, viewGroup, false);
                holder = new ViewHolder(view);

                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            final Shuttle shuttle = shuttles.get(i);
            final Shuttle book = bookmark.get(i);

            holder.shuttle_number.setText(shuttle.getNo());
            holder.shuttle_type.setText(shuttle.getType());
            holder.shuttle_start_time.setText(shuttle.getStart_time());
            holder.shuttle_ramp_time.setText(shuttle.getRamp_time());

            if (book.isBookmark() && book.getNo().equals(shuttle.getNo())) {
                holder.bookmark.setImageResource(R.drawable.ic_filled_favorite);
            } else {
                holder.bookmark.setImageResource(R.drawable.ic_empty_favorite);
            }

            holder.bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // if existing data exists, there is a value in the Database
                    if (book.isBookmark()) {
                        database.setBookmarkCheck(getActivity(), shuttle.getNo(), false);
                        holder.bookmark.setImageResource(R.drawable.ic_empty_favorite);
                    } else {
                        database.setBookmarkCheck(getActivity(), shuttle.getNo(), true);
                        holder.bookmark.setImageResource(R.drawable.ic_filled_favorite);
                    }
                    refresh(shuttle.getStart_time(), shuttle.getType());
                }
            });

            return view;
        }

        class ViewHolder {
            @BindView(R.id.shuttle_number)
            TextView shuttle_number;
            @BindView(R.id.shuttle_type)
            TextView shuttle_type;
            @BindView(R.id.shuttle_start_time)
            TextView shuttle_start_time;
            @BindView(R.id.shuttle_ramp_time)
            TextView shuttle_ramp_time;
            @BindView(R.id.bookmark)
            ImageView bookmark;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

    private void refresh(String time, String type) {
        String text = time + " " + type;
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.detach(this).attach(this).commit();

        new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText(text)
                .setContentText(getString(R.string.shuttle_bookmark))
                .show();
    }
}
