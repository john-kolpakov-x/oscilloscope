package kz.pompei.oscilloscope.launchers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;

public class OscilloscopeLauncher extends JPanel {
  public static void main(String[] args) {
    final OscilloscopeLauncher panel = new OscilloscopeLauncher();

    final JFrame mainFrame = new JFrame();
    mainFrame.setLocation(500, 300);
    mainFrame.setSize(800, 400);
    mainFrame.setContentPane(panel);
    mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    SwingUtilities.invokeLater(() -> {
      mainFrame.setVisible(true);
    });

    final Thread thread = new Thread(() -> {

      try {
        while (mainFrame.isVisible()) {

          Thread.sleep(10);

          SwingUtilities.invokeAndWait(panel::repaint);

        }
      } catch (InvocationTargetException | InterruptedException e) {
        throw new RuntimeException(e);
      }

    });

    mainFrame.addComponentListener(new ComponentAdapter() {
      @Override
      public void componentShown(ComponentEvent e) {
        thread.start();
      }
    });

  }

  public OscilloscopeLauncher() {
    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
          repaint();
        }
      }
    });
  }

  @Override
  public void paint(Graphics g) {
    //super.paint(g);
    paintG2((Graphics2D) g);
  }

  private final Screen screen = new Screen();

  {
    screen.f = new FuncSinus();
  }

  private void paintG2(Graphics2D g) {
    screen.width = getWidth();
    screen.height = getHeight();
    screen.paint(g);
  }
}
