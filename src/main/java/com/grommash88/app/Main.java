package com.grommash88.app;

import com.grommash88.app.logger.AppLogger;
import com.grommash88.app.logger.Msgs;
import com.grommash88.app.mapper.SiteMapper;
import com.grommash88.app.util.Validator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.concurrent.ForkJoinPool;

public class Main {

  public static void main(String[] args) throws IOException {

    String pathToTheProcessedSite;
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    while (true) {
      AppLogger.logMessage(Msgs.ENTER_SITE_NAME.getMsg());
      pathToTheProcessedSite = reader.readLine().trim().toLowerCase();
      if (Validator.isValidSiteName(pathToTheProcessedSite)) {
        pathToTheProcessedSite = "https://".concat(pathToTheProcessedSite);
        break;
      }
    }

    String fileName = "site_maps/".concat(
        pathToTheProcessedSite.replaceAll("https://", "").concat(".txt"));
    AppLogger.logMessage(Msgs.START_MSG.getMsg());
    new ForkJoinPool(Runtime.getRuntime().availableProcessors())
        .invoke(new SiteMapper(pathToTheProcessedSite, pathToTheProcessedSite));

    PrintWriter writer = new PrintWriter(fileName);
    writer.write(SiteMapper.getSiteMapInString());
    writer.flush();
    writer.close();
    AppLogger.logMessage(String.format(Msgs.END_MSG.getMsg(), fileName));
  }
}