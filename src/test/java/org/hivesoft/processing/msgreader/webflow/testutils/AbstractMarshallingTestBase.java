package org.hivesoft.processing.msgreader.webflow.testutils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractMarshallingTestBase {

  public String readFile(String sourcePath) {
    try {
      Path path = Paths.get(getClass().getClassLoader()
              .getResource(sourcePath).toURI());

      Stream<String> lines = Files.lines(path);
      String data = lines.collect(Collectors.joining("\n"));
      lines.close();

      return data;
    } catch (URISyntaxException | IOException e) {
      throw new IllegalArgumentException("Could not read file " + sourcePath, e);
    }
  }
}
