package com.grommash88.app.util;

import com.grommash88.app.util.logger.AppLogger;
import com.grommash88.app.util.logger.Msgs;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class AppWriter {

  public static void writeSiteMapInFile(String fileName, String data)
      throws FileNotFoundException {

    PrintWriter writer = new PrintWriter(fileName);
    writer.write(data);
    writer.flush();
    writer.close();
    AppLogger.logMessage(String.format(Msgs.END_MSG.getMsg(), fileName));
  }
}
