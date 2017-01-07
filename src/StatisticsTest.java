import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by Matthew on 2017-01-06.
 */
public class StatisticsTest {
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void argExceptionTest1(){
        String term = "7";
        String firstname = "Lol";
        String lastname = "lolololtest";
        Statistics statistics = new Statistics();
        PEnvoy test = statistics.returnEnvoy(firstname,lastname,Integer.parseInt(term));
    }

    @Test
    public void getAvgTest(){
        Statistics statistics = new Statistics();
        BigDecimal test = statistics.AvgExpensesFunction(7);
        String val = "262663.02";
        BigDecimal test2 = new BigDecimal(val);
        assertEquals(test,test2);
    }
    @Test
    public void mostexpTest(){
        Statistics statistics = new Statistics();
        String name = "Adam";
        String last = "Szejnfeld";
        Integer term = 7;
        PEnvoy test = statistics.returnEnvoy(name,last,term);
        PEnvoy test2 = statistics.MaxPriceTrip(7);
        assertEquals(test,test2);
    }
    @Test
    public void maxamounttripTest(){
        Statistics statistics = new Statistics();
        String name = "Tadeusz";
        String last = "Iwiński";
        Integer term = 7;
        PEnvoy test = statistics.returnEnvoy(name,last,term);
        PEnvoy test2 = statistics.TripAmonut(7);
        assertEquals(test,test2);
    }
    @Test
    public void MaxtimeTest(){
        Statistics statistics = new Statistics();
        String name = "Killion";
        String last = "Munyama";
        Integer term = 7;
        PEnvoy test = statistics.returnEnvoy(name,last,term);
        PEnvoy test2 = statistics.TimeTrip(7);
        assertEquals(test,test2);
    }
    @Test
    public void ItalyTest(){
        Statistics statistics = new Statistics();
        assertTrue(statistics.ItalyTrip(7).size() == 50);
    }



}