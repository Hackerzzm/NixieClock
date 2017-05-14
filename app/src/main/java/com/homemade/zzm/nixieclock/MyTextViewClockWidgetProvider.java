package com.homemade.zzm.nixieclock;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

import com.orhanobut.logger.Logger;

public class MyTextViewClockWidgetProvider extends AppWidgetProvider {
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
//        Logger.d("MyTextViewClockWidgetProvider onReceive ");
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Logger.d("MyTextViewClockWidgetProvider onUpdate widget更新操作");
        //通过RemoteViews和AppWidgetManager更新
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Logger.d("MyTextViewClockWidgetProvider onDeleted widget从屏幕上移除");
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Logger.d("MyTextViewClockWidgetProvider onEnabled 第一个widget添加到屏幕上");
        context.startService(new Intent(context,TimerService.class));
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Logger.d("MyTextViewClockWidgetProvider onDisabled 所有widget都被删除了");
        context.stopService(new Intent(context,TimerService.class));
    }
}
