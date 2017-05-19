package com.homemade.zzm.nixieclock;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;
import com.orhanobut.logger.Logger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class TimerService extends Service {
    private Timer timer;
  //private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
  private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                updateViews();
            }
        }, 0, 1000);
    }

    /*private void updateViews() {
        String time_str = sdf.format(new Date());
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.textview_clock_widget);
        remoteViews.setTextViewText(R.id.clock_tv, time_str);
        AppWidgetManager manager = AppWidgetManager.getInstance(getApplicationContext());
        ComponentName cn = new ComponentName(getApplicationContext(), MyTextViewClockWidgetProvider.class);
        manager.updateAppWidget(cn, remoteViews);
    }*/

  private void updateViews() {
    String time_str = sdf.format(new Date());
    RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.imageview_clock_widget);
    remoteViews.setImageViewResource(R.id.position_1, getPictureResource(time_str.charAt(0)));
    remoteViews.setImageViewResource(R.id.position_2, getPictureResource(time_str.charAt(1)));
    remoteViews.setImageViewResource(R.id.position_3, getPictureResource(time_str.charAt(2)));
    remoteViews.setImageViewResource(R.id.position_4, getPictureResource(time_str.charAt(3)));
    remoteViews.setImageViewResource(R.id.position_5, getPictureResource(time_str.charAt(4)));
    remoteViews.setImageViewResource(R.id.position_6, getPictureResource(time_str.charAt(5)));
    remoteViews.setImageViewResource(R.id.position_7, getPictureResource(time_str.charAt(6)));
    remoteViews.setImageViewResource(R.id.position_8, getPictureResource(time_str.charAt(7)));

    AppWidgetManager manager = AppWidgetManager.getInstance(getApplicationContext());
    ComponentName cn =
        new ComponentName(getApplicationContext(), MyImageViewClockWidgetProvider.class);
    manager.updateAppWidget(cn, remoteViews);
    Logger.e("已经更新桌面时间");
  }

  private int getPictureResource(char ca) {
    String str = String.valueOf(ca);
    switch (str) {
      case "0":
        return R.mipmap.zero;
      case "1":
        return R.mipmap.one;
      case "2":
        return R.mipmap.two;
      case "3":
        return R.mipmap.three;
      case "4":
        return R.mipmap.four;
      case "5":
        return R.mipmap.five;
      case "6":
        return R.mipmap.six;
      case "7":
        return R.mipmap.seven;
      case "8":
        return R.mipmap.eight;
      case "9":
        return R.mipmap.nine;
      case ":":
        return R.mipmap.colon;
      case ".":
        return R.mipmap.point;
      default:
        return R.mipmap.ic_launcher;
    }
  }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer = null;
    }
}
