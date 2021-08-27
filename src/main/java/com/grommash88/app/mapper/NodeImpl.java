package com.grommash88.app.mapper;

import com.grommash88.app.exceptions.PageNotFoundException;
import com.grommash88.app.util.logger.AppLogger;
import com.grommash88.app.util.logger.Msgs;
import com.grommash88.app.util.Validator;
import com.grommash88.app.util.properties.Props;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class NodeImpl implements Node {

  private final String startPage;
  private final String pathToTheProcessedPage;
  private HashSet<String> children;

  public NodeImpl(String link, String startPage) {

    this.startPage = startPage;
    pathToTheProcessedPage = link;
    try {
      children = (HashSet<String>) getPageRefSet(link);
    } catch (NullPointerException e) {
      AppLogger.logException(new PageNotFoundException(
          String.format(Msgs.PAGE_404_MSG.getMsg(), link)));
      children = new HashSet<>();
    } catch (IOException | InterruptedException e) {
      AppLogger.logException(e);
      e.printStackTrace();
    }
  }

  private Set<String> getPageRefSet(String string)
      throws IOException, InterruptedException, NullPointerException {

    Thread.sleep(150);
    if (Validator.isValidPath(pathToTheProcessedPage, startPage)) {
      Document doc;
      try {
        doc = Jsoup.connect(string)
            .userAgent(Props.USER_AGENT.getProperty())
            .referrer(Props.REFERRER.getProperty())
            .get();
      } catch (HttpStatusException | SocketTimeoutException e) {
        doc = null;
      }
      assert doc != null;
      Elements elements = doc.select("[href]");
      return elements.stream().map(e -> e.attr("href"))
          .filter(ref -> Validator.isStringMeetsRequirements(ref, startPage))
          .map(href -> {
            if (!Validator.isFullRef(href.trim(), startPage)) {
              href = startPage.concat(href.replaceAll("//", "/"));
            }
            return href;
          }).collect(Collectors.toSet());
    } else {
      return new HashSet<>();
    }
  }

  @Override
  public Set<String> getChildren() {

    return children;
  }

  @Override
  public String getValue() {

    return pathToTheProcessedPage;
  }
}
