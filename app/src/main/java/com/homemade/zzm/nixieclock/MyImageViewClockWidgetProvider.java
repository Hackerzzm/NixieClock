package com.homemade.zzm.nixieclock;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import com.orhanobut.logger.Logger;

/**
 * Created by zouzheming on 2017/5/18 15:39
 * 邮箱：920108874@qq.com
 * 手机：18352533507
 */
public class MyImageViewClockWidgetProvider extends AppWidgetProvider {
  @Override public void onReceive(Context context, Intent intent) {
    super.onReceive(context, intent);
    // Logger.d("MyImageViewClockWidgetProvider onReceive ");
  }

  @Override
  public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
    super.onUpdate(context, appWidgetManager, appWidgetIds);
    Logger.d("MyImageViewClockWidgetProvider onUpdate widget更新操作");
    //通过RemoteViews和AppWidgetManager更新
  }

  @Override public void onDeleted(Context context, int[] appWidgetIds) {
    super.onDeleted(context, appWidgetIds);
    Logger.d("MyImageViewClockWidgetProvider onDeleted widget从屏幕上移除");
  }

  @Override public void onEnabled(Context context) {
    super.onEnabled(context);
    Logger.d("MyImageViewClockWidgetProvider onEnabled 第一个widget添加到屏幕上");
    context.startService(new Intent(context, TimerService.class));
  }

  @Override public void onDisabled(Context context) {
    super.onDisabled(context);
    Logger.d("MyImageViewClockWidgetProvider onDisabled 所有widget都被删除了");
    context.stopService(new Intent(context, TimerService.class));
  }
}
