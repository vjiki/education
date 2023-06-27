package cmd.impl;

import cmd.Command;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

public class TextAppender implements Command {

  @Override
  public void exec(String... options) {
    Optional<String> optionalFileName = Arrays.stream(options).findFirst();

    if (optionalFileName.isPresent()) {
      String fileName = System.getProperty("user.dir") + "\\" + optionalFileName.get();

      System.out.println("Enter text: ");
      Scanner sc2 = new Scanner(System.in);
      String textToAppend = sc2.nextLine();

      try {
        Files.write(
            Paths.get(fileName),
            textToAppend.getBytes(),
            StandardOpenOption.APPEND);
      } catch (IOException e) {
        System.out.println(e);
      }
    }
  }
}
