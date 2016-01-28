package kz.pompei.oscilloscope.launchers;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class BigCalc {
  public static void main(String[] args) {
    MathContext mc = new MathContext(100, RoundingMode.HALF_UP);

    BigDecimal res = BigDecimal.ZERO;

    for (int i = 1; i <= 100000; i++) {
      BigDecimal I = new BigDecimal(i);
      BigDecimal K = I.multiply(I).multiply(I);

      BigDecimal part = BigDecimal.ONE.divide(K, mc);
      System.out.println("K = " + K + ", part = " + part.toPlainString());

      res = res.add(part);
      System.out.println("res = " + res);
    }

  }
}
