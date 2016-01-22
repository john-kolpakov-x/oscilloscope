package kz.pompei.oscilloscope.launchers;

import static java.lang.Math.*;

public class FuncSinus implements Func {

  final long createdAt = System.currentTimeMillis();

  @Override
  public double getValue(double x_st) {

    double timeSec = (System.currentTimeMillis() - createdAt) / 1000.0;

    double omega = 0.08 * (2 * PI);

    double x = 2 * x_st + omega * timeSec;

    double A = 4 / PI / 5;

    final double DEG = PI / 180;

    final double alpha = 85 * DEG;

    double ret = 0;
    int sign = 1;
    for (int n = 0; n < 300; n++) {
      //ret += sign * A * sin((2 * n + 1) * xx) / (2 * n + 1) / (2 * n + 1);
      //ret += sign * A * sin(n * xx) / n;
      //ret += sign * A * cos(n * xx) / n/n;
      int k = 2 * n + 1;
      ret += cos(k * alpha) * sin(k * x) / k;
      sign = -sign;
    }

    return ret * A;
  }
}
