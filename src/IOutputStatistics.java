import java.util.LinkedList;

/**
 * Created by Matthew on 2016-12-16.
 */
public interface IOutputStatistics {

    // Kinda unnecessay 1-2 lines methodes xD
    void PrintExpensesFunction(EnvoyData envoy);
    void PrintSmallExpensesFunction(EnvoyData envoy);
    void PrintAvgExpensesFunction(int avgExpenses);
    void PrintTimeTrip(EnvoyData maxTimeTrip);
    void PrintMaxPriceTrip(EnvoyData maxTripPrice);
    void TripAmonut(EnvoyData maxTimeTrip);
    void PrintItalyTrip(LinkedList<EnvoyData> ItalyTrip);
}
