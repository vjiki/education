package cmd.impl;

import cmd.Command;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class Touch implements Command {

  @Override
  public void exec(String... options) {
    Optional<String> optionalFileName = Arrays.stream(options).findFirst();

    if (optionalFileName.isPresent()) {
      String fileName = System.getProperty("user.dir") + "\\" + optionalFileName.get();
      File file = new File(fileName);
      try {
        if (file.createNewFile()) {
          System.out.println(fileName + ": File Created in current dir");
        } else {
          System.out.println(fileName + ": already exists in the current directory");
        }
      } catch (IOException e) {
        System.out.println(e);
      }
    }
  }
}
