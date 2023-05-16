package appiumproject.testcases;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoggerDemo {
    public static void main(final String[] args)
    {
        System.out.println("***** Logger demo class *****");
        Logger logger = LoggerFactory.getLogger(LoggerDemo.class);
        logger.info("***** Logger demo class *****");
        logger.info("Info log ");
        logger.error("Error log");
        logger.warn("Warn log");

    }

}

