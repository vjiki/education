package cmd.impl;

import cmd.Command;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.stream.Stream;

public class ListDir implements Command {

  @Override
  public void exec(String... options) {
    File currentDir = new File(System.getProperty("user.dir"));

    Stream.of(Objects.requireNonNull(currentDir.listFiles()))
        .map(f -> {
          if (Files.isSymbolicLink(f.toPath())) {
            try {
              Path file = Files.readSymbolicLink(f.toPath());
              String fileName = f.isDirectory() ? f.getName() + "\\" : f.getName();
              return "'" + fileName + "'" + "\t -> \t" + file.toString();
            } catch (IOException e) {
              return f.getName();
            }
          }
          if (f.isDirectory()) {
            return f.getName() + "\\";
          }
          return f.getName();
        })
        .forEach(System.out::println);
  }
}
