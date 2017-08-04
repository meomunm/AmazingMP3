package techkids.vn.zingmp3.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import techkids.vn.zingmp3.MainActivity;
import techkids.vn.zingmp3.R;
import techkids.vn.zingmp3.databases.TopSongModel;
import techkids.vn.zingmp3.managers.MusicManager;

/**
 * Created by ADMIN on 7/29/2017.
 */

public class PlayMusicNotification extends BroadcastReceiver{
    private static final String TAG = PlayMusicNotification.class.toString();

    public static NotificationCompat.Builder builder;
    public static RemoteViews remoteViews;
    public static NotificationManager notificationManager;

    public static void setupNotification(Context context, TopSongModel topSongModel) {
        //remote view
        remoteViews = new RemoteViews(context.getPackageName(), R.layout.notification_layout);
        remoteViews.setTextViewText(R.id.tv_song_noti, topSongModel.getName());

        Intent switchIntent = new Intent("com.example.app.ACTION_PLAY");       // xu ly su kien khi khi click vao imageview on notification
        PendingIntent pendingSwitchIntent = PendingIntent.getBroadcast(context, 100, switchIntent, 0);
        remoteViews.setOnClickPendingIntent(R.id.iv_song_noti, pendingSwitchIntent);

        //pending
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivities(context, 0, new Intent[]{intent}, PendingIntent.FLAG_UPDATE_CURRENT);

        Log.d(TAG, String.format("setupNotification:  %s", intent.getAction()));

        builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.drawable.ic_favorite_black_24dp) //icon nhỏ bên trên action bar
                .setContent(remoteViews)  //nội dung
                .setOngoing(true)  //true thi sẽ clear notification đc
                .setContentIntent(pendingIntent);   // nhiệm vụ

        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());

    }


    public static void updateNotification(boolean isPlaying) {
        if (isPlaying){
            remoteViews.setImageViewResource(R.id.iv_song_noti, R.drawable.exo_controls_pause);
        }else {
            remoteViews.setImageViewResource(R.id.iv_song_noti, R.drawable.exo_controls_play);
        }
        notificationManager.notify(0, builder.build());
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Toast.makeText(context,intent.getAction(),Toast.LENGTH_SHORT).show();
      if (action.equalsIgnoreCase("com.example.app.ACTION_PLAY")){
          Toast.makeText(context, "click play/pause on Notification", Toast.LENGTH_SHORT).show();
          MusicManager.playPause();
          updateNotification(MusicManager.hybridMediaPlayer.isPlaying());
          Toast.makeText(context, "update notification", Toast.LENGTH_SHORT).show();
      }
    }
}