import cmd.Command;
import cmd.impl.ChangeDir;
import cmd.impl.NoSuchCommand;
import cmd.impl.ListDir;
import cmd.impl.Quit;
import cmd.impl.Remove;
import cmd.impl.ShowContent;
import cmd.impl.TextAppender;
import cmd.impl.Touch;
import java.util.Arrays;
import java.util.Scanner;

// Файловый менеджер.
//
//  Создать консольный файловый менеджер, который поддерживает ввод комманд от пользователя
// (например, посмотреть текущую директорию, сменить текущую директорию, удалить файл, создать файл, дозаписать какой-то текст в файл,
//  посмотреть файлы в текущей директории или в указанной директории).
//
//  Задание достаточно простое, обратите внимание на архитектуры различных комманд в консоли. Сможете ли вы сделать их легко расширяемыми?

public class Main {


  private static void printCurrentWorkingDirectoryUsingFileSystems() {

//    Path currentDirectoryPath = FileSystems.getDefault().getPath("");
    String currentDirectoryName = System.getProperty("user.dir");
    System.out.printf(currentDirectoryName + "\"" + ">");

  }

  public static void main(String[] args) {

//    String[] commandsHistory = new String[100];
    String delimSpace = " ";

    while (true) {
      printCurrentWorkingDirectoryUsingFileSystems();

      Scanner sc1 = new Scanner(System.in);
      String command = sc1.nextLine();

      String[] commandSplit  = command.split(delimSpace);
      String commandName = commandSplit[0].trim();

      Command cmd = switch (commandName) {
        case "ls" -> new ListDir();
        case "cd" -> new ChangeDir();
        case "rm" -> new Remove();
        case "touch" -> new Touch();
        case "append" -> new TextAppender();
        case "show" -> new ShowContent();
        case "q", "quit" -> new Quit();
        default -> new NoSuchCommand(commandName);
      };

      if (commandSplit.length > 1 ) {
        String[] commandArgs = Arrays.copyOfRange(commandSplit, 1, commandSplit.length);
        cmd.exec(commandArgs);
      } else {
        cmd.exec();
      }

      if (cmd instanceof Quit) {
        return;
      }
    }
  }
}