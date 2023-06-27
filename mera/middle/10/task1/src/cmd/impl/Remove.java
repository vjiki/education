package cmd.impl;

import cmd.Command;
import java.io.File;
import java.util.Arrays;
import java.util.Optional;

public class Remove implements Command {

  @Override
  public void exec(String... options) {
    Optional<String> optionalFileName = Arrays.stream(options).findFirst();

    if (optionalFileName.isPresent()) {
      String fileName = System.getProperty("user.dir") + "\\" + optionalFileName.get();
      File file = new File(fileName);
      if (file.delete()) {
        System.out.println(fileName + ": File deleted in current dir");
      } else {
        System.out.println(fileName + ": is not exist in the current directory");
      }
    }
  }
}
