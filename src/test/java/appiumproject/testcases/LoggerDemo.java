package appiumproject.testcases;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoggerDemo {
    public static void main(final String[] args)
    {
        Logger logger = LoggerFactory.getLogger(LoggerDemo.class);
        logger.info("Hello World !!");
        System.out.println("Hello World");
    }

}

