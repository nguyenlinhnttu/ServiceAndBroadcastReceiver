package com.lynkmieu.serviceandbroadcastreceiver;

import android.annotation.TargetApi;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by LynkMieu on 6/11/2017.
 */

public class MyService extends Service {
    private String LOG_TAG = "MyService";
    private String myString;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(LOG_TAG, "onCreate MyService");
        myString= "WWW.ANDROIDCOBAN.COM";
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(LOG_TAG, "onStartCommand MyService");
        //Tạo một Thread sau 2 giây gửi data đến Broadcast cùng với intent tương ứng.
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent broadcastIntent = new Intent();
                broadcastIntent.setAction(MainActivity.mBroadcastAction);
                broadcastIntent.putExtra("Data", myString);
                sendBroadcast(broadcastIntent);
            }
        }).start();
        //flag này có tác dụng khi android bị kill hoặc bộ nhớ thấp, hệ thống sẽ start lại và gửi kết quả lần nữa.
        return START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(LOG_TAG, "In onBind");
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "In onDestroy");
    }
}
