package kz.pompei.oscilloscope.launchers;

import javax.swing.*;
import java.awt.*;

public class OscilloscopeLauncher extends JPanel {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      JFrame mainFrame = new JFrame();
      mainFrame.setLocation(500, 300);
      mainFrame.setSize(800, 400);
      mainFrame.setContentPane(new OscilloscopeLauncher());
      mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      mainFrame.setVisible(true);
    });
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    paintG2((Graphics2D) g);
  }

  private void paintG2(Graphics2D g) {
    g.setColor(Color.RED);
    g.drawLine(10, 10, 100, 100);
  }
}
