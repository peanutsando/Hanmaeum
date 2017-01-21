package kr.ac.mju.hanmaeum.activity.notice;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import kr.ac.mju.hanmaeum.R;
import kr.ac.mju.hanmaeum.activity.BaseActivity;
import kr.ac.mju.hanmaeum.utils.Constants;

/**
 * Modified by Jinhyeon Park on 2017-01-21.
 */

public class NoticeContent extends BaseActivity {
    private String url, title, timestamp, content = "";
    private TextView timestampView, titleView, contentView, attachView;
    private GetContentTask getContentTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_content);

        // get intent from MainActivity.
        Intent intent = getIntent();
        url = intent.getStringExtra("URL");
        title = intent.getStringExtra("TITLE");
        timestamp = intent.getStringExtra("TIMESTAMP");

        // Set layout contents and I will change this using ButterKnife and detach them to initFunction.
        timestampView = (TextView) findViewById(R.id.contentTimestamp);
        titleView = (TextView) findViewById(R.id.contentTitle);
        contentView = (TextView) findViewById(R.id.content);
        attachView = (TextView) findViewById(R.id.attachFile);

        // get title and timestamp from MainActivity.
        titleView.setText(Constants.NOTICE_TITLE + title);
        timestampView.setText(Constants.NOTICE_TIMESTAMP + timestamp);

        // operate getContentTask for crawling
        getContentTask = new GetContentTask();
        getContentTask.execute();
    }

    class GetContentTask extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                Document doc = Jsoup.connect(url).get();

            } catch(IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}
