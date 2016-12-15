import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;

public class Test {
    /* Get actual class name to be printed on */
    static Logger log = LogManager.getLogger(Test.class.getName());

    public static void main(String[] args)throws IOException,SQLException {
         log.debug("Hello this is a debug message");
         log.info("Hello this is an info message");
    }
}