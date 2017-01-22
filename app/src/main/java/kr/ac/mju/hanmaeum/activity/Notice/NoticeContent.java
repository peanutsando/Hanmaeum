package kr.ac.mju.hanmaeum.activity.notice;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
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
 * Modified by Jinhyeon Park on 2017-01-22..
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

                Element element = doc.select("#divView").first();
                Elements eChildren = element.children();
                for(Element e : eChildren) {
                    if(!e.text().equals("")) {
                        e.text().replace("U+00A0", "<br />");
                        content = content + e.text() + "<br />";
                        //       content.replace("&nbsp", "<br /");
                    }else {
                        content = content + "<br />";
                    }
                }

     /*           Element element = doc.select(Constants.DIV).first();
                if(element != null) {
                    element = doc.select("#divView > div > span").first();

                    if(element != null) {
                        Elements es = doc.select("#divView > div > span > p");

                        *//** divView > div > span > p *//*
                        for(Element e : es) {
                            content = content + e.text() + "<br />";
                        }
                    }
                }

                element = doc.select("#divView > div > span").first();
                if(element != null) {
                    Elements es = doc.select("#divView > div > span");

                    *//** divView > div > span > p *//*
                    for(Element e : es) {
                        content = content + e.text() + "<br />";
                    }
                }
*/


             /*   // div
                if(element != null) {
                    element = doc.select(Constants.DIV_SPAN).first();

                    // div > span
                    if(element != null) {
                        element = doc.select(Constants.DIV_SPAN_P).first();

                        // div > span > p
                        if(element != null) {
                            Elements es = doc.select(Constants.DIV_SPAN_P);

                            *//** divView > div > span > p *//*
                            for(Element e : es) {
                                content = content + e.text() + "<br />";
                            }

                            // div > span
                        } else {
                            Elements es = doc.select(Constants.DIV_SPAN);

                            *//** divView > div > span *//*
                            for(Element e : es) {
                                content = content + e.text() + "<br />";
                            }
                        }
                    }else {
                        Elements es = doc.select("#divView > div");

                        for(Element e : es) {
                            content = content + e.text() + "<br />";
                        }
                    }
                } else {
                    // div가 없다면
                    element = doc.select("#divView > span").first();

                    // span
                    if(element != null) {
                        element = doc.select("#divView > span > p").first();

                        if(element != null) {
                            Elements es = doc.select("#divView > span > p");

                            for(Element e : es) {
                                content = content + e.text() + "<br />";
                            }
                        }else {
                            *//** *//*
                            Elements es = doc.select("#divView > span");

                            for(Element e : es) {
                                content = content + e.text() + "<br />";
                            }
                        }
                    }else {
                        // div 와 span이 없기 때문에 p가 존재할 것.
                        Elements es = doc.select("#divView > p");

                        for(Element e : es) {
                            content = content + e.text() + "<br />";
                        }
                    }
                }*/
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
}
