import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Matthew on 2016-12-16.
 */
public class ParlamentEnvoys {
    // consider using list instead of map
    Map<String, EnvoyData> envoysMap = new HashMap<>();
    LinkedList<EnvoyData> ItalyTrip;
    public int avgExpenses;
    public EnvoyData maxAmountTrips;
    public EnvoyData maxTimeTrip;
    public EnvoyData maxTripPrice;

    // Trzymac to jako plik txt czy pobierac za kazdym razym ze strony?

    public int getEnvoyID(){
        // Iter over map and return envoy.id
        return 0;
    }

}
