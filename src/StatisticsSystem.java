import com.google.gson.Gson;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Matthew on 2016-12-15.
 */
public class StatisticsSystem {

    public static void main(String[] args){
        try{
            ArgParser argParser = new ArgParser(args); // Passing command line to checkFunction (consider static!)
            if(argParser.parserArguments()){
                JsonEnvoys readEnvoys = new JsonEnvoys();
                LinkedList<Envoy> testList = readEnvoys.readEnvoysFromJSON();
                System.out.println(testList.get(0).id);






            }

        }
        catch (IllegalArgumentException  e) {
            System.out.println(e.getMessage());
        }
        // print list of envoys
        // user can chose one of them


    }
}
