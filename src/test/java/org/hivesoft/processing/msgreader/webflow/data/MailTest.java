package org.hivesoft.processing.msgreader.webflow.data;

import org.hivesoft.processing.msgreader.webflow.testutils.AbstractMarshallingTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.webflow.test.MockRequestContext;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

class MailTest extends AbstractMarshallingTestBase {

  private Mail classUnderTest;

  @Test
  void parseMail_valid() {
    String mailAsString = readFile("samples/hotels.eml");
    classUnderTest = new Mail(mailAsString);

    Collection<MailHeader> parsedMail = classUnderTest.parseMail(new MockRequestContext());

    parsedMail.forEach(System.out::println);
    assertThat(parsedMail)
            .contains(new MailHeader("MIME-Version", "1.0"))
            .hasSize(20);
  }

  @Test
  void parseMail_invalid() {
    classUnderTest = new Mail("just some text");

    Collection<MailHeader> parsedMail = classUnderTest.parseMail(new MockRequestContext());

    assertThat(parsedMail)
            .contains(new MailHeader("just some text", "just some text"))
            .hasSize(1);
  }
}