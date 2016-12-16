import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Matthew on 2016-12-15.
 */
public interface IStatistics {
    int ExpensesFunction(Envoy envoy);
    int SmallExpensesFunction(Envoy envoy);
    int AvgExpensesFunction(Map<String,Envoy> envoyMap);
    Envoy TimeTrip(Map<String,Envoy> envoyMap);
    Envoy MaxPriceTrip(Map<String,Envoy> envoyMap);
    Envoy TripAmonut(Map<String,Envoy> envoyMap);
    LinkedList<Envoy> ItalyTrip(Map<String,Envoy> envoyMap);




}
