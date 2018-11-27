package com.example.john.weatherview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/* ************************************************************************************************************************* /
 * 	Denna klass kopplar upp sig mot en server och har en metod för att hämta data från den servern. Konstruktorn lagrar
 * 	serverns adress och en APIKey i klassvariabler, och med dessa plus den formaterade queryn hämtas data från servern.
 *
 */

public class NetAccess {
    private String server;

    public NetAccess(String server) {
        this.server = server;
    }

    // getServerData tar en query-string som argument och gör en HTTP-request bestående av server, query och APIKey.
    // Metoden returnerar en String med datan från servern.
    public String getServerData(String query) throws IOException {
        String inputLine;
        String toReturn = new String();
        URL url = new URL(server + query);

        URLConnection wwwSession = url.openConnection();

        BufferedReader in = new BufferedReader(new InputStreamReader(wwwSession.getInputStream()));
        while ((inputLine = in.readLine()) != null) {
            toReturn = toReturn.concat(inputLine);
        }
        in.close();

        return toReturn;
    }
}