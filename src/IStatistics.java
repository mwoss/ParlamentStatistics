import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Matthew on 2016-12-15.
 */
public interface IStatistics {
    BigDecimal ExpensesFunction(PEnvoy envoy);
    BigDecimal SmallExpensesFunction(PEnvoy envoy);
    BigDecimal AvgExpensesFunction(Integer termOfOffice);
    PEnvoy TimeTrip(Integer termOfOffice);
    PEnvoy MaxPriceTrip(Integer termOfOffice);
    PEnvoy TripAmonut(Integer termOfOffice);
    LinkedList<PEnvoy> ItalyTrip(Integer termOfOffice);




}
