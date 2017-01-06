/**
 * Created by Matthew on 2016-12-15.
 */
public class ArgParser {

    private String[] parsArgs;

    public ArgParser(String[] args) {
        parsArgs = args;
    }

    boolean parserArguments() {
        if (parsArgs.length != 3) {
            throw new IllegalArgumentException("Not enough or too many arguments. Command line should looks like: Cadence FristName LastName");
        } else {
            if (parsArgs[0].matches("7") || parsArgs[0].matches("8")) {
                if (!(parsArgs[1].toLowerCase().matches("[a-z]+") && parsArgs[1].toLowerCase().matches("[a-z]+"))) {

                    throw new IllegalArgumentException("First name or last name contains incorrect symbols");
                }
            } else {
                throw new NumberFormatException("Wrong cadence. You can choose 7th or 8th cadence.");
            }
            return true;
        }
    }
}