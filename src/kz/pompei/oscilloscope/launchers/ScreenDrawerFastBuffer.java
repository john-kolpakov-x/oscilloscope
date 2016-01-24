package kz.pompei.oscilloscope.launchers;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import static java.lang.Math.PI;
import static java.lang.Math.ceil;

public class ScreenDrawerFastBuffer implements ScreenDrawer {
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

  private final ViewPort port = new ViewPort();

  private int[] canvas = null;
  BufferedImage image = null;

  final double gridDeltaX = 0.1, gridDeltaY = 0.1;

  @Override
  public void paint(Graphics2D g) {

    if (canvas == null || canvas.length != width * height) {
      canvas = new int[width * height];
    }
    Arrays.fill(canvas, 0);

    g.setColor(Color.BLACK);
    g.fillRect(0, 0, width, height);

    g.setColor(Color.WHITE);

    double h = 1.2;

    port.defineScreen(-PI, PI, -h, h, width, height);

    final vec2 xy = new vec2();
    final vec2 XY = new vec2();

    int graphRGB = Color.GREEN.getRGB();

    double dx = (port.x2() - port.x1()) / width;

    int N = 30;

    for (int X = 0; X < width; X++) {
      port.cc.convertB(xy, X, 0);

      for (int i = 0; i < N; i++) {
        xy.y = 4 * func.getValue(xy.x + i * dx / N);

        port.cc.convertA(XY, xy.x, xy.y);

        tryPutPixel(XY.X(), XY.Y(), graphRGB);
      }

    }

    putGrid();

    if (image == null || image.getWidth() != width || image.getHeight() != height) {
      image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }

    image.setRGB(0, 0, width, height, canvas, 0, width);

    g.drawImage(image, null, 0, 0);
  }

  private void putGrid() {
    //int osRGB = Color.GRAY.brighter().getRGB();
    int osRGB = Color.WHITE.darker().getRGB();
    int grid1RGB = Color.GRAY.darker().getRGB();
    int grid2RGB = Color.GRAY.darker().darker().getRGB();
    int grid3RGB = Color.GRAY.darker().darker().darker().getRGB();

    final vec2 XY = new vec2();

    {
      port.cc.convertA(XY, 0, 0);
      for (int X = 0; X < width; X++) {
        int Y = XY.Y();
        tryPutPixel(X, Y, osRGB);
      }
      for (int Y = 0; Y < height; Y++) {
        int X = XY.X();
        tryPutPixel(X, Y, osRGB);
      }
    }
    {
      double nomer = ceil(port.x1() / gridDeltaX);
      double x = nomer * gridDeltaX;
      boolean anotherColors = false;
      long n = 0;
      if ((double) Long.MIN_VALUE < x && x < (double) Long.MAX_VALUE) {
        n = (long) nomer;
        anotherColors = true;
      }

      while (x < port.x2()) {
        int rgb = grid3RGB;

        if (anotherColors) {
          if (n % 10 == 0) rgb = grid1RGB;
          else if (n % 5 == 0) rgb = grid2RGB;
        }


        port.cc.convertA(XY, x, 0);
        for (int Y = 0; Y < height; Y++) {
          int X = XY.X();
          tryPutPixel(X, Y, rgb);
        }

        x += gridDeltaX;
        n++;
      }
    }

    {
      double nomer = ceil(port.y1() / gridDeltaY);
      double y = nomer * gridDeltaY;
      boolean anotherColors = false;
      long n = 0;
      if ((double) Long.MIN_VALUE < y && y < (double) Long.MAX_VALUE) {
        n = (long) nomer;
        anotherColors = true;
      }

      while (y < port.y2()) {
        int rgb = grid3RGB;

        if (anotherColors) {
          if (n % 10 == 0) rgb = grid1RGB;
          else if (n % 5 == 0) rgb = grid2RGB;
        }


        port.cc.convertA(XY, 0, y);
        for (int X = 0; X < width; X++) {
          int Y = XY.Y();
          tryPutPixel(X, Y, rgb);
        }

        y += gridDeltaY;
        n++;
      }
    }

  }

  private void tryPutPixel(int X, int Y, int rgb) {
    if (X < 0) X = 0;
    if (X >= width) X = width - 1;
    if (Y < 0) Y = 0;
    if (Y >= height) Y = height - 1;

    int index = Y * width + X;
    if (canvas[index] == 0) canvas[index] = rgb;
  }

}
