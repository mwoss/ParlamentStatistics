import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Matthew on 2016-12-15.
 */
public class StatisticsSystem {

    public static void main(String[] args){
        try{
            ArgParser argParser = new ArgParser(args); // Passing command line to checkFunction (consider static!)
            if(argParser.parserArguments()){
                final long startTime = System.currentTimeMillis();
                System.out.println("File are included in programme. Do you want to make an update? Press \"y\" if yes, press any key if no.");
                System.out.println("This will probably take few minutes.");
                Scanner s= new Scanner(System.in);
                char x = s.next().charAt(0);
                if(x == 'y'){
                    System.out.println("Updating. Pleas wait.");
                    JsonEnvoys update = new JsonEnvoys();
                    update.update();
                    update.readEnvoysFromJSON();
                }
                OutputStatistics outputData = new OutputStatistics(args);
                final long endtime = System.currentTimeMillis();
                System.out.println("Calculations/downloading files took "+(endtime-startTime)/1000.0 + " seconds");
                outputData.printAll();
            }
        }
        catch (IllegalArgumentException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
