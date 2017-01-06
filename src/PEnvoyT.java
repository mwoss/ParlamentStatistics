import java.math.BigDecimal;
import java.util.LinkedList;

/**
 * Created by Agnieszka on 2016-12-30.
 */
public class PEnvoyT {
    public String country;
    public BigDecimal allCash;
    public int days;
    public Integer termOfOffice;

    public static LinkedList<PEnvoyT> returnRefactoredTrips(EnvoyData envoy){
        LinkedList<PEnvoyT> listPEnvoyT = new LinkedList<>();

        if(envoy.envoyTrips.tripsList == null){
            return listPEnvoyT;
        }
        else{
            for(SerializedDataTrips trips : envoy.envoyTrips.tripsList){
                PEnvoyT retData = new PEnvoyT();
                retData.country = trips.kraj;
                retData.allCash = new BigDecimal(trips.koszt_suma);
                retData.days = Integer.parseInt(trips.liczba_dni);
                if(envoy.serializedData.poslowie_kadencja.length == 1){
                    retData.termOfOffice = envoy.serializedData.poslowie_kadencja[0];
                }
                else{
                    if(Integer.parseInt(trips.doData.substring(0, 4)) <= 2015
                            && Integer.parseInt(trips.doData.substring(4, 6)) <= 10
                            && Integer.parseInt(trips.doData.substring(8)) < 25){

                        retData.termOfOffice = 7;
                    }
                    else{
                        retData.termOfOffice = 8;
                    }
                }
                listPEnvoyT.add(retData);
            }
        }
        return listPEnvoyT;
    }

    public int getDays(){
        return days;
    }

}
