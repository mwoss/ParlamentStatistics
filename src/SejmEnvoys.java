import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Matthew on 2016-12-16.
 */
public class SejmEnvoys {
    // consider using list instead of map
    Map<String,Envoy> envoysMap = new HashMap<>();
    LinkedList<Envoy> ItalyTrip = new LinkedList<>();
    public int avgExpenses;
    public Envoy maxAmountTrips;
    public Envoy maxTimeTrip;
    public Envoy maxTripPrice;

    // TODO Add objects helping interact with others classes
    // Trzymac to jako plik txt czy pobierac za kazdym razym ze strony?

    public int getEnvoyID(){
        // Iter over map and return envoy.id
        return 0;
    }

}
