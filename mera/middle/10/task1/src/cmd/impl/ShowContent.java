package cmd.impl;

import cmd.Command;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShowContent implements Command {

  @Override
  public void exec(String... options) {
    Optional<String> optionalFileName = Arrays.stream(options).findFirst();

    if (optionalFileName.isPresent()) {
      String fileName = System.getProperty("user.dir") + "\\" + optionalFileName.get();
      openOutputFile(fileName);
    }
  }


  private void openOutputFile(String file_name) {
    Path path = Paths.get(file_name);

    try {
      Stream<String> lines = Files.lines(path);
      String data = lines.collect(Collectors.joining("\n"));
      System.out.println(data);
      lines.close();
    }
    catch (IOException e) {
      System.out.println(e);
    }
  }
}
