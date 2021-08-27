package com.grommash88.app.mapper;

import com.grommash88.app.util.Validator;
import java.util.Comparator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class SiteMapper extends RecursiveAction {

  private final NodeImpl sitePage;
  private static String startPage;
  private static final CopyOnWriteArrayList<String> REF_LIST = new CopyOnWriteArrayList<>();
  private static final CopyOnWriteArraySet<String> PROCESSED_REFS = new CopyOnWriteArraySet<>();


  public SiteMapper(String pathToTheProcessedSite, String startPage) {

    SiteMapper.startPage = startPage;
    sitePage = new NodeImpl(pathToTheProcessedSite, startPage);
  }

  public static String getSiteMapInString() {

    StringBuilder stringBuilder = new StringBuilder();
    REF_LIST.stream()
        .map(ref -> ref.concat(System.lineSeparator()))
        .forEachOrdered(stringBuilder::append);
    return stringBuilder.toString();
  }


  @Override
  protected void compute() {

    if (PROCESSED_REFS.size() == 0) {

      PROCESSED_REFS.add(this.sitePage.getValue());
      REF_LIST.add(this.sitePage.getValue());
      this.sitePage.getChildren()
          .forEach(ref -> REF_LIST.add("\t".concat(ref)));

    } else if (!PROCESSED_REFS.contains(this.sitePage.getValue())) {

      PROCESSED_REFS.add(this.sitePage.getValue());
      int index = index(this.sitePage.getValue());

      if (index < REF_LIST.size() - 1) {

        this.sitePage.getChildren().forEach(ref -> REF_LIST.add(index + 1,
            REF_LIST.get(index).replaceAll(this.sitePage.getValue(), "")
            .concat("\t".concat(ref))));
      } else {

        this.sitePage.getChildren().forEach(ref -> REF_LIST.add(REF_LIST.get(index)
            .replaceAll(this.sitePage.getValue(), "")
            .concat("\t".concat(ref))));
      }
    }
    this.sitePage.getChildren().stream()
        .filter(ref -> Validator.isNotProcessedValidRef(PROCESSED_REFS, ref))
        .map(ref -> new SiteMapper(ref, startPage))
        .peek(ForkJoinTask::fork)
        .forEachOrdered(ForkJoinTask::join);

  }

  private int index(String sitePageAddress) {

    String parent = REF_LIST.stream()
        .filter(elem -> sitePageAddress.equals(elem.trim()))
        .min(Comparator.comparing(String::length))
        .orElseThrow(()-> new IllegalArgumentException(sitePageAddress));
    return REF_LIST.indexOf(parent);
  }
}
