import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.assertEquals;

/**
 * Created by Matthew on 2017-01-06.
 */
public class PEnvoyTest {

    @Test
    public void EnvoyExpTest(){
        Statistics statistics = new Statistics();
        String term = "7";
        String firstname = "Maks";
        String lastname = "Kraczkowski";
        BigDecimal test = statistics
                .returnEnvoy(firstname,lastname,Integer.parseInt(term))
                .allExpenses(Integer.parseInt(term));
        test = test.setScale(2, BigDecimal.ROUND_HALF_UP);
        String val = "291868.23";
        BigDecimal test2 = new BigDecimal(val);
        assertEquals(test,test2);
    }

    @Test
    public void EnvoySmallExpText(){
        Statistics statistics = new Statistics();
        String term = "7";
        String firstname = "Maks";
        String lastname = "Kraczkowski";
        BigDecimal test = statistics
                .returnEnvoy(firstname,lastname,Integer.parseInt(term))
                .smallExpenses(Integer.parseInt(term));
        test = test.setScale(2, BigDecimal.ROUND_HALF_UP);
        String val = "0.00";
        BigDecimal test2 = new BigDecimal(val);
        assertEquals(test,test2);
    }

}