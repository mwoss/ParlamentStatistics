import com.google.gson.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;

/**
 * Created by Student30 on 2016-12-16.
 */
public class JsonEnvoys {

    LinkedList<EnvoyData> Envoys = new LinkedList<>();
    private boolean update;
    public JsonEnvoys(){
        this.update = false;
    }
    public LinkedList<EnvoyData> readEnvoysFromJSON(){
        try {
            File SDir = new File("./Sources");                            // creating directory for neccessery source files as Mp's list if doesn't exist yet
            if (!SDir.exists())
                SDir.mkdir();

            File SwDir = new File ("./Sources/DetailsExpenses");							// creating directory for detailed mp data
            if(!SwDir.exists())
                SwDir.mkdir();

            File SwyDir = new File ("./Sources/DetailsTrips");							// creating directory for detailed mp data
            if(!SwyDir.exists())
                SwyDir.mkdir();

            URL src = new URL("https://api-v3.mojepanstwo.pl/dane/poslowie.json");    // first envoy and default data values
            int i = 0;
            File mpList = new File("./Sources/MpList" + " " + i + ".json");
            if (!mpList.exists() || this.update)
                FileUtils.copyURLToFile(src, mpList);

            JsonParser parser = new JsonParser();
            JsonElement parseTree = null;
            JsonElement next = null;
            Gson dataGson = new Gson();
            JsonElement last = null;

            do {
                parseTree = parser.parse(new FileReader(mpList));
                JsonObject parseTreeObject = parseTree.getAsJsonObject();
                JsonArray praseTreeArray = parseTreeObject.getAsJsonArray("Dataobject");

                for (JsonElement iter : praseTreeArray) {
                    EnvoyData dataEnvoy = null;
                    try {
                        dataEnvoy = dataGson.fromJson(iter, EnvoyData.class);
                    } catch (JsonParseException e) {
                        System.err.println("Gson or json data error");
                    }
                    JsonElement elementSerializedData = iter.getAsJsonObject().get("data");

                    dataEnvoy.serializedData = dataGson.fromJson(elementSerializedData, SerializedDataEnvoy.class);


                    URL src2 = new URL("https://api-v3.mojepanstwo.pl/dane/poslowie/" + dataEnvoy.id + ".json?layers[]=wyjazdy");
                    File mpDetailsTrips = new File("./Sources/DetailsTrips/" + dataEnvoy.id + ".json");
                    if (!mpDetailsTrips.exists() || this.update)
                        FileUtils.copyURLToFile(src2, mpDetailsTrips);

                    URL src3 = new URL("https://api-v3.mojepanstwo.pl/dane/poslowie/" + dataEnvoy.id + ".json?layers[]=wydatki");
                    File mpDetailsExpenses = new File("./Sources/DetailsExpenses/" + dataEnvoy.id + ".json");
                    if (!mpDetailsExpenses.exists() || this.update)
                        FileUtils.copyURLToFile(src3, mpDetailsExpenses);

                    JsonElement expenses = parser
                            .parse(new FileReader(mpDetailsExpenses))
                            .getAsJsonObject()
                            .get("layers")
                            .getAsJsonObject()
                            .get("wydatki");

                    JsonElement trips = parser
                            .parse(new FileReader(mpDetailsTrips))
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
                }

                last = parseTree.getAsJsonObject().get("Links").getAsJsonObject().get("last");
                if (last != null) {
                    i++;
                    next = parseTree.getAsJsonObject().get("Links").getAsJsonObject().get("next");
                    src = new URL(next.getAsString());
                    mpList = new File("./Sources/MpList" + " " + i + ".json");
                    if (!mpList.exists() || this.update == true)
                        FileUtils.copyURLToFile(src, mpList);
                }

            } while (last != null);

        }
        catch (IOException e){
            System.out.println("Problem with getting content from Json");
        }
        return Envoys;
    }

    /*public LinkedList<EnvoyData> readEnvoysFromJSON(){

        HttpURLConnection request = JsonReader.openHttpURLConnection("https://api-v3.mojepanstwo.pl/dane/poslowie.json");

        JsonParser parser = new JsonParser();
        JsonElement parseTree = null;
        JsonElement next = null;
        Gson dataGson = new Gson();

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

                    // ZMIENIC!!!!!! .parse(new InputStreamReader((InputStream) requestExpenses.getContent())

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
    }*/
}
