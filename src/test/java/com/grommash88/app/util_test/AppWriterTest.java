package com.grommash88.app.util_test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import com.grommash88.app.util.logger.AppLogger;
import com.grommash88.app.util.AppWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тестирование класса com.grommash88.app.util.AppWriter")
public class AppWriterTest {

  Random testRandom;
  List<String> testStrings;
  String testPathToProcessedSite;
  StringBuilder sb;

  @BeforeEach
  protected void setUp() {

    testPathToProcessedSite = "some path";
    testStrings = new ArrayList<>();
    testRandom = new Random();

    for (int i = 0; i < 10000; i++) {

      int tabCount = testRandom.nextInt(7);
      sb = new StringBuilder();
      sb.append("\t".repeat(tabCount));
      testStrings.add(sb.append("some reference").append(System.lineSeparator()).toString());
    }
    sb.setLength(0);
  }

  @Test
  @DisplayName("Тест метода writeSiteMapInFile.")
  public void testWriteSiteMapInFile() {

    String fileName = "src/test/resources/".concat(
        testPathToProcessedSite.replaceAll("https://", "").concat(".txt"));
    testStrings.forEach(sb::append);
    String data = sb.toString();
    List<String> actualList;
    try {
      AppWriter.writeSiteMapInFile(fileName, data);
      actualList = Files.readAllLines(Path.of(fileName));
      actualList = actualList.stream()
          .map(s -> s.concat(System.lineSeparator()))
          .collect(Collectors.toList());
    } catch (Exception e) {
      actualList = new ArrayList<>();
      AppLogger.logException(e);
    }

    assertEquals(testStrings, actualList);
  }

  @AfterEach
  void tearDown() {
    testStrings = null;
    testRandom = null;
    testPathToProcessedSite = null;
    sb = null;
  }

}
