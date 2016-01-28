package kz.pompei.oscilloscope.launchers;

import java.awt.*;

public interface ScreenDrawer {
  void paint(Graphics2D g);

  void setFunc(Func func);

  void setWidth(int width);

  void setHeight(int height);
}
