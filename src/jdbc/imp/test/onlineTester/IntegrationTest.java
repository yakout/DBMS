package jdbc.imp.test.onlineTester;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Driver;

public class IntegrationTest {

    public static Class<?> getSpecifications(){
        return Driver.class;
    }

    @Test
    public void test() {
        Assert.assertNotNull("Failed to create Driver implemenation",  (Driver) TestRunner.getImplementationInstance());
    }

}
