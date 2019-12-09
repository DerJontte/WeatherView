package com.example.john.weatherview;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Implementation of App Widget functionality.
 */
public class SimpleWeatherWidget extends AppWidgetProvider {

    static void updateAppWidget(final Context context, final AppWidgetManager appWidgetManager,
                                final int appWidgetId) {

        final AtomicReference<CharSequence> atomicText = new AtomicReference<CharSequence>(context.getString(R.string.appwidget_text));

        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String temp = null;
                    String time = null;
                    try {
                        while(true) {
                            temp = Parser.parse().second;
                            time = DateAndTime.getTime();
                            atomicText.set("Artukainen\n".concat(time).concat("\n").concat(temp).concat(" \u00b0C"));

                            // Construct the RemoteViews object
                            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.simple_weather_widget);
                            views.setTextViewText(R.id.appwidget_text, atomicText.get());
                            // Instruct the widget manager to update the widget
                            appWidgetManager.updateAppWidget(appWidgetId, views);
                            Thread.sleep(60000);
                        }
                    } catch (Exception e) {
                        atomicText.set("Error");
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (Exception e) {
            atomicText.set("Error");
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

