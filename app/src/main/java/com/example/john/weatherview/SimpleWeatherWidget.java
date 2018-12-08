package com.example.john.weatherview;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;
import android.widget.TextView;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Implementation of App Widget functionality.
 */
public class SimpleWeatherWidget extends AppWidgetProvider {

    static void updateAppWidget(final Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        final AtomicReference<CharSequence> atomicText = new AtomicReference<>();
        final AtomicBoolean ready = new AtomicBoolean(false);

        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String temp = null;
                    try {
                        temp = Parser.parse().second;
                        atomicText.set("Artukainen\n" + temp + " \u00b0C");
                        ready.set(true);
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
        while(!ready.get()) {

        }
        widgetText = (CharSequence) atomicText.get();
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.simple_weather_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
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

