import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;

public class Test {
    /* Get actual class name to be printed on */
	private static Logger log = LogManager.getLogger(Test.class);

    public static void main(String[] args)throws IOException,SQLException {
    	log.debug("This is a debug message");
    	log.info("This is an info message");
    	log.warn("This is a warn message");
    	log.error("This is an error message");
    	log.fatal("This is a fatal message");
    	log.debug("This is a debug message");
    	log.info("This is an info message");
    	log.warn("This is a warn message");
    	log.error("This is an error message");
    	log.fatal("This is a fatal message");
    }
}