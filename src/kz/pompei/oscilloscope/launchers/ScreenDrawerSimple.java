package kz.pompei.oscilloscope.launchers;

import java.awt.*;

import static java.lang.Math.PI;

public class ScreenDrawerSimple implements ScreenDrawer {
  private int width, height;
  private Func func;

  @Override
  public void setFunc(FuncPulses func) {
    this.func = func;
  }

  @Override
  public void setWidth(int width) {
    this.width = width;
  }

  @Override
  public void setHeight(int height) {
    this.height = height;
  }

  private final CoorsConverter cc = new CoorsConverter();

  private int[] canvas = null;

  @Override
  public void paint(Graphics2D g) {

    g.setColor(Color.BLACK);
    g.fillRect(0, 0, width, height);

    g.setColor(Color.WHITE);

    cc.defineScreen(-PI, PI, -1.2, +1.2, width, height);

    final vec2 xy = new vec2();
    final vec2 XY = new vec2();

    g.setColor(Color.GREEN);

    for (int X = 0; X <= width; X++) {
      cc.convertB(xy, X, 0);
      xy.y = func.getValue(xy.x);
      cc.convertA(XY, xy.x, xy.y);
      g.drawLine(XY.X(), XY.Y(), XY.X(), XY.Y());
    }

  }

}
