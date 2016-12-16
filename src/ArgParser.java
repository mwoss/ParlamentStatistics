/**
 * Created by Matthew on 2016-12-15.
 */
public class ArgParser {
    // not have to implement romantodecimal, ther's only 2 possible Roman's number as arg
    /*
        Command line should have 2 arguments
        First Roman number VII or VIII
            True - check 2nd arg
            False - throw exception
        2nd number arg in range (1-7) or string arg like
                1<=>"expenses", 2<=>"smallexpenses", 3<=>"allexpenses":
                4<=>"tripquantitymax", 5<=>"triptimemax", 6<=>"tripcostmax"
                7<=>"tripitaly"
            False - throw exception

        Return True
     */
    private String[] parsArgs;
    public ArgParser(String[] args){
        parsArgs = args;
    }

    boolean parserArguments(String[] parsArgs){
        if(parsArgs.length != 2){
            throw new IllegalArgumentException("Not enough or too many arguments");
        }
        else {
            if(parsArgs[0].matches("VII|vii") || parsArgs[1].matches("VIII|viii")){
                if(parsArgs[1].matches("[1-7]") || parsArgs[1].matches("") ){

                }
                else{
                    throw new IllegalArgumentException("Cannot match certain argument to avtion");
                }
            }
            else{
                throw new IllegalArgumentException("Not correct cadence ");
            }
        }
        return true;
    }

}
