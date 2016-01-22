package kz.pompei.oscilloscope.launchers;

import java.awt.*;

import static java.lang.Math.PI;

public class Screen {
  public int width, height;
  public Func f;

  double x1 = -PI, x2 = +PI;

  private final CoorsConverter cc = new CoorsConverter();

  public void paint(Graphics2D g) {

    g.setColor(Color.BLACK);
    g.fillRect(0, 0, width, height);

    g.setColor(Color.WHITE);

    cc.defineScreen(-PI, PI, -1.2, +1.2, width, height);

    final vec2 xy = new vec2();
    final vec2 XY = new vec2();

    g.setColor(Color.WHITE);

    for (int X = 0; X <= width; X++) {
      cc.convertB(xy, X, 0);
      xy.y = f.getValue(xy.x);
      cc.convertA(XY, xy.x, xy.y);
      g.drawLine(XY.X(), XY.Y(), XY.X(), XY.Y());
    }

  }
}
