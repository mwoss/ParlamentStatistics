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

    LinkedList<EnvoyData> Envoys = new LinkedList<>();

    public LinkedList<EnvoyData> readEnvoysFromJSON(){

        HttpURLConnection request = JsonReader.openHttpURLConnection("https://api-v3.mojepanstwo.pl/dane/poslowie.json");

        JsonParser parser = new JsonParser();
        JsonElement parseTree = null;
        JsonElement next = null;
        Gson dataGson = new Gson();
        final long startTime = System.currentTimeMillis();

        while (request != null){
            try {
                parseTree = parser.parse(new InputStreamReader((InputStream) request.getContent()));
                JsonObject parseTreeObject = parseTree.getAsJsonObject();
                JsonArray praseTreeArray = parseTreeObject.getAsJsonArray("Dataobject");

                int testiter =0;

                for(JsonElement iter : praseTreeArray){

                    EnvoyData dataEnvoy = null;

                    try {
                        dataEnvoy = dataGson.fromJson(iter, EnvoyData.class);
                    }
                    catch (JsonParseException e){
                        System.err.println("Gson or json data error");
                    }
                    JsonElement elementSerializedData = iter.getAsJsonObject().get("data");

                    dataEnvoy.serializedData = dataGson.fromJson(elementSerializedData, SerializedDataEnvoy.class);

                    HttpURLConnection requestExpenses = JsonReader.openHttpURLConnection("https://api-v3.mojepanstwo.pl/dane/poslowie/" +
                            dataEnvoy.id +
                            ".json?layers[]=wydatki");
                    HttpURLConnection requestTrips = JsonReader.openHttpURLConnection("https://api-v3.mojepanstwo.pl/dane/poslowie/" +
                            dataEnvoy.id +
                            ".json?layers[]=wyjazdy");

                    // ZMIENIC!!!!!! .parse(new InputStreamReader((InputStream) requestExpenses.getContent()))

                    final long startTime1 = System.currentTimeMillis();
                    Object test = requestExpenses.getContent();
                    final long startTime2 = System.currentTimeMillis();
                    System.out.println((startTime2-startTime1)/1000.0);
                    JsonElement expenses = parser
                            .parse(new InputStreamReader((InputStream) requestExpenses.getContent()))
                            .getAsJsonObject()
                            .get("layers")
                            .getAsJsonObject()
                            .get("wydatki");

                    JsonElement trips = parser
                            .parse(new InputStreamReader((InputStream) requestTrips.getContent()))
                            .getAsJsonObject()
                            .get("layers")
                            .getAsJsonObject()
                            .get("wyjazdy");




                    JsonArray expensesPoints = expenses.getAsJsonObject().get("punkty").getAsJsonArray();
                    JsonArray expensesYears = expenses.getAsJsonObject().get("roczniki").getAsJsonArray();

                    ETrips envoyTripsret = new ETrips();
                    if(!trips.isJsonObject()){
                        LinkedList<SerializedDataTrips> tripsList = new LinkedList<>();
                        JsonArray tripsEnovy = trips.getAsJsonArray();

                        for (JsonElement iter2 : tripsEnovy)
                            tripsList.add(dataGson.fromJson(iter2, SerializedDataTrips.class));

                        envoyTripsret.tripsList = tripsList;
                    }
                    dataEnvoy.envoyTrips = envoyTripsret;
                    LinkedList<SerializedDataYears> yearsList = new LinkedList<>();
                    LinkedList<SerializedDataPoints> pointsList = new LinkedList<>();

                    for (JsonElement iter3 : expensesPoints)
                        pointsList.add(dataGson.fromJson(iter3, SerializedDataPoints.class));

                    for (JsonElement iter4 : expensesYears)
                        yearsList.add(dataGson.fromJson(iter4, SerializedDataYears.class));

                    dataEnvoy.envoyExpense = dataGson.fromJson(expenses, EExpenses.class);
                    dataEnvoy.envoyExpense.pointsList = pointsList;
                    dataEnvoy.envoyExpense.yearsList = yearsList;

                    Envoys.add(dataEnvoy);





                    testiter++; // TO USUNAC TO JEST TYLKO ZEBY TESTOWac NA MALEJ ILOSCI DANYCH
                    if(testiter == 5){
                        final long endtime = System.currentTimeMillis();
                        System.err.println((endtime-startTime)/1000.0);
                        return Envoys;
                    }

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
                request = null;
            }
        }
        return  Envoys;
    }
/*
    public EExpenses readEnvoysExpensesFromJSON(EnvoyData envoy){
        HttpURLConnection requestExpenses = JsonReader.openHttpURLConnection("https://api-v3.mojepanstwo.pl/dane/poslowie/" +
                                                                                    envoy.id +
                                                                                    ".json?layers[]=wydatki");
        JsonParser parser = new JsonParser();
        EExpenses envoyExpanes = new EExpenses();

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
            System.err.println("Problem with getting expenses content from Json");
        }
        return envoyExpanes;
    }

    public ETrips readEnvoyTripsFromJSON(EnvoyData envoy){
        HttpURLConnection requestTrips = JsonReader.openHttpURLConnection("https://api-v3.mojepanstwo.pl/dane/poslowie/" +
                                                                                envoy.id +
                                                                                ".json?layers[]=wyjazdy");
        JsonParser parser = new JsonParser();
        ETrips envoyTrips = new ETrips(); // CHECK THIS IF

        try{
            JsonElement trips = parser
                    .parse(new InputStreamReader((InputStream) requestTrips.getContent()))
                    .getAsJsonObject()
                    .get("layers")
                    .getAsJsonObject()
                    .get("wyjazdy");

            if(!trips.isJsonObject()){
                LinkedList<SerializedDataTrips> tripsList = new LinkedList<>();
                JsonArray tripsEnovy = trips.getAsJsonArray();
                Gson dataGson = new Gson();

                for (JsonElement iter : tripsEnovy)
                    tripsList.add(dataGson.fromJson(iter, SerializedDataTrips.class));

                envoyTrips.tripsList = tripsList;
            }

        }
        catch (IOException e){
            System.err.println("Problem with getting trip content from Json");
        }
        return envoyTrips;
    }
*/
}
