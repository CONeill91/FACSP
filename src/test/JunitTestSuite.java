package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * JUnit Test Suite for the application
 * @author Conor
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ReaderTest.class, ParserTest.class, PrettyPrinterTest.class,
})

public class JunitTestSuite {
}
