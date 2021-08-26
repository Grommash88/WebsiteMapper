package com.grommash88.app.mapper;

import java.util.Set;

public interface Node {

  Set<String> getChildren();

  String getValue();
}

