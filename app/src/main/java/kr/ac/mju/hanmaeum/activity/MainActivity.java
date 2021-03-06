package kr.ac.mju.hanmaeum.activity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.ac.mju.hanmaeum.R;
import kr.ac.mju.hanmaeum.activity.notice.NoticeContent;
import kr.ac.mju.hanmaeum.activity.notice.NoticeListAdapter;
import kr.ac.mju.hanmaeum.utils.Constants;
import kr.ac.mju.hanmaeum.utils.service.ShuttleService;
import kr.ac.mju.hanmaeum.utils.service.database.BookmarkDatabase;
import kr.ac.mju.hanmaeum.utils.service.database.DatabaseHelper;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Modified by Jinhyeon Park on 2017-02-02.
 */

public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.noticeListview)
    ListView noticeListview;

    private NoticeListAdapter noticeListAdapter = null;

    private OkHttpClient client;

    // Values for notice
    private GetNoticeTask getNoticeTask;
    private ArrayList<String> number;
    private ArrayList<String> title;
    private ArrayList<String> timestamp;
    private ArrayList<String> urlList;
    private int index = 0;

    // for checking number

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this, SplashActivity.class));// show splash

        setContentView(R.layout.activity_main); // get a layout
        ButterKnife.bind(this);

        this.setNavigationDrawer(savedInstanceState); // get navigation

        new TedPermission(this)
                .setPermissionListener(permissionListener)
                .setDeniedMessage("")
                .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION)
                .check();

        // get listView layout for notices and set Adapter to listView
        noticeListAdapter = new NoticeListAdapter();
        noticeListview.setAdapter(noticeListAdapter);

        // execute works of background to get notices from homepage
        getNoticeTask = new GetNoticeTask();
        getNoticeTask.execute();

        // Register eventListener of listView for click event
        noticeListview.setOnItemClickListener(this);

        //추가한 라인
        FirebaseMessaging.getInstance().subscribeToTopic("notice");
        FirebaseInstanceId.getInstance().getToken();

        BusAlarmThread busAlarmThread = new BusAlarmThread();
        busAlarmThread.start();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        /** If an item is clicked, it calls another activity for showing contents of notice */
        Intent intent = new Intent(getApplicationContext(), NoticeContent.class);
        intent.putExtra("URL", urlList.get(position));
        intent.putExtra("TITLE", title.get(position));
        intent.putExtra("TIMESTAMP", timestamp.get(position));

        startActivityForResult(intent, Constants.ACTIVITY_REQUEST_CODE);
    }

    // Change UI (add notices to ListView) using AsyncTask
    class GetNoticeTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            try {
                // connect url
                Document doc = Jsoup.connect(Constants.NOTICE_URL).get();

                number = new ArrayList<String>();
                title = new ArrayList<String>();
                timestamp = new ArrayList<String>();
                urlList = new ArrayList<String>();

                // parse doc type element
                Elements titleElement = doc.select(Constants.TITLE_ELEMENT);
                for (Element e : titleElement) {
                    title.add(String.valueOf(e.text()));
                }

                // parse doc type element
                Elements numberElement = doc.select(Constants.NUMBER_ELEMENT);
                for (Element e : numberElement) {
                    number.add(String.valueOf(e.text()));
                }

                // parse doc type element
                Elements timestampElement = doc.select(Constants.TIMESTAMP_ELEMENT);
                for (Element e : timestampElement) {
                    timestamp.add(String.valueOf(e.text()));
                }

                // parse doc type element
                Elements urlElement = doc.select(Constants.CHECK_URL_ELEMENT);
                for (Element e : urlElement) {
                    if (!e.text().equals("")) {
                        urlList.add(String.valueOf(e.attr(Constants.HREF_ELEMENT)));
                    }
                }

                // How many do we delete notices? (they don't have a number)
                for (int i = 0; i < number.size(); i++) {
                    if (!number.get(i).matches("^[0-9]+$")) {
                        index++;
                    }
                }

                // remove them. So We can get notices with number
                for (int i = 0; i < index; i++) {
                    title.remove(0);
                    number.remove(0);
                    timestamp.remove(0);
                    urlList.remove(0);
                }

                for (int i = 0; i < number.size(); i++) {
                    noticeListAdapter.addItem(number.get(i), title.get(i), timestamp.get(i), urlList.get(i));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("NUMBER!!!!!!", number.get(number.size() - 1));

            return number.get(number.size() - 1);
        }


        @Override
        protected void onPostExecute(String number) {
            super.onPostExecute(number);
            noticeListAdapter.notifyDataSetChanged();

            NotificationThread notificationThread = new NotificationThread();
            notificationThread.start();
        }
    }

    public class BusAlarmThread extends  Thread {
        boolean isRun = true;

        @Override
        public void run() {
            while(isRun) {
                if(ShuttleService.getShuttleServiceCheckList(getApplicationContext())) {
                    Log.d("CALL#########", "check");
                    sendBusAlarm();

                    try {
                        Thread.sleep(60000);
                    } catch (Exception e) {}
                }

                try {
                    Thread.sleep(15000);
                } catch (Exception e) {}
            }

        }
    }

    public class NotificationThread extends  Thread {
                    boolean isRun = true;
                    ArrayList<String> checkNumber;

                    public void run() {
                        try {
                            while(isRun) {
                                Document doc = Jsoup.connect(Constants.NOTICE_URL).get();
                                int index=0;

                                checkNumber = new ArrayList<String>();
                                Elements numberElement = doc.select(Constants.NUMBER_ELEMENT);

                                for (Element e : numberElement) {
                                    checkNumber.add(String.valueOf(e.text()));
                                }

                                // How many do we delete notices? (they don't have a number)
                                for (int i = 0; i < checkNumber.size(); i++) {
                                    if (!checkNumber.get(i).matches("^[0-9]+$")) {
                                        index++;
                                    }
                                }

                                // remove them. So We can get notices with number
                                for (int i = 0; i < index; i++) {
                                    checkNumber.remove(0);
                                }

                                //checkNumber.get(checkNumber.size() - 1);
                                String notiNumber = checkNumber.get(checkNumber.size() - 1);

                                // Save notice number
                                SharedPreferences mPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                String callValue = mPref.getString("number", "-1");

                                // if notice number does not exist, Save new number.
                                if (callValue.toString().equals("-1")) {
                                    SharedPreferences.Editor editor = mPref.edit();
                                    editor.putString("number", notiNumber);
                                    editor.commit();

                                    Log.d("TEST##########", callValue.toString());
                                }

                                callValue = mPref.getString("number", "-1");
                                Log.d("callValue@@@", callValue.toString());
                                Log.d("number@@@@", notiNumber.toString());

                                // if notice number does exist and differ from the parameter number, change notice number to parameter number.
                                if (!callValue.toString().equals(notiNumber.toString())) {
                                    Log.d("TEST@@@@@@@@@@@@@", callValue.toString() + " " + notiNumber.toString());
                                    SharedPreferences.Editor editor = mPref.edit();
                        editor.remove("number");
                        editor.commit();

                        editor.putString("number", notiNumber);
                        editor.commit();

                        sendNotification();
                    }
                    try {
                        Thread.sleep(60000);
                    } catch (Exception e) {}
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendNotification() {
        RequestBody body = new FormBody.Builder()
                .add("Message", "공지사항 확인")
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

    private void sendBusAlarm() {
        RequestBody body = new FormBody.Builder()
                .add("Message", "버스 출발 5분 전")
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



    PermissionListener permissionListener = new PermissionListener() {
        @Override public void onPermissionGranted() {
            Log.i(permissionListener.toString(), "Permission Granted");
        }

        @Override public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            Log.i(permissionListener.toString(), "Permission Denied");
        }
    };
}
