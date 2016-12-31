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
        Stream<PEnvoy> temp;
       if(termOfOffice == 7)
           temp = seventh.stream();
       else
           temp = eighth.stream();

       BigDecimal amount = new BigDecimal(460);
       BigDecimal allCash = Stream.concat(temp,Stream.empty()) // flatmap aby sciasnac do jednego poziomu wartosci z roznych tytulow
               .flatMap(x -> x.getExpensesE().stream())
               .map(PEnvoyE::getCash)
               .collect(Collectors.toList())
               .stream()
               .reduce(BigDecimal.ZERO,BigDecimal::add);

       return allCash.divide(amount,BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public PEnvoy TimeTrip(Integer termOfOffice) {
        Stream<PEnvoy> temp;
        if(termOfOffice == 7)
            temp = seventh.stream();
        else
            temp = eighth.stream();

        return null;
    }

    @Override
    public PEnvoy MaxPriceTrip(Integer termOfOffice) {
        Stream<PEnvoy> temp;
        if(termOfOffice == 7)
            temp = seventh.stream();
        else
            temp = eighth.stream();
        return null;
    }

    @Override
    public PEnvoy TripAmonut(Integer termOfOffice) {
        Stream<PEnvoy> temp;
        if(termOfOffice == 7)
            temp = seventh.stream();
        else
            temp = eighth.stream();

        return Stream.concat(temp,Stream.empty())
                .collect(Collectors.toList())
                .stream()
                .sorted( (x,y) -> Integer.compare(x.tripsE.size(), y.tripsE.size())) // check if intelij has right
                .findFirst()
                .orElseThrow( () -> new IllegalArgumentException("Something goes wrong") );
    }

    @Override
    public LinkedList<PEnvoy> ItalyTrip(Integer termOfOffice) {
        Stream<PEnvoy> temp;
        if(termOfOffice == 7)
            temp = seventh.stream();
        else
            temp = eighth.stream();

        return Stream.concat(temp,Stream.empty())
                .filter(x -> x.getTripsE().stream().anyMatch(f -> f.country.equals("Włochy")))
                .collect(Collectors.toCollection(LinkedList::new));
    }
}
