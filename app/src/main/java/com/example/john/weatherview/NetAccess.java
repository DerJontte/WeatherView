package com.example.john.weatherview;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.os.StrictMode.*;
import android.widget.TextView;
import org.xml.sax.XMLFilter;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLFilterImpl;

import javax.xml.transform.Result;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* ************************************************************************************************************************* /
 * 	Denna klass kopplar upp sig mot en server och har en metod för att hämta data från den servern. Konstruktorn lagrar
 * 	serverns adress och en APIKey i klassvariabler, och med dessa plus den formaterade queryn hämtas data från servern.
 *
 */

public class NetAccess {
    private String server;
    private String query;

    public NetAccess(String server, String query) {
        this.server = server;
        this.query = query;
    }

    // getServerData tar en query-string som argument och gör en HTTP-request bestående av server, query och APIKey.
    // Metoden returnerar en String med datan från servern.
    public String getServerData() {
        final AtomicReference<String> toReturn = new AtomicReference<>("");
        final AtomicBoolean ready = new AtomicBoolean(false);

        new Thread(new Runnable() {
            public void run() {

                try {
                    URL url = new URL(server + query);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");

                    BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        toReturn.set(toReturn.get().concat(inputLine));
                    }
                    in.close();
                    ready.set(true);
                } catch (Exception e) {
                    toReturn.set(e.toString());
                }
            }
        }).start();


        while (!ready.get()) {}
        return toReturn.get();
    }
}