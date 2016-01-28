package kz.pompei.oscilloscope.launchers;

import static java.lang.Math.*;

public class FuncTrapeze implements Func {

  final long createdAt = System.currentTimeMillis();

  @Override
  public double getValue(double x_st) {
    return getValueDouble(x_st);
  }

  public double getValueDouble(double x_st) {

    double timeSec = (System.currentTimeMillis() - createdAt) / 1000.0;

    double omega = 0.08 * (2 * PI);

    double x = 2 * x_st + omega * timeSec;

    double A = 4 / PI / 2;

    double w = 0;
    int sign = 1;
    for (int n = 1; n < 17; n++) {
      int k1 = 2 * n;
      int k2 = 2 * n - 1;
      int k3 = 2 * n + 1;

      w += cos(k1 * x) / (k2 * k3);

      sign = -sign;
    }

    double res = 1 / PI + sin(x) / 2 - 2 * w / PI;

    return res * A;
  }
}
