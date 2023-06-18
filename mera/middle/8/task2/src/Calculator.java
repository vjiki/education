import java.util.HashMap;
import java.util.Map;
import operation.Operation;
import operation.impl.Division;

public class Calculator {

  Map<String, Operation> operationMap = new HashMap<>();

  public void calculate(String operationName, Double number1, Double number2) {
    Operation operation = operationMap.get(operationName);
    if(operation != null){
      System.out.println(operation.doOperation(number1, number2));
    }
    throw new ArithmeticException(operationName+" does not exists!");
  }

  public void addOperation(String operationName, Operation implementation) {
    operationMap.put(operationName, implementation);
  }

  public static void main(String[] args) {
    Calculator calculator = new Calculator();
    calculator.addOperation("sum", Double::sum);
    calculator.addOperation("div", new Division());
    calculator.calculate("sum", 2.0, 3.0);
    calculator.calculate("div", 2.0, 3.0);
  }
}
