import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Created by Matthew on 2016-12-15.
 */
public class Statistics implements IStatistics {

    public LinkedList<PEnvoy> seventh;
    public LinkedList<PEnvoy> eighth;
    public LinkedList<PEnvoy> all;

    public Statistics(){
        JsonEnvoys readEnvoys = new JsonEnvoys();
        LinkedList<EnvoyData> EList = readEnvoys.readEnvoysFromJSON();

        this.seventh = EList.stream().map(PEnvoy::new)
                .filter(x -> Arrays.asList(x.termOfOffice).contains(7))
                .collect(Collectors.toCollection(LinkedList::new));

        this.eighth = EList.stream().map(PEnvoy::new)
                .filter(x -> Arrays.asList(x.termOfOffice).contains(8))
                .collect(Collectors.toCollection(LinkedList::new));


    }

    public PEnvoy returnEnvoy(String name, String lastname, Integer termOfOffice){
        return Stream.concat(seventh.stream(),eighth.stream())
                .collect(Collectors.toList())
                .stream() // TU MOZE BYC BLAD W TERMoo
                .filter(x -> x.name.equals(name) && x.lastname.equals(lastname) && Arrays.asList(x.termOfOffice).contains(termOfOffice))
                .findFirst()
                .orElseThrow( () -> new IllegalArgumentException("Wrong envoy's name. Check spelling") );
    }

    @Override
    public BigDecimal AvgExpensesFunction(Integer termOfOffice) {
        BigDecimal allCash = 
        return null;
    }

    @Override
    public PEnvoy TimeTrip(Integer termOfOffice) {
        return null;
    }

    @Override
    public PEnvoy MaxPriceTrip(Integer termOfOffice) {
        return null;
    }

    @Override
    public PEnvoy TripAmonut(Integer termOfOffice) {
        return null;
    }

    @Override
    public LinkedList<PEnvoy> ItalyTrip(Integer termOfOffice) {
        return null;
    }
}
