/**
 * Created by Matthew on 2016-12-15.
 */
public class StatisticsSystem {

    public static void main(String[] args){
        try{
            ArgParser argParser = new ArgParser(args); // Passing command line to checkFunction (consider static!)
            if(argParser.parserArguments()){

            }

        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        // print list of envoys
        // user can chose one of them


    }
}
