package kr.ac.mju.hanmaeum.utils.service;

import android.os.Handler;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by user on 2017-02-07.
 */

public class ServiceThread extends  Thread {
    boolean isRun = true;
    private OkHttpClient client = new OkHttpClient();

    public ServiceThread() {

    }

    public void stopForever() {
        synchronized (this) {
            this.isRun = false;
        }
    }

    public void run() {
        while(isRun) {
            RequestBody body = new FormBody.Builder()
                    .add("Message", "공지사항 알림 확인")
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

            try {
                Thread.sleep(60000);
            } catch (Exception e) {}
        }
    }
}
