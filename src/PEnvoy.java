import java.math.BigDecimal;
import java.util.LinkedList;

/**
 * Created by Agnieszka on 2016-12-30.
 */
public class PEnvoy {
    public String name;
    public String lastname;
    public Integer[] termOfOffice;
    public LinkedList<PEnvoyE> expensesE;
    public LinkedList<PEnvoyT> tripsE;

    public PEnvoy(EnvoyData envoy){
        this.name = envoy.serializedData.poslowie_imie_pierwsze;
        this.lastname = envoy.serializedData.poslowie_nazwisko;
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
        return this.name + " " + this.lastname;
    }
    @Override
    public String toString(){
        return this.name + " " + this.lastname;
    }

    public LinkedList<PEnvoyE> getExpensesE(){
        return expensesE;
    }
    public LinkedList<PEnvoyT> getTripsE(){
        return tripsE;
    }


}
