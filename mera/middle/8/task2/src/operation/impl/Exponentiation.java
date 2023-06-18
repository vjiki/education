package operation.impl;

import operation.Operation;

public class Exponentiation implements Operation {

  @Override
  public Double doOperation(Double number1, Double number2) throws ArithmeticException {
    return Math.pow(number1, number2);
  }
}
