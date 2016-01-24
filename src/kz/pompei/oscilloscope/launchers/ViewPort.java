package kz.pompei.oscilloscope.launchers;

public class ViewPort {
  public final CoorsConverter cc = new CoorsConverter();

  private double x1, x2, y1, y2;
  private int width, height;

  public double x1() {
    return x1;
  }

  public double x2() {
    return x2;
  }

  public double y1() {
    return y1;
  }

  public double y2() {
    return y2;
  }

  public int width() {
    return width;
  }

  public int height() {
    return height;
  }

  public void defineScreen(double x1, double x2, double y1, double y2, int width, int height) {
    cc.defineScreen(x1, x2, y1, y2, width, height);

    this.x1 = x1;
    this.x2 = x2;
    this.y1 = y1;
    this.y2 = y2;
    this.width = width;
    this.height = height;
  }

}
