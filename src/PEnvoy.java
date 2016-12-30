import java.math.BigDecimal;
import java.util.LinkedList;

/**
 * Created by Agnieszka on 2016-12-30.
 */
public class PEnvoy {
    public String name;
    public String secName;
    public String surname;
    public Integer[] termOfOffice;
    public LinkedList<PEnvoyE> expensesE;
    public LinkedList<PEnvoyT> tripsE;

    public PEnvoy(EnvoyData envoy){
        this.name = envoy.serializedData.poslowie_imie_pierwsze;
        this.secName = envoy.serializedData.poslowie_imie_drugie;
        this.surname = envoy.serializedData.poslowie_nazwisko;
        this.termOfOffice = envoy.serializedData.poslowie_kadencja;
        this.expensesE = PEnvoyE.returnRefactoredExpenses(envoy);
        this.tripsE = PEnvoyT.returnRefactoredTrips(envoy);
    }

    public BigDecimal allExpenses(Integer too){
        return this.expensesE.stream().filter(x -> x.getTermOfOffice().equals(too))
                .map(PEnvoyE::getCash)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    public  BigDecimal smallExpenses(Integer too){
        return this.expensesE.stream()
                .filter(x -> x.getTermOfOffice().equals(too) && x.fieldName.equals("Koszty drobnych napraw i remontów lokalu biura poselskiego"))
                .map(PEnvoyE::getCash)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    public String getName(){
        return this.name + " " + this.secName + " " + this.surname;
    }


}
