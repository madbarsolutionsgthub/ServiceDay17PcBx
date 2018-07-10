package com.example.mobileapp.servicepb5;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    MediaPlayer player;

    private static final String TAG = MyService.class.getSimpleName();

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "service created");
        player = MediaPlayer.create(this,R.raw.song);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "service destroyed");
        //player.stop();
        //player.release();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "started:");
        Log.e(TAG, "startId: "+startId);
        //player.start();
        //do heavy job

        //stopSelf();
        //stopSelf(3);
        sendBroadcast(new Intent(MainActivity.MyCustomReceiver.CUSTOM_ACTION).putExtra("msg","Good bye"));

        return START_REDELIVER_INTENT;
    }
}
