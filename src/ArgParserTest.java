import org.testng.annotations.Test;

import static org.testng.Assert.*;


/**
 * Created by Matthew on 2017-01-06.
 */
public class ArgParserTest {

    @Test
    public void argTest() throws Exception {
        String[] testTable = {"7", "Piotr", "Bauć"};
        String[] testTable1 = {"8", "Anna", "Fotyga"};
        ArgParser test = new ArgParser(testTable);
        ArgParser test1 = new ArgParser(testTable1);
        assertTrue(test.parserArguments());
        assertTrue(test1.parserArguments());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void argExceptionTest1(){
        String[] testTable = {"8","Anna"};
        ArgParser test = new ArgParser(testTable);
        test.parserArguments();
    }
    @Test(expectedExceptions = NumberFormatException.class)
    public void argExceptionTest2(){
        String[] testTable = {"5","Anna","Fotyga"};
        ArgParser test = new ArgParser(testTable);
        test.parserArguments();
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void argExceptionTest3(){
        String[] testTable = {"5","konstytucja.txt","Fotyga"};
        ArgParser test = new ArgParser(testTable);
        test.parserArguments();
    }

}