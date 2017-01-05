/**
 * Created by Matthew on 2016-12-15.
 */
public class OutputStatistics implements IOutputStatistics{

    private String firstname;
    private String lastname;
    private int term;
    private Statistics statistics;
    public OutputStatistics(String[] arg){
        term = Integer.parseInt(arg[0]);
        firstname = arg[1];
        lastname = arg[2];
        statistics = new Statistics();
    }
    @Override
    public void PrintExpensesFunction() {
        System.out.println("Envoy's expenses:");
        System.out.println(statistics
                .returnEnvoy(firstname,lastname,term)
                .allExpenses(term));
    }

    @Override
    public void PrintSmallExpensesFunction() {
        System.out.println("Envoy's (\"small\") expenses:");
        System.out.println(statistics
                .returnEnvoy(firstname,lastname,term)
                .smallExpenses(term));
    }

    @Override
    public void PrintAvgExpensesFunction() {
        System.out.println("Average expanses value of all envoys:");
        System.out.println(statistics.AvgExpensesFunction(term));
    }

    @Override
    public void PrintTimeTrip() {
        System.out.println("Envoys who was the longest time abroad:");
        PEnvoy ret = statistics.TimeTrip(term);
        System.out.println(ret.getName());

    }

    @Override
    public void PrintMaxPriceTrip() {
        System.out.println("Envoy with most expensive foreign trips:");
        PEnvoy ret = statistics.MaxPriceTrip(term);
        System.out.println(ret.getName());

    }

    @Override
    public void TripAmonut() {
        System.out.println("Envoy who took the most foreign trips:");
        PEnvoy ret = statistics.TripAmonut(term);
        System.out.println(ret.getName());

    }

    @Override
    public void PrintItalyTrip() {
        System.out.println("List of all envoys who visited Italy:");
        statistics.ItalyTrip(term).forEach(System.out::println);

    }
    public void printAll(){
        System.out.println("You've chosen envoy: "+ firstname + " " + lastname);
        this.PrintExpensesFunction();
        this.PrintSmallExpensesFunction();
        this.PrintAvgExpensesFunction();
        this.PrintTimeTrip();
        this.TripAmonut();
        this.PrintMaxPriceTrip();
        this.PrintItalyTrip();
    }

}
