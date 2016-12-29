import com.google.gson.*;
import jdk.nashorn.internal.runtime.JSONFunctions;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        JsonElement parseTree = null;
        JsonElement next = null;

        while (request != null){
            try {
                parseTree = parser.parse(new InputStreamReader((InputStream) request.getContent()));
                JsonObject parseTreeObject = parseTree.getAsJsonObject();
                JsonArray praseTreeArray = parseTreeObject.getAsJsonArray("Dataobject");

                int testiter =0;

                for(JsonElement iter : praseTreeArray){

                    Gson dataGson = new Gson();
                    Envoy dataEnvoy = null;
                    try {
                        dataEnvoy = dataGson.fromJson(iter, Envoy.class);
                    }
                    catch (JsonParseException e){
                        System.err.println("Gson or json data error");
                    }
                    JsonElement elementSerializedData = iter.getAsJsonObject().get("data");
                    dataEnvoy.serializedData = dataGson.fromJson(elementSerializedData, EnvoySerializedData.class);

                    Envoys.add(dataEnvoy);
                    //testiter++;
                    if(testiter == 5)
                        return Envoys;
                }


                // tu powinien byc kod skopiowany ze stacka

                // przerzucic jakos te dane to dabeli jest metoda jsontoarray
                // przeiterowac sie po kazdej dacie i wyciagac co potrzebne
            }
            catch(IOException e){
                System.err.println("Problem with getting content from Json");
            }
            JsonElement last = parseTree.getAsJsonObject().get("Links").getAsJsonObject().get("last");
            if(last != null){
                next = parseTree.getAsJsonObject().get("Links").getAsJsonObject().get("next");
                request = JsonReader.openHttpURLConnection(next.getAsString());

            }
            else{
                next = null;
                request = null;
            }
        }
        return  Envoys;
    }

}
