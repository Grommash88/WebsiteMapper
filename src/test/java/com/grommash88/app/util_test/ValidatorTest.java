package com.grommash88.app.util_test;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.grommash88.app.util.Validator;
import java.util.concurrent.CopyOnWriteArraySet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тестирование класса com.grommash88.app.util.Validator")
public class ValidatorTest {


  @Test
  @DisplayName("Тест метода isValidSiteName.")
  public void testIsValidSiteName() {
    String testName = "Some name";
    String testSiteName = testName.concat(".ru");
    assertTrue(Validator.isValidSiteName(testSiteName));
    testSiteName = testName.concat(".ua");
    assertTrue(Validator.isValidSiteName(testSiteName));
    testSiteName = testName.concat(".com");
    assertTrue(Validator.isValidSiteName(testSiteName));
    testSiteName = testName.concat(".org");
    assertTrue(Validator.isValidSiteName(testSiteName));
    testSiteName = testName;
    assertFalse(Validator.isValidSiteName(testSiteName));
    testSiteName = "";
    assertFalse(Validator.isValidSiteName(testSiteName));
    testSiteName = testName.concat(".comzfxgchvjk");
    assertFalse(Validator.isValidSiteName(testSiteName));
    testSiteName = testName.concat("uyfduyfu");
    assertFalse(Validator.isValidSiteName(testSiteName));
  }

  @Test
  @DisplayName("Тест метода isNotProcessedValidRef.")
  public void testIsNotProcessedValidRef() {

    CopyOnWriteArraySet<String> testRefs = new CopyOnWriteArraySet<>();
    testRefs.add("someRef1/");
    testRefs.add("someRef2/");
    String testRef = "SomeRef3/";
    assertTrue(Validator.isNotProcessedValidRef(testRefs, testRef));
    testRef = "someRef2/";
    assertFalse(Validator.isNotProcessedValidRef(testRefs, testRef));
    testRef = "SomeRef3";
    assertFalse(Validator.isNotProcessedValidRef(testRefs, testRef));
  }

  @Test
  @DisplayName("Тест метода isStringMeetsRequirements.")
  public void testIsStringMeetsRequirements() {
    String testStartPage = "Some text";
    String testRef = "https://some text.".concat(testStartPage).concat(".ru/");
    assertFalse(Validator.isStringMeetsRequirements(testRef, testStartPage));
    testRef = "/some text";
    assertFalse(Validator.isStringMeetsRequirements(testRef, testStartPage));
    testRef = testStartPage.concat("/some text#");
    assertFalse(Validator.isStringMeetsRequirements(testRef, testStartPage));
    testRef = testStartPage.concat("/some text.js");
    assertFalse(Validator.isStringMeetsRequirements(testRef, testStartPage));
    testRef = testStartPage.concat("/some te#xt");
    assertFalse(Validator.isStringMeetsRequirements(testRef, testStartPage));
    testRef = testStartPage.concat("/some text");
    assertTrue(Validator.isStringMeetsRequirements(testRef, testStartPage));
    testRef = "/some text/";
    assertTrue(Validator.isStringMeetsRequirements(testRef, testStartPage));
  }

  @Test
  @DisplayName("Тест метода isValidPath.")
  public void testIsValidPath() {
    String testStartPage = "Some text";
    String testPath = "https://some text.".concat(testStartPage).concat(".ru/");
    assertTrue(Validator.isValidPath(testPath, testStartPage));
    testPath = testStartPage.concat(".ru/");
    assertTrue(Validator.isValidPath(testPath, testStartPage));
    testPath = testStartPage.concat("/");
    assertTrue(Validator.isValidPath(testPath, testStartPage));
    testPath = "some text/some text";
    assertTrue(Validator.isValidPath(testPath, testStartPage));
    testPath = "some text/";
    assertTrue(Validator.isValidPath(testPath, testStartPage));
    testPath = "some text";
    assertFalse(Validator.isValidPath(testPath, testStartPage));
  }
}
