import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.net.HttpURLConnection;
import java.util.LinkedList;

/**
 * Created by Student30 on 2016-12-16.
 */
public class JsonEnvoys {

    public LinkedList<Envoy> readEnvoysFromJSON(){
        LinkedList<Envoy> Envoys = new LinkedList<>();
        HttpURLConnection request = JsonReader.openHttpURLConnection("https://api-v3.mojepanstwo.pl/dane/poslowie.json");
        JsonParser parser = new JsonParser();

        while (request != null){

            // tu msi byc lapanie wyjatow nie wiem jeszcze lol

            // tu powinien byc kod skopiowany ze stacka

            // przerzucic jakos te dane to dabeli jest metoda jsontoarray
            // przeiterowac sie po kazdej dacie i wyciagac co potrzebne


            JsonElement next; // jakos sciagnac next linka

            if(next != null){
                request = JsonReader.openHttpURLConnection(next.getAsString());
            }
            else{
                request = null;
            }

        }
        return  Envoys;
    }

}
