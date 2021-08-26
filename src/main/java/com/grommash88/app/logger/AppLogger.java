package com.grommash88.app.logger;

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

  public static void logException(Exception e) {

    LOGGER.error(EXCEPTION, Arrays.toString(e.getStackTrace()).concat(System.lineSeparator())
        .concat(e.getMessage()));
    logErrMsg(e.getMessage());
  }

  public static void logMessage(String message) {

    LOGGER.info(CONSOLE, message);
  }

  public static void logErrMsg(String message) {

    LOGGER.error(CONSOLE_ERR, message);
  }
}
