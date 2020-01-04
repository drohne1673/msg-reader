package org.hivesoft.processing.msgreader.webflow.data;

import org.hivesoft.processing.msgreader.webflow.testutils.AbstractMarshallingTestBase;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

class MailTest extends AbstractMarshallingTestBase {

  private Mail classUnderTest;

  @Test
  void parseMail_valid() {
    String mailAsString = readFile("samples/hotels.msg");
    classUnderTest = new Mail(mailAsString);

    Collection<String> parsedMail = classUnderTest.parseMail(null);

    parsedMail.forEach(System.out::println);
    assertThat(parsedMail)
            .contains("MIME-Version: 1.0")
            .hasSize(20);
  }
}