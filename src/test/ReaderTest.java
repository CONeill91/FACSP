package test;

import org.junit.Before;
import org.junit.Test;
import util.Reader;

import java.io.File;
import java.io.IOException;

/**
 * Created by Conor on 18/03/2016.
 */
public class ReaderTest {
    Reader reader;
    final String SAMPLE_FILEPATH = "C:/Users/Conor/Documents/NS7.spl~";

    @Before
    public void initReader(){
        reader = new Reader();
    }

    @Test
    public void testReadFileFailureIOException(){
        File file = new File(SAMPLE_FILEPATH);
        reader.readFile(file);
    }

    @Test (expected = NullPointerException.class)
    public void testReadFileFailureNullFile(){
        File file = null;
        reader.readFile(file);
    }




}
