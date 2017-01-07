import java.math.BigDecimal;
import java.util.LinkedList;

/**
 * Created by Matthew on 2016-12-15.
 */
public interface IStatistics {

    BigDecimal AvgExpensesFunction(Integer termOfOffice);
    PEnvoy TimeTrip(Integer termOfOffice);
    PEnvoy MaxPriceTrip(Integer termOfOffice);
    PEnvoy TripAmonut(Integer termOfOffice);
    PEnvoy TimeTripAll(Integer termOfOffice);
    LinkedList<PEnvoy> ItalyTrip(Integer termOfOffice);




}
