import com.google.gson.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Scanner;

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
            File SDir = new File("./Sources");
            if (!SDir.exists())
                SDir.mkdir();
            else {
                System.out.println("File already exists. Do you want to make an update? Press \"y\" if yes, press any key if no.");
                Scanner s= new Scanner(System.in);
                char x = s.next().charAt(0);
                if(x == 'y'){
                    System.out.println("Updating. Pleas wait.");
                    this.update();
                }
            }


            File SwDir = new File ("./Sources/DetailsExpenses");
            if(!SwDir.exists())
                SwDir.mkdir();

            File SwyDir = new File ("./Sources/DetailsTrips");
            if(!SwyDir.exists())
                SwyDir.mkdir();

            URL src = new URL("https://api-v3.mojepanstwo.pl/dane/poslowie.json");
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

    public void update()throws IOException{
        this.update = true;
    }
}
