import java.util.LinkedList;

/**
 * Created by Matthew on 2016-12-16.
 */
public interface IOutputStatistics {

    void PrintExpensesFunction(Envoy envoy);
    void PrintSmallExpensesFunction(Envoy envoy);
    void PrintAvgExpensesFunction(int avgExpenses);
    void PrintTimeTrip(Envoy maxTimeTrip);
    void PrintMaxPriceTrip(Envoy maxTripPrice);
    void TripAmonut(Envoy maxTimeTrip);
    void PrintItalyTrip(LinkedList<Envoy> ItalyTrip);
}
