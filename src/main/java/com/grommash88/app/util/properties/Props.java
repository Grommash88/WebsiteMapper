package com.grommash88.app.util.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Props {

  REFERRER("https://www.google.com"),
  USER_AGENT("Chrome/4.0.249.0 Safari/532.5");

  private final String property;
}