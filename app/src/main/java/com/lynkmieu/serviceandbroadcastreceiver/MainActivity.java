package com.lynkmieu.serviceandbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String mBroadcastAction = "STRING_BROADCAST_ACTION";
    private TextView mTextView;
    private IntentFilter mIntentFilter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.textview1);

        //Start MyService cùng với IntentFilter
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(mBroadcastAction);
        Intent serviceIntent = new Intent(this, MyService.class);
        startService(serviceIntent);
    }

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(mReceiver, mIntentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(mReceiver);
        super.onPause();
    }

    //Tạo một BroadcastReceiver lắng nghe service
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
          if (intent.getAction().equals(mBroadcastAction)) {
                mTextView.setText(mTextView.getText()
                        + intent.getStringExtra("Data").toString()
                        + "\n\n");
                Intent stopIntent = new Intent(MainActivity.this,
                        MyService.class);
                stopService(stopIntent);
            }
        }
    };


}
