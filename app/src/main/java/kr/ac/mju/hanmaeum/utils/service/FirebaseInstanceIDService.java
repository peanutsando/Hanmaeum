package kr.ac.mju.hanmaeum.utils.service;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Jinhyeon Park on 2017-01-27.
 */

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = "MyFirebaseIIDService";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        // Get updated InstanceID token.
        String token = FirebaseInstanceId.getInstance().getToken();
    //    Log.d("TEST@@@@@@@@@@@@@@@@@@@@@@@@@@@@", "Refreshed token : " + token);

    //    sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("Token", token)
                .build();

        // request
        Request request = new Request.Builder()
                .url("http://서버주소/fcm/register.php")
                .post(body)
                .build();

        try {
            client.newCall(request).execute();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
