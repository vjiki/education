package cmd.impl;

import cmd.Command;

public class Quit implements Command {

  @Override
  public void exec(String... options) {
    System.out.println("Quit the program");
  }
}
