import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Matthew on 2016-12-15.
 */
public class Statistics implements IStatistics {
    @Override
    public int ExpensesFunction(EnvoyData envoy) {
        return 0;
    }

    @Override
    public int SmallExpensesFunction(EnvoyData envoy) {
        return 0;
    }

    @Override
    public Double AvgExpensesFunction(Map<String, EnvoyData> envoyMap) {
        return 0.0;
    }

    @Override
    public EnvoyData TimeTrip(Map<String, EnvoyData> envoyMap) {
        return null;
    }

    @Override
    public EnvoyData MaxPriceTrip(Map<String, EnvoyData> envoyMap) {
        return null;
    }

    @Override
    public EnvoyData TripAmonut(Map<String, EnvoyData> envoyMap) {
        return null;
    }

    @Override
    public LinkedList<EnvoyData> ItalyTrip(Map<String, EnvoyData> envoyMap) {
        return null;
    }
}
