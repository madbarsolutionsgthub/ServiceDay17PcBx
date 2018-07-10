package com.example.mobileapp.servicepb5;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class MyIntentService extends IntentService {


    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //runs on background thread
        String msg = intent.getStringExtra("msg");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendNotification(msg);
    }

    private void sendNotification(String msg) {
        String channelID = "my_channel";
        String name = "Songs";
        String description = "some descriptionsss";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        Intent notificationIntent = new Intent(this,MainActivity.class);
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
        taskStackBuilder.addNextIntentWithParentStack(notificationIntent);
        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(555,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,channelID);
        builder.setSmallIcon(R.drawable.ic_notification);
        builder.setContentTitle(msg);
        builder.setContentText("Click here to download this song");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelID,name,importance);
            channel.setDescription(description);
            manager.createNotificationChannel(channel);
        }

        manager.notify(555,builder.build());

    }


}
