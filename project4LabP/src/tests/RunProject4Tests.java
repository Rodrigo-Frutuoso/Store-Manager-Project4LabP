package tests;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

import project.*;

public class RunProject4Tests {

    private static void testRun(int exec) throws FileNotFoundException {
        RunProject4 runProject4 = new RunProject4();
        runProject4.execute(exec);
        Scanner expectedScanner = new Scanner(new File("./expectedOutput/storeLog" + exec + ".txt")).useDelimiter("\\A");
        String expected = expectedScanner.next();
        Scanner actualScanner = new Scanner(new File("./output/storeLog" + exec + ".txt")).useDelimiter("\\A");;
        String actual = actualScanner.next();
        expectedScanner.close();
        actualScanner.close();
        assertEquals(expected, actual);
    }

    @Test
    public void testRun1() throws FileNotFoundException {
        testRun(1);
    }

    @Test
    public void testRun2() throws FileNotFoundException {
        testRun(2);
    }

    @Test
    public void testRun3() throws FileNotFoundException {
        testRun(3);
    }

    @Test
    public void testRun4() throws FileNotFoundException {
        testRun(4);
    }

    @Test
    public void testRun5() throws FileNotFoundException {
        testRun(5);
    }


}