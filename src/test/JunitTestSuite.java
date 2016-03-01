package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Conor on 29/02/2016.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        MessageTest.class, Process_Test.class, ProtocolTest.class,IntruderTest.class
})

public class JunitTestSuite {
}
