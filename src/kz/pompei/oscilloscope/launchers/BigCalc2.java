package kz.pompei.oscilloscope.launchers;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class BigCalc2 {
  final static int TECH = 1000;
  final static MathContext MC = new MathContext(TECH, RoundingMode.HALF_UP);

  public static void main(String[] args) {

    BigDecimal x = BigDecimal.valueOf(300.0);

    BigDecimal res = ln(x);

    System.out.println("ln(" + x + ") = " + res);
  }

  private static BigDecimal ln(BigDecimal x) {

    BigDecimal fact = BigDecimal.ONE;

    BigDecimal res = x, xn = x;

    for (int n = 2; n <= 10000; n++) {

      xn = xn.multiply(x);
      fact = fact.multiply(BigDecimal.valueOf(n));

      BigDecimal K = xn.divide(fact, MC);

      String old = partOf(res.toString());

      res = res.add(K);

      System.out.println("K    = " + K);
      System.out.println("fact = " + fact);
      System.out.println("xn   = " + xn);
      System.out.println("res  = " + res);
      System.out.println("---------------- n = " + n);

      if (old.equals(partOf(res.toString()))) break;
    }

    return res;
  }

  private static String partOf(String s) {
    if (s.length() < TECH) return s;
    return s.substring(0, TECH);
  }

}
