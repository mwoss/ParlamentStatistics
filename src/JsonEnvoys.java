import com.google.gson.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.LinkedList;

/**
 * Created by Student30 on 2016-12-16.
 */
public class JsonEnvoys {

    public LinkedList<EnvoyData> readEnvoysFromJSON(){

        LinkedList<EnvoyData> Envoys = new LinkedList<>();
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
                    EnvoyData dataEnvoy = null;
                    try {
                        dataEnvoy = dataGson.fromJson(iter, EnvoyData.class);
                    }
                    catch (JsonParseException e){
                        System.err.println("Gson or json data error");
                    }
                    JsonElement elementSerializedData = iter.getAsJsonObject().get("data");
                    dataEnvoy.serializedData = dataGson.fromJson(elementSerializedData, SerializedDataEnvoy.class);

                    Envoys.add(dataEnvoy);
                    testiter++; // TO USUNAC TO JEST TYLKO ZEBY TESTOWac NA MALEJ ILOSCI DANYCH
                    if(testiter == 5)
                        return Envoys;
                }
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

    public EExpenses readEnvoysExpensesFromJSON(EnvoyData envoy){
        HttpURLConnection requestExpenses = JsonReader.openHttpURLConnection("https://api-v3.mojepanstwo.pl/dane/poslowie/" +
                                                                                    envoy.id +
                                                                                    ".json?layers[]=wydatki");
        JsonParser parser = new JsonParser();
        EExpenses envoyExpanes = null;

        try{
            JsonElement expenses = parser
                            .parse(new InputStreamReader((InputStream) requestExpenses.getContent()))
                            .getAsJsonObject()
                            .get("layers")
                            .getAsJsonObject()
                            .get("wydatki");


            JsonArray expensesPoints = expenses.getAsJsonObject().get("punkty").getAsJsonArray();
            JsonArray expensesYears = expenses.getAsJsonObject().get("roczniki").getAsJsonArray();

            LinkedList<SerializedDataYears> yearsList = new LinkedList<>();
            LinkedList<SerializedDataPoints> pointsList = new LinkedList<>();

            Gson dataGson = new Gson();

            for (JsonElement iter : expensesPoints)
                pointsList.add(dataGson.fromJson(iter, SerializedDataPoints.class));

            for (JsonElement iter2 : expensesYears)
                yearsList.add(dataGson.fromJson(iter2, SerializedDataYears.class));

            envoyExpanes = dataGson.fromJson(expenses, EExpenses.class);
            envoyExpanes.pointsList = pointsList;
            envoyExpanes.yearsList = yearsList;

        }
        catch (IOException e){
            System.err.println("Problem with getting content from Json");
        }
        return envoyExpanes;
    }

    public ETrips readEnvoyTripsFromJSON(EnvoyData envoy){
        HttpURLConnection requestTrips = JsonReader.openHttpURLConnection("https://api-v3.mojepanstwo.pl/dane/poslowie/" +
                                                                                envoy.id +
                                                                                ".json?layers[]=wyjazdy");
        JsonParser parser = new JsonParser();
        ETrips envoyTrips = null;

        try{
            JsonElement trips = parser
                    .parse(new InputStreamReader((InputStream) requestTrips.getContent()))
                    .getAsJsonObject()
                    .get("layers")
                    .getAsJsonObject()
                    .get("wyjazdy");

            if(trips.getAsString().length() != 0){
                LinkedList<SerializedDataTrips> tripsList = null;
                JsonArray tripsEnovy = trips.getAsJsonArray();
                Gson dataGson = new Gson();

                for (JsonElement iter : tripsEnovy)
                    tripsList.add(dataGson.fromJson(trips, SerializedDataTrips.class));

                envoyTrips.tripsList = tripsList;

            }
        }
        catch (IOException e){
            System.err.println("Problem with getting content from Json");
        }
        return envoyTrips;
    }

}
