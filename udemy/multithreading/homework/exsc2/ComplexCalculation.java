import java.math.BigInteger;

public class ComplexCalculation {

  public static void main(String[] args) throws InterruptedException {
    System.out.println(calculateResult(BigInteger.valueOf(2), BigInteger.valueOf(2), BigInteger.valueOf(2), BigInteger.valueOf(2)));
  }

  public static BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2) throws InterruptedException {
    BigInteger result;
        /*
            Calculate result = ( base1 ^ power1 ) + (base2 ^ power2).
            Where each calculation in (..) is calculated on a different thread
        */
    PowerCalculatingThread thread1 = new PowerCalculatingThread(base1, power1);
    PowerCalculatingThread thread2 = new PowerCalculatingThread(base2, power2);
    thread1.start();
    thread2.start();
    thread1.join();
    thread2.join();
    result = thread1.getResult().add(thread2.getResult());
    return result;
  }

  private static class PowerCalculatingThread extends Thread {
    private BigInteger result = BigInteger.ONE;
    private final BigInteger base;
    private BigInteger power;

    public PowerCalculatingThread(BigInteger base, BigInteger power) {
      this.base = base;
      this.power = power;
    }

    @Override
    public void run() {
      BigInteger powerCount = power;
      while (!powerCount.equals(BigInteger.ZERO)) {
        powerCount = powerCount.subtract(BigInteger.ONE);
        result = result.multiply(base);
      }
    }

    public BigInteger getResult() { return result; }
  }
}