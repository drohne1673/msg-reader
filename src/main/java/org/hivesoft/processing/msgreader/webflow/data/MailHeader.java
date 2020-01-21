package org.hivesoft.processing.msgreader.webflow.data;

import java.io.Serializable;
import java.util.Objects;

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
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    MailHeader that = (MailHeader) o;
    return Objects.equals(name, that.name) &&
            Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, value);
  }

  @Override
  public String toString() {
    return "MailHeader{" +
            "name='" + name + '\'' +
            ", value='" + value + '\'' +
            '}';
  }
}
