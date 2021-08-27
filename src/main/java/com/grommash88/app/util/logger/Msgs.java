package com.grommash88.app.util.logger;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Msgs {

  START_MSG("Site mapping start ..."),
  END_MSG("Site map is compiled and recorded in the file: %s"),
  PAGE_404_MSG("Page %s does not exist."),
  ENTER_SITE_NAME("Enter mapped site name.");

  private final String msg;
}
