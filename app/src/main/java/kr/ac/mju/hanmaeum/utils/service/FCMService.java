package kr.ac.mju.hanmaeum.utils.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class FCMService extends Service {
    private ServiceThread thread;

    public FCMService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        thread = new ServiceThread();
        thread.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        thread.stopForever();
        thread = null;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
