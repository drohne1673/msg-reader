package org.hivesoft.processing.msgreader.webflow.data;

import java.io.Serializable;

public class MailHeader implements Serializable {

  private static final long serialVersionUID = 1L;

  private final String name;
  private final String value;

  public MailHeader(String name, String value) {
    this.name = name;
    this.value = value;
  }

  public String getName() {
    return this.name;
  }

  public String getValue() {
    return this.value;
  }

  @Override
  public String toString() {
    return "MailHeader{" +
            "name='" + name + '\'' +
            ", value='" + value + '\'' +
            '}';
  }
}
