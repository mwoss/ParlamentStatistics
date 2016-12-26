import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Matthew on 2016-12-15.
 */
public class ArgParser {
    private static final Set<String> validOptions = new HashSet<>(Arrays.asList("expenses", "smallexpenses", "allexpenses",
                                                                                "tripamountmax", "triptimemax", "tripcostmax", "tripitaly"));
    // not have to implement romantodecimal, ther's only 2 possible Roman's number as arg
    /*
        Command line should have 2 arguments
        First Roman number VII or VIII
            True - check 2nd arg
            False - throw exception
        2nd number arg in range (1-7) or string arg like [lower or upper letters]
                1<=>"expenses", 2<=>"smallexpenses", 3<=>"allexpenses":
                4<=>"tripamountmax", 5<=>"triptimemax", 6<=>"tripcostmax"
                7<=>"tripitaly"
            False - throw exception

        Return True
     */
    private Actions procedure;
    private String[] parsArgs;
    public ArgParser(String[] args){
        parsArgs = args;
    }

    boolean parserArguments(){
        if(parsArgs.length != 2){
            throw new IllegalArgumentException("Not enough or too many arguments");
        }
        else {
            if(parsArgs[0].matches("VII|vii") || parsArgs[1].matches("VIII|viii")){
                if(parsArgs[1].matches("[1-7]") || validOptions.contains(parsArgs[1].toLowerCase()) ){
                    procedure = procedureEnum();
                }
                else{
                    throw new IllegalArgumentException("Cannot associate certain argument with action");
                }
            }
            else{
                throw new IllegalArgumentException("Wrong cadence");
            }
        }
        return true;
    }
    private Actions procedureEnum(){ // THIS IS SO NONOPTIMAL, I ALMOST VOMIT
        if(parsArgs[1].equals("1") || parsArgs[1].equals("expenses")) return Actions.Expanses;
        else if(parsArgs[1].equals("2") || parsArgs[1].equals("smallexpenses")) return Actions.SmallExpanses;
        else if(parsArgs[1].equals("3") || parsArgs[1].equals("allexpenses")) return  Actions.AllExpanses;
        else if(parsArgs[1].equals("4") || parsArgs[1].equals("tripamountmax")) return Actions.TripAmountMax;
        else if(parsArgs[1].equals("5") || parsArgs[1].equals("triptimemax")) return Actions.TripTimeMax;
        else if(parsArgs[1].equals("5") || parsArgs[1].equals("tripcostmax")) return Actions.TripCostMax;
        else return Actions.TripItaly;
    }

    public Actions getProcedure(){
        return procedure;
    }

}
