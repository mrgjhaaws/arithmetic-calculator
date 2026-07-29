import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Bounce - a bouncing ball animation.
 *
 * A Swing/AWT take on the classic "first game" exercise: a ball moves
 * around the screen and bounces off the walls, drawn over a simple
 * two-tone checkerboard background. Swing panels are double-buffered
 * by default, which is the modern equivalent of the offscreen-image
 * technique used to avoid flicker in old-school AWT applets.
 */
public class Bounce extends JPanel {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private static final int FRAME_DELAY_MS = 20; // ~50 frames per second

    private final Ball ball;
    private final Rectangle bounds;

    public Bounce() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.WHITE);

        bounds = new Rectangle(0, 0, WIDTH, HEIGHT);

        // Start the ball 1/3 of the way in from the left and 1/4 of the
        // way down from the top, moving slowly down and to the right.
        ball = new Ball(WIDTH / 3.0, HEIGHT / 4.0, 1.5, 2.3, 24, Color.BLUE);

        Timer timer = new Timer(FRAME_DELAY_MS, e -> {
            ball.move(bounds);
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw a 2x2 checkerboard background so the ball's motion is easy to see.
        int halfWidth = getWidth() / 2;
        int halfHeight = getHeight() / 2;

        g.setColor(Color.GRAY);
        g.fillRect(0, 0, halfWidth, halfHeight);
        g.fillRect(halfWidth, halfHeight, getWidth() - halfWidth, getHeight() - halfHeight);

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(halfWidth, 0, getWidth() - halfWidth, halfHeight);
        g.fillRect(0, halfHeight, halfWidth, getHeight() - halfHeight);

        ball.draw(g);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bounce");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Bounce());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
