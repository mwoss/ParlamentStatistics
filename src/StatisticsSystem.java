import java.util.LinkedList;

/**
 * Created by Matthew on 2016-12-15.
 */
public class StatisticsSystem {

    public static void main(String[] args){
        try{
            ArgParser argParser = new ArgParser(args); // Passing command line to checkFunction (consider static!)
            if(argParser.parserArguments()){
                final long startTime = System.currentTimeMillis();
                JsonEnvoys readEnvoys = new JsonEnvoys();
                LinkedList<EnvoyData> testList = readEnvoys.readEnvoysFromJSON();
                System.out.println(testList.get(0).envoyTrips.tripsList.get(0).country_code);
                //Statistics statistics = new Statistics();
               // System.out.println(Statistics);
                final long endtime = System.currentTimeMillis();
                System.out.println("Downloading files takes "+(endtime-startTime)/1000.0 + " seconds");









            }

        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        // print list of envoys
        // user can chose one of them


    }
}
