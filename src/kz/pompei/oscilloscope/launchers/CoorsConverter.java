package kz.pompei.oscilloscope.launchers;

public class CoorsConverter {
  /*

  X = aXx * x    +   aXy * y   +   aX;
  Y = aYx * x    +   aYy * y   +   aY;

  */
  public double aXx, aXy, aX;
  public double aYx, aYy, aY;

  public void convertA(vec2 XY, double x, double y) {

    XY.x = aXx * x + aXy * y + aX;
    XY.y = aYx * x + aYy * y + aY;

  }

  /*

  x = bxX * X   +   bxY * Y   +   bx
  y = byX * X   +   byY * Y   +   by

  */
  public double bxX, bxY, bx;
  public double byX, byY, by;

  public void convertB(vec2 xy, double X, double Y) {

    xy.x = bxX * X + bxY * Y + bx;
    xy.y = byX * X + byY * Y + by;

  }

  public void defineScreen(double x1, double x2, double y1, double y2, int width, int height) {
    aXy = aYx = 0;

    aXx = width / (x2 - x1);
    aX = width * x1 / (x1 - x2);

    aYy = height / (y1 - y2);
    aY = height * y2 / (y2 - y1);

    a_to_b();
  }

  public void a_to_b() {
    double D = aXx * aYy - aXy * aYx;

    bxX = aYy / D;
    bxY = -aXy / D;
    bx = (aY * aXy - aX * aYy) / D;

    byX = -aYx / D;
    byY = aXx / D;
    by = (aX * aYx - aY * aXx) / D;
  }

  public void b_to_a() {
    double D = bxX * byY - bxY * byX;

    aXx = byY / D;
    aXy = -bxY / D;
    aX = (by * bxY - bx * byY) / D;

    aYx = -byX / D;
    aYy = bxX / D;
    aY = (bx * byX - by * bxX) / D;
  }
}
