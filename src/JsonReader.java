import com.google.gson.JsonParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.LinkedList;

/**
 * Created by Matthew on 2016-12-15.
 */
public class JsonReader {

    public LinkedList<Envoy> readEnvoysFromJSON(){
        LinkedList<Envoy> Envoys = new LinkedList<>();
        HttpURLConnection request = JsonReader.openHttpURLConnection("https://api-v3.mojepanstwo.pl/dane/poslowie.json");
        JsonParser parser = new JsonParser();

        while (request != null){

            // przerzucic jakos te dane to dabeli jest metoda jsontoarray
            // przeiterowac sie po kazdej dacie i wyciagac co potrzebne



        }






        return  Envoys;
    }

    private static HttpURLConnection openHttpURLConnection(String str) {
        try {
            URL link = new URL(str);
            HttpURLConnection request = (HttpURLConnection) link.openConnection();
            request.connect();
            return request;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
