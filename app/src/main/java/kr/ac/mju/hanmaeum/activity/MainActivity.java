package kr.ac.mju.hanmaeum.activity;

import android.Manifest;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.ac.mju.hanmaeum.R;
import kr.ac.mju.hanmaeum.activity.notice.NoticeContent;
import kr.ac.mju.hanmaeum.activity.notice.NoticeItem;
import kr.ac.mju.hanmaeum.activity.notice.NoticeListAdapter;
import kr.ac.mju.hanmaeum.utils.Constants;
import kr.ac.mju.hanmaeum.utils.PreferenceManager;
import kr.ac.mju.hanmaeum.utils.service.database.BookmarkDatabase;

/**
 * Modified by Jinhyeon Park on 2017-02-02.
 */

public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.noticeListview)
    ListView noticeListview;

    private NoticeListAdapter noticeListAdapter = null;

    // Values for notice
    private GetNoticeTask getNoticeTask;
    private ArrayList<String> number;
    private ArrayList<String> title;
    private ArrayList<String> timestamp;
    private ArrayList<String> urlList;
    private int index = 0;


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

        Log.i("TAG", "" + PreferenceManager.getDatabase(this));

        if (!PreferenceManager.getDatabase(this)) {
            BookmarkDatabase database = new BookmarkDatabase();
            database.createDatabase(this);
        }


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
    class GetNoticeTask extends AsyncTask<Void, Void, List<NoticeItem>> {
        @Override
        protected List<NoticeItem> doInBackground(Void... voids) {
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
            return null;
        }


        @Override
        protected void onPostExecute(List<NoticeItem> noticeItems) {
            super.onPostExecute(noticeItems);
            noticeListAdapter.notifyDataSetChanged();
        }
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
