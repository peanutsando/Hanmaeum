package kr.ac.mju.hanmaeum.activity;

import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;
import kr.ac.mju.hanmaeum.R;
import kr.ac.mju.hanmaeum.activity.Notice.NoticeItem;
import kr.ac.mju.hanmaeum.activity.Notice.NoticeListAdapter;
import kr.ac.mju.hanmaeum.utils.Constants;

public class MainActivity extends BaseActivity {
    private ListView noticeListview;
    private NoticeListAdapter noticeListAdapter = null;
    private ArrayList<NoticeItem> itemList = new ArrayList<>();

    // Values for notice
    private GetNoticeTask getNoticeTask;
    private ArrayList<String> number;
    private ArrayList<String> title;
    private ArrayList<String> timestamp;
    private ArrayList<String> urlList;
    private int index=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setNavigationDrawer(savedInstanceState);

        noticeListview = (ListView) findViewById(R.id.noticeListview);

        noticeListAdapter = new NoticeListAdapter();
        noticeListview.setAdapter(noticeListAdapter);

        getNoticeTask = new GetNoticeTask();
        getNoticeTask.execute();
    }

    class GetNoticeTask extends AsyncTask<Void, Void, List<NoticeItem>> {
        @Override
        protected List<NoticeItem> doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect(Constants.NOTICE_URL).get();

                Log.d("Test", "@@@@@@@@@@@@@@@@@@@@");

                number = new ArrayList<String>();
                title = new ArrayList<String>();
                timestamp = new ArrayList<String>();
                urlList = new ArrayList<String>();

                Elements titleElement = doc.select(Constants.TITLE_ELEMENT);
                for (Element e : titleElement) {
                    title.add(String.valueOf(e.text()));
                }

                Elements numberElement = doc.select(Constants.NUMBER_ELEMENT);
                for (Element e : numberElement) {
                    number.add(String.valueOf(e.text()));
                }

                Elements timestampElement = doc.select(Constants.TIMESTAMP_ELEMENT);

                for (Element e : timestampElement) {
                    timestamp.add(String.valueOf(e.text()));
                }

                Elements urlElement = doc.select(Constants.CHECK_URL_ELEMENT);
                for (Element e : urlElement) {
                    if (!e.text().equals("")) {
                        urlList.add(String.valueOf(e.attr(Constants.HREF_ELEMENT)));
                    }
                }

                for (int i=0; i < number.size(); i++) {
                    if(!number.get(i).equals("")) {
                        index++;
                    }
                }

                for(int i=index; i < number.size(); i++) {
                    noticeListAdapter.addItem(number.get(i), title.get(i), timestamp.get(i));
  /*                  Log.d("TEST", number.get(i));
                    Log.d("TEST", title.get(i));
                    Log.d("TEST", timestamp.get(i));*/
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
}
