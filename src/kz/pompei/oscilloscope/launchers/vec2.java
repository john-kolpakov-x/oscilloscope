package kz.pompei.oscilloscope.launchers;

public class vec2 {
  public double x, y;

  public vec2() {
  }

  public vec2(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public vec2(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public vec2(vec2 v) {
    if (v == null) {
      x = y = 0;
      return;
    }
    this.x = v.x;
    this.y = v.y;
  }

  public void X(int X) {
    x = X;
  }

  public void Y(int Y) {
    y = Y;
  }

  public int X() {
    return (int) Math.round(x);
  }

  public int Y() {
    return (int) Math.round(y);
  }

  @Override
  public String toString() {
    return "(" + x + ", " + y + ")";
  }
}
