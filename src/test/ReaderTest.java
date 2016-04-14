package test;

import org.junit.Before;
import org.junit.Test;
import util.Reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * Class to test the Reader
 * @author Conor
 */
public class ReaderTest {
    Reader reader;
    final String SAMPLE_FILEPATH = "C:/Users/Conor/Documents/Test Scripts/NS7.spl~";

    @Before
    public void initReader(){
        reader = new Reader();
    }

    @Test (expected = NullPointerException.class)
    public void testReadFileFailureNullFile(){
        File file = null;
        reader.readFile(file);
    }




}
