package com.grommash88.app;

import com.grommash88.app.util.logger.AppLogger;
import com.grommash88.app.util.logger.Msgs;
import com.grommash88.app.mapper.SiteMapper;
import com.grommash88.app.util.AppWriter;
import com.grommash88.app.util.Validator;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.ForkJoinPool;

public class Main {

  public static void main(String[] args) {

    try {

      String pathToProcessedSite;
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      while (true) {
        AppLogger.logMessage(Msgs.ENTER_SITE_NAME.getMsg());
        pathToProcessedSite = reader.readLine().trim().toLowerCase();
        if (Validator.isValidSiteName(pathToProcessedSite)) {
          pathToProcessedSite = "https://".concat(pathToProcessedSite);
          break;
        }
      }
      AppLogger.logMessage(Msgs.START_MSG.getMsg());
      new ForkJoinPool().invoke(new SiteMapper(pathToProcessedSite, pathToProcessedSite));
      String fileName = "site_maps/".concat(
          pathToProcessedSite.replaceAll("https://", "").concat(".txt"));
      AppWriter.writeSiteMapInFile(fileName, SiteMapper.getSiteMapInString());

    } catch (Exception e) {

      AppLogger.logException(e);
    }
  }
}