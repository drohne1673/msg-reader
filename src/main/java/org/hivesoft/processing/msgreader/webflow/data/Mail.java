package org.hivesoft.processing.msgreader.webflow.data;

import org.springframework.binding.message.MessageBuilder;
import org.springframework.util.StringUtils;
import org.springframework.webflow.execution.RequestContext;

import javax.mail.Header;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.validation.constraints.NotEmpty;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Mail implements Serializable {

  private static final long serialVersionUID = 1L;

  @NotEmpty
  private String source;

  public Mail() {
  }

  public Mail(String source) {
    this.source = source;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public Collection<MailHeader> parseMail(RequestContext context) {

    boolean hasMail = StringUtils.hasText(source);

    if (!hasMail) {
      context.getMessageContext().addMessage(
              new MessageBuilder().error()
                      .defaultText("Please enter a valid source!")
                      .build());
      return Collections.emptyList();
    }

    Session mailSession = Session.getDefaultInstance(new Properties(), null);
    InputStream srcIn = new ByteArrayInputStream(source.getBytes(StandardCharsets.UTF_8));
    Collection<MailHeader> result = new ArrayList<>();
    try {
      MimeMessage message = new MimeMessage(mailSession, srcIn);
      Enumeration<Header> allHeaders = message.getAllHeaders();
      while (allHeaders.hasMoreElements()) {
        Header header = allHeaders.nextElement();
        result.add(new MailHeader(header.getName(), header.getValue()));
//        System.out.println("Added header: " + header.getName());
//        result.add(header.getName() + ": " + header.getValue());
      }
    } catch (MessagingException e) {
      throw new IllegalArgumentException(e);
    }
    return result;
  }
}
