package dev.jsort.window;

import javax.swing.*;
import java.awt.*;

public class GraphicsPanel extends JPanel {

    public static Number currentIndex = 0D;
    private final int PANEL_WIDTH = JSortWindow.WIDTH * 9 / 16;

    public GraphicsPanel() {
        setPreferredSize(new Dimension(PANEL_WIDTH, 720));
        setBackground(Color.GRAY);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paintComponent(g2d);
        double[] numbers = JSortWindow.getNumbersList().getGeneratedNumbers();
        double x = 0;
        double width = (double) PANEL_WIDTH / numbers.length;
        for (int i = 0; i < numbers.length; i++) {
            if (currentIndex.intValue() == i) {
                g2d.setPaint(Color.RED);
            } else {
                g2d.setPaint(Color.BLACK);
            }

            g2d.fillRect((int) x, (int) (720 - numbers[i]), (int) width + 1, (int) numbers[i]);

            x += width;
        }

        g2d.dispose();
        g.dispose();
    }
}
