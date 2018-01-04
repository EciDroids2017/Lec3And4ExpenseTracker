package temerbu.edu.expenses.http;

import android.support.annotation.NonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPHandler {

    public static String read(String address) throws IOException {
        URL url = new URL(address);

       // HttpsURLConnection -> HttpURLConnection -> URLConnection
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        //Binary inputStream
        InputStream in = con.getInputStream();
        return read(in); //ctrl + alt + m
    }

    /**
     * Read a binary input stream and translate it to a String
     * @param in A Binary InputStream
     * @return The String Translation of the InputString
     * @throws IOException Network Error
     */
    @NonNull
    public static String read(InputStream in) throws IOException {
        //wrap the binary input stream:
        //in a character stream:
        //Char By Char
        //utf-8
        InputStreamReader reader = new InputStreamReader(in);

        //Line By Line
        BufferedReader bufferedReader = new BufferedReader(reader);

        String line = null;

        //Strings in Java are immutable
        //StringBuilder is here to help
        StringBuilder builder = new StringBuilder();

        while ((line = bufferedReader.readLine()) != null) {
            builder.append(line); //Strings in java are immutable
        }

        //return a String
        return builder.toString();
    }
}




























