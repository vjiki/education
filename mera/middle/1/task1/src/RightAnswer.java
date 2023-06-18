import java.util.Scanner;

public class RightAnswer {

  public static void main(String[] args) {

    int var1;
    int var2;

    Scanner sc1 = new Scanner(System.in);
    var1 = sc1.nextInt();
    var2 = sc1.nextInt();
    Integer sum = Integer.sum(var1,var2);
    int multiply = var1*var2;
    System.out.println(sum);
    System.out.println(multiply);
  }
}
