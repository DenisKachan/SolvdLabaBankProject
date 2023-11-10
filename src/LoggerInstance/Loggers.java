package LoggerInstance;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class Loggers {

    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }

    public static final Logger LOGGER = (Logger) LogManager.getLogger(Loggers.class);

    public static void main(String[] args) {
        LOGGER.info("Hello!");
    }
}
