import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by Matthew on 2016-12-15.
 */
public class JsonReader {

    public static HttpURLConnection openHttpURLConnection(String str) {
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
