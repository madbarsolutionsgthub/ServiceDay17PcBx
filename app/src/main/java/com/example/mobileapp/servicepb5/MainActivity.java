package com.example.mobileapp.servicepb5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MyCustomReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        receiver = new MyCustomReceiver();
        registerReceiver(receiver,new IntentFilter(MyCustomReceiver.CUSTOM_ACTION));
    }

    public void startStartedService(View view) {
        startService(new Intent(MainActivity.this,MyService.class));
    }

    public void startIntentService(View view) {
        startService(new Intent(MainActivity.this,MyIntentService.class).putExtra("msg","Hello from the other world"));
    }

    public class MyCustomReceiver extends BroadcastReceiver{
        public static final String CUSTOM_ACTION = "CUSTOM_ACTION";
        @Override
        public void onReceive(Context context, Intent intent) {
            String msg = intent.getStringExtra("msg");
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    }
}
