package com.grommash88.app.util;

import java.util.concurrent.CopyOnWriteArraySet;

public class Validator {

  public static boolean isValidSiteName(String siteName) {
    return siteName.matches(".+\\.(ru|com|org|ua)");
  }

  public static boolean isStringMeetsRequirements(String string, String startPage) {
    return (string.trim().matches(startPage + "/.+")
        && !string.trim().matches(".+/.+#.*")
        && !string.trim().matches(".+\\.js"))
        || (string.trim().matches("/.+/.*")
        && !string.trim().matches(".+/.+#.*")
        && !string.trim().matches(".+\\.js"));
  }

  public static boolean isFullRef(String ref, String startPage) {
    return ref.matches(startPage + ("/.+"));
  }

  public static boolean isValidPath(String pathToTheProcessedPage, String startPage) {
    return (pathToTheProcessedPage.matches(".+/.*")
        || pathToTheProcessedPage.matches(startPage + "/.*"));
  }

  public static boolean isNotProcessedValidRef(CopyOnWriteArraySet<String> refs, String ref) {
    return !refs.contains(ref) && ref.matches(".+/");
  }
}
