/**
 * Created by Matthew on 2016-12-15.
 */
public class StatisticsSystem {

    public static void main(String[] args){
        try{
            ArgParser argParser = new ArgParser(args); // Passing command line to checkFunction (consider static!)
            if(argParser.parserArguments()){
                final long startTime = System.currentTimeMillis();
                OutputStatistics outputData = new OutputStatistics(args);
                final long endtime = System.currentTimeMillis();
                System.out.println("Downloading files took "+(endtime-startTime)/1000.0 + " seconds");
                outputData.printAll();
            }
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
