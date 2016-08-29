package kz.pompei.oscilloscope.launchers;

import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.ObjectOutputStream;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import static kz.pompei.oscilloscope.launchers.Util.dummyCheck;

public class FindSame {
  public static void main(String[] args) throws Exception {

    ObjectOutputStream oos;

    FindSame fs = new FindSame();

    fs.createImage();

    fs.saveTo(new File("build/image.png"));

    System.out.println("Finish");
  }

  static class DotStyle {
    Color bgColor;
    Color pointColor;
  }

  private void saveTo(File file) throws Exception {
    dummyCheck(file.getParentFile().mkdirs());

    ImageIO.write(im, "png", file);
  }

  public static class Connection implements Comparable<Connection> {
    final int i1, j1, i2, j2;

    public Connection(int i1, int j1, int i2, int j2) {
      if (i1 > i2 || i1 == i2 && j1 > j2) {
        int tmp = i1;
        i1 = i2;
        i2 = tmp;
        tmp = j1;
        j1 = j2;
        j2 = tmp;
      }
      this.i1 = i1;
      this.j1 = j1;
      this.i2 = i2;
      this.j2 = j2;
    }

    @Override
    public int compareTo(@NotNull Connection o) {
      {
        int c = i1 - o.i1;
        if (c != 0) return c;
      }
      {
        int c = j1 - o.j1;
        if (c != 0) return c;
      }
      {
        int c = i2 - o.i2;
        if (c != 0) return c;
      }
      {
        int c = j2 - o.j2;
        if (c != 0) return c;
      }
      return 0;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Connection that = (Connection) o;

      return i1 == that.i1 && j1 == that.j1 && i2 == that.i2 && j2 == that.j2;

    }

    @Override
    public int hashCode() {
      int result = i1;
      result = 31 * result + j1;
      result = 31 * result + i2;
      result = 31 * result + j2;
      return result;
    }
  }

  static class ConnectionCollector {
    final Set<Connection> set = new TreeSet<>();
    private final int iCount;
    private final int jCount;

    final Random rnd = new Random();

    public ConnectionCollector(int iCount, int jCount) {
      this.iCount = iCount;
      this.jCount = jCount;
    }

    public void init() {
      for (int i = 0; i < 5; i++) {
        int i1 = rnd.nextInt(iCount);
        int j1 = rnd.nextInt(jCount);
        int i2 = rnd.nextInt(iCount);
        int j2 = rnd.nextInt(jCount);
        set.add(new Connection(i1, j1, i2, j2));
      }
    }
  }

  static class IXY implements Comparable<IXY> {
    final int ix;
    final int iy;

    public IXY(int ix, int iy) {
      this.ix = ix;
      this.iy = iy;
    }

    @Override
    public int compareTo(@NotNull IXY o) {
      int c = ix - o.ix;
      if (c != 0) return c;
      return iy - o.iy;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      IXY ixy = (IXY) o;

      return ix == ixy.ix && iy == ixy.iy;
    }

    @Override
    public int hashCode() {
      int result = ix;
      result = 31 * result + iy;
      return result;
    }
  }

  private final BufferedImage im = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
  private Graphics2D g;

  private void createImage() {
    DotStyle normal = new DotStyle();
    normal.bgColor = Color.gray;
    normal.pointColor = Color.black;

    DotStyle central = new DotStyle();
    central.bgColor = Color.cyan;
    central.pointColor = Color.black;

    g = im.createGraphics();
    setRHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    setRHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    g.setColor(Color.white);
    g.fillRect(0, 0, im.getWidth(), im.getHeight());

    for (int iy = 0; iy < CELL_ROWS; iy++) {

      for (int ix = 0; ix < CELL_COLS; ix++) {

        boolean draw = true;

        if (3 <= ix && ix <= 5 && 2 <= iy && iy <= 4) {
          draw = false;
        }

        if (draw) drawRect(ix, iy, normal);
      }

    }

    drawRect(4, 3, central);

    g.dispose();
  }

  private void setRHints(RenderingHints.Key key, Object value) {
    g.setRenderingHints(new RenderingHints(key, value));
  }

  private static final int CELL_COLS = 9;
  private static final int CELL_ROWS = 7;

  private static final int DOT_NODE_WIDTH = 20, DOT_NODE_HEIGHT = 20;
  private static final int DOT_COLS = 4, DOT_ROWS = 4;
  private static final int POINT_RADIUS = 5;
  private static final int DOT_X_PADDING = 5, DOT_Y_PADDING = 5;

  private static final int LEFT_PADDING = 5, TOP_PADDING = 5;
  private static final int DOT_X_SPACING = 5, DOT_Y_SPACING = 5;

  private static final int CELL_WIDTH = DOT_X_PADDING + POINT_RADIUS
      + (DOT_COLS - 1) * DOT_NODE_WIDTH
      + POINT_RADIUS + DOT_X_PADDING
      + DOT_X_SPACING;
  private static final int CELL_HEIGHT = DOT_Y_PADDING + POINT_RADIUS
      + (DOT_ROWS - 1) * DOT_NODE_HEIGHT
      + POINT_RADIUS + DOT_Y_PADDING
      + DOT_Y_SPACING;

  private static final int WIDTH = CELL_WIDTH * CELL_COLS + 2 * LEFT_PADDING;
  private static final int HEIGHT = CELL_HEIGHT * CELL_ROWS + 2 * TOP_PADDING;

  private void drawRect(int ix, int iy, DotStyle style) {

    int xDot = LEFT_PADDING + CELL_WIDTH * ix;
    int yDot = TOP_PADDING + CELL_HEIGHT * iy;
    g.setColor(style.bgColor);
    g.fillRoundRect(xDot, yDot, CELL_WIDTH - DOT_X_SPACING, CELL_HEIGHT - DOT_Y_SPACING,
        POINT_RADIUS + DOT_X_PADDING, POINT_RADIUS + DOT_Y_PADDING);

    g.setColor(style.pointColor);
    for (int j = 0; j < DOT_ROWS; j++) {
      for (int i = 0; i < DOT_COLS; i++) {
        int xCenter = xDot + DOT_X_PADDING + POINT_RADIUS + DOT_NODE_WIDTH * i;
        int yCenter = yDot + DOT_Y_PADDING + POINT_RADIUS + DOT_NODE_HEIGHT * j;
        g.fillOval(xCenter - POINT_RADIUS, yCenter - POINT_RADIUS, POINT_RADIUS * 2, POINT_RADIUS * 2);
      }
    }

  }
}
