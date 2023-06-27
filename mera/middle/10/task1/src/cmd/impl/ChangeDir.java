package cmd.impl;

import cmd.Command;
import java.io.File;
import java.util.Arrays;
import java.util.Optional;

public class ChangeDir implements Command {

  @Override
  public void exec(String... options) {
    Optional<String> optionalDirName = Arrays.stream(options).findFirst();

    if (optionalDirName.isPresent()) {
      if (!setCurrentDirectory(optionalDirName.get())) {
        System.out.println("could not open the dir: " + optionalDirName.get());
      } else {
        System.out.println(optionalDirName.get());
      }
    }
  }

  private boolean setCurrentDirectory(String directory_name)
  {
    boolean result = false;  // Boolean indicating whether directory was set
    File directory;       // Desired current working directory

    directory = new File(directory_name).getAbsoluteFile();
    if (directory.exists() || directory.mkdirs())
    {
      result = (System.setProperty("user.dir", directory.getAbsolutePath()) != null);
    }

    return result;
  }
}
