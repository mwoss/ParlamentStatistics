import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Matthew on 2016-12-15.
 */
public interface IStatistics {
    int ExpensesFunction(EnvoyData envoy);
    int SmallExpensesFunction(EnvoyData envoy);
    Double AvgExpensesFunction(Map<String, EnvoyData> envoyMap);
    EnvoyData TimeTrip(Map<String, EnvoyData> envoyMap);
    EnvoyData MaxPriceTrip(Map<String, EnvoyData> envoyMap);
    EnvoyData TripAmonut(Map<String, EnvoyData> envoyMap);
    LinkedList<EnvoyData> ItalyTrip(Map<String, EnvoyData> envoyMap);




}
