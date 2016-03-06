package kz.pompei.oscilloscope.launchers;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class BigCalc {
  public static void main(String[] args) {
    MathContext mc = new MathContext(100, RoundingMode.HALF_UP);
    BigDecimal res = BigDecimal.ZERO;
    for (int i = 1; i <= 1001; i++) {
      BigDecimal I = new BigDecimal(i);
      BigDecimal K = I.multiply(I).multiply(I);
      BigDecimal part = BigDecimal.ONE.divide(K, mc);
      System.out.println("K = " + K + ", part = " + part.toPlainString());
      String old = res.toString();
      res = res.add(part);
      System.out.println("i = " + i + ", res = " + res);
      if (res.toString().equals(old)) break;
    }
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.add(Calendar.DAY_OF_YEAR, 1);
    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    System.out.println("date = " + sdf.format(date));
  }
}
