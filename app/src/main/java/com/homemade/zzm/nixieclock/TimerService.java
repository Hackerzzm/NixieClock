package com.homemade.zzm.nixieclock;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class TimerService extends Service {
    private Timer timer;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);

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

    private void updateViews() {
        String time_str = sdf.format(new Date());
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.textview_clock_widget);
        remoteViews.setTextViewText(R.id.clock_tv, time_str);
        AppWidgetManager manager = AppWidgetManager.getInstance(getApplicationContext());
        ComponentName cn = new ComponentName(getApplicationContext(), MyTextViewClockWidgetProvider.class);
        manager.updateAppWidget(cn, remoteViews);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer = null;
    }
}
