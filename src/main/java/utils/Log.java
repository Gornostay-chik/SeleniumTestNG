package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
    private static final Logger logger = LogManager.getLogger(Log.class);

    public static void info(String args) {

         logger.info(args);
    }

    public static void warn(String args) {

        logger.warn(args);
    }

    public static void error(String args) {

        logger.error(args);
    }

    public static void debug(String args) {

        logger.debug(args);
    }



}
