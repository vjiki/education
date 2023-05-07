package operation.impl;

import operation.Operation;

public class Addition implements Operation {

  @Override
  public Double doOperation(Double number1, Double number2) throws ArithmeticException {
    return number1 + number2;
  }
}
