package kr.ac.mju.hanmaeum.activity.notice;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import kr.ac.mju.hanmaeum.R;
import kr.ac.mju.hanmaeum.activity.BaseActivity;
import kr.ac.mju.hanmaeum.activity.SubActivity;
import kr.ac.mju.hanmaeum.utils.Constants;
import android.support.v7.app.ActionBar;

/**
 * Modified by Jinhyeon Park on 2017-01-25.
 */

public class NoticeContent extends BaseActivity {
    private String url, title, timestamp, content = "";
/*
    @BindView(R.id.contentTimestamp)
    TextView timestampView;

    @BindView(R.id.contentTitle)
    TextView titleView;

    @BindView(R.id.attachFile)
    TextView attachView;

    @BindView(R.id.content_Linear)
    LinearLayout linearLayout;*/

    private TextView timestampView, titleView, attachView;
    private GetAttachFileTask getAttachFileTask;
    private GetContentTask getContentTask;
    private GetImageContentTask getImageContentTask;
    private ArrayList<String> imgList, attachList, attachUrlList;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_content);

        Init();
        RegisterBasicInfo();

        // operate getContentTask for crawling
        getImageContentTask = new GetImageContentTask();
        getImageContentTask.execute();

        getAttachFileTask = new GetAttachFileTask();
        getAttachFileTask.execute();
    }

    private void Init() {
        // get intent from MainActivity.
        Intent getIntent = getIntent();
        url = getIntent.getStringExtra(Constants.URL);
        title = getIntent.getStringExtra(Constants.TITLE);
        timestamp = getIntent.getStringExtra(Constants.TIMESTAMP);

        imgList = new ArrayList<String>();
        attachList = new ArrayList<String>();
        attachUrlList = new ArrayList<String>();

        timestampView = (TextView) findViewById(R.id.contentTimestamp);
        titleView = (TextView) findViewById(R.id.contentTitle);
        attachView = (TextView) findViewById(R.id.attachFile);
        linearLayout = (LinearLayout) findViewById(R.id.content_Linear);
    }

    private void RegisterBasicInfo() {
        // get title and timestamp and then set it on noticeContent.
        titleView.setText(Constants.NOTICE_TITLE + title);
        timestampView.setText(Constants.NOTICE_TIMESTAMP + timestamp);
    }

    class GetAttachFileTask extends AsyncTask<Void, Void, ArrayList<String>> {
        @Override
        protected void onPreExecute() { super.onPreExecute(); }

        @Override
        protected ArrayList<String> doInBackground(Void... params) {
            try {
                Document doc = Jsoup.connect(url).get();

                Elements attachFiles = doc.select("td > a > img");
                if(!attachFiles.isEmpty()) {
                    for(Element attachFile : attachFiles) {
                        attachList.add(attachFile.parent().text());
                        attachUrlList.add(attachFile.parent().attr("href").toString());

                       // Log.e("TEST@@@@@@@@@@@@@@@@@@@@@", attachFile.parent().text());
                    }
                } else {
                    attachList.add("첨부파일이 없습니다.");
                }

            } catch(IOException e) {
                e.printStackTrace();
            }

            return attachList;
        }

        @Override
        protected void onPostExecute(ArrayList<String> attList) {
            super.onPostExecute(attList);

            for(int i=0; i < attList.size(); i++) {
                attachView.setText(Html.fromHtml("<a href="+ attachUrlList.get(i) + ">" + attachView.getText() + attList.get(i).toString() + "<br /"));
            }
        }
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

            final TextView textView = new TextView(NoticeContent.this);
            textView.setText(Html.fromHtml(result));
            linearLayout.addView(textView);
        }
    }

    class GetImageContentTask extends  AsyncTask<Void, Void, ArrayList<String>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        /* Get url src from homepage and insert that into imgs (ArrayString) */
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
        /* imgList has arrayString about img url src */
        protected void onPostExecute(ArrayList<String> imgList) {
            super.onPostExecute(imgList);

            for(int i=0; i< imgList.size(); i++) {
                // create ImageView dynamically
                ImageView imgView = new ImageView(NoticeContent.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT); // width, height
                imgView.setLayoutParams(lp);

                /* draw images in imgView */
                Glide.with(NoticeContent.this)
                        .load(imgList.get(i))
                        .override(Constants.GLIDE_WIDTH, Constants.GLIDE_HEIGHT)
                        .into(imgView);

                // add imgView ti linearLayout
                linearLayout.addView(imgView);
            }

            // get contents after getting images
            getContentTask = new GetContentTask();
            getContentTask.execute();
        }
    }
}
