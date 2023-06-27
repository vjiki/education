package cmd.impl;

import cmd.Command;

public class NoSuchCommand implements Command {

  private final String cmdName;

  public NoSuchCommand(String cmdName) {
    this.cmdName = cmdName;
  }

  @Override
  public void exec(String... options) {
    System.out.println("No such command: " + cmdName);
  }
}
