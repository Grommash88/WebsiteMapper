package com.grommash88.app.util.logger;

import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class AppLogger {

  private static final Marker EXCEPTION = MarkerManager.getMarker("EXCEPTION");
  private static final Marker CONSOLE = MarkerManager.getMarker("CONSOLE");
  private static final Marker CONSOLE_ERR = MarkerManager.getMarker("CONSOLE_ERR");
  private static final Logger LOGGER = LogManager.getRootLogger();
  private static final StringBuilder sb = new StringBuilder();
  public static void logException(Exception e) {

    Arrays.stream(e.getStackTrace())
        .map(StackTraceElement::toString)
        .map(s-> s.concat(System.lineSeparator()))
        .forEachOrdered(sb::append);

    LOGGER.error(EXCEPTION, e.getMessage().concat(System.lineSeparator().concat(sb.toString())));
    logErrMsg(e.getMessage());
  }

  public static void logMessage(String message) {

    LOGGER.info(CONSOLE, message);
  }

  public static void logErrMsg(String message) {

    LOGGER.debug(CONSOLE_ERR, message);
  }
}
