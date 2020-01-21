package org.hivesoft.processing.msgreader.webflow.data;

import org.hivesoft.processing.msgreader.webflow.testutils.AbstractMarshallingTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.binding.message.Severity;
import org.springframework.webflow.test.MockRequestContext;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
  void parseMail_notValid() {
    classUnderTest = new Mail("just some text");

    Collection<MailHeader> parsedMail = classUnderTest.parseMail(new MockRequestContext());

    assertThat(parsedMail)
            .contains(new MailHeader("just some text", "just some text"))
            .hasSize(1);
  }

  @Test
  void parseMail_empty() {
    classUnderTest = new Mail(null);

    MockRequestContext context = new MockRequestContext();
    Collection<MailHeader> parsedMail = classUnderTest.parseMail(context);

    assertThat(parsedMail)
            .isEmpty();
    List<String> allMessages = Arrays.stream(context.getMessageContext().getAllMessages()).map(m -> m.getText() + " " + m.getSeverity()).collect(Collectors.toList());
    assertThat(allMessages).contains("Please enter a valid source!" + " " + Severity.ERROR);
  }
}