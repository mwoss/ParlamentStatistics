import java.math.BigDecimal;
import java.util.LinkedList;

/**
 * Created by Agnieszka on 2016-12-30.
 */
public class PEnvoyE {
    public String fieldName;
    public BigDecimal cash;
    public int year;
    public Integer termOfOffice;

    public static LinkedList<PEnvoyE> returnRefactoredExpenses(EnvoyData envoy){
        LinkedList<PEnvoyE> listPEnvoyE = new LinkedList<>();

        for(SerializedDataYears years : envoy.envoyExpense.yearsList)
            for(SerializedDataPoints points : envoy.envoyExpense.pointsList){
                PEnvoyE retData = new PEnvoyE();
                retData.fieldName = points.title;
                retData.cash = years.pola[points.number-1];
                retData.year = years.rok;
                if(envoy.serializedData.poslowie_kadencja.length == 1){
                    retData.termOfOffice = envoy.serializedData.poslowie_kadencja[0];
                }
                else{
                    if(years.rok <= 2015 ){
                        retData.termOfOffice = 7;
                    }
                    else{
                        retData.termOfOffice = 8;
                    }
                }
                listPEnvoyE.add(retData);

            }
        return listPEnvoyE;
    }
}
