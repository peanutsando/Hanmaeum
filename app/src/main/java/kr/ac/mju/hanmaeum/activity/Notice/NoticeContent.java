package kr.ac.mju.hanmaeum.activity.notice;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kr.ac.mju.hanmaeum.R;
import kr.ac.mju.hanmaeum.activity.BaseActivity;
import kr.ac.mju.hanmaeum.utils.Constants;

/**
 * Modified by Jinhyeon Park on 2017-01-23..
 */

public class NoticeContent extends BaseActivity {
    private String url, title, timestamp, content = "";
    private TextView timestampView, titleView, contentView, attachView;
    private GetContentTask getContentTask;
    private GetImageContentTask getImageContentTask;
    private ArrayList<String> imgList;
    private LinearLayout linearLayout;

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

        linearLayout = (LinearLayout) findViewById(R.id.content_Linear);
        imgList = new ArrayList<String>();

        // get title and timestamp from MainActivity.
        titleView.setText(Constants.NOTICE_TITLE + title);
        timestampView.setText(Constants.NOTICE_TIMESTAMP + timestamp);

        // operate getContentTask for crawling
        getContentTask = new GetContentTask();
        getContentTask.execute();

        getImageContentTask = new GetImageContentTask();
        getImageContentTask.execute();
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

                Element element = doc.select(Constants.DIVVIEW).first();
                Elements eChildren = element.children();
                for(Element e : eChildren) {
                    if(!e.text().equals("")) {
//                      e.text().replace("U+00A0", "<br />");
                        content = content + e.text() + Constants.BR;
                    }else {
                        content = content + Constants.BR;
                    }
                }
            } catch(IOException e) {
                e.printStackTrace();
            }

            return content;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            contentView.setText(Html.fromHtml(result));
        }
    }

    class GetImageContentTask extends  AsyncTask<Void, Void, ArrayList<String>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<String> doInBackground(Void... params) {
            try {
                Document doc = Jsoup.connect(url).timeout(0).get();

                Elements imgs = doc.select(Constants.DIVVIEW_IMG);
                if(!imgs.isEmpty()) {
                    for(Element img : imgs) {
                        imgList.add(img.attr(Constants.IMG_ATTR));
                    }
                }else {
                    imgs = doc.select(Constants.DIVVIEW_P_IMG);

                    for(Element img : imgs) {
                        imgList.add(img.attr(Constants.IMG_ATTR));
                    }
                }

            } catch(IOException e) {
                e.printStackTrace();
            }
            return imgList;
        }

        @Override
        protected void onPostExecute(ArrayList<String> imgList) {
            super.onPostExecute(imgList);

            for(int i=0; i< imgList.size(); i++) {
                ImageView imgView = new ImageView(NoticeContent.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT); // width, height
                imgView.setLayoutParams(lp);

                /* */
                Glide.with(NoticeContent.this)
                        .load(imgList.get(i))
                        .override(Constants.GLIDE_WIDTH, Constants.GLIDE_HEIGHT)
                        .into(imgView);
                linearLayout.addView(imgView);
            }
        }
    }
}
