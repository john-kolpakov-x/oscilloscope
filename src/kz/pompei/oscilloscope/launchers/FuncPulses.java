package kz.pompei.oscilloscope.launchers;

import static java.lang.Math.*;

public class FuncPulses implements Func {

  final long createdAt = System.currentTimeMillis();

  @Override
  public double getValue(double x_st) {
    return getValueDouble(x_st);
  }

  public double getValueDouble(double x_st) {

    double timeSec = (System.currentTimeMillis() - createdAt) / 1000.0;

    double omega = 0.08 * (2 * PI);

    double x = 2 * x_st + omega * timeSec;

    double A = 4 / PI /2 ;

    final double DEG = PI / 180;

    final double alpha = 60 * DEG;

    double ret = 0;
    int sign = 1;
    for (int n = 1; n < 60; n++) {
      int k = 2 * n - 1;

      ret += sign * sin(n * x) / n ;
      //ret += sign * sin(k * x) / k / k;
      //ret += sign * A * cos(n * x) / n/n;
      //ret += cos(k * alpha) * sin(k * x) / k;

      sign = -sign;
    }

    return ret * A;
  }
}
