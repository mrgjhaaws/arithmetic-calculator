package com.codingcompany.bounce;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * {@code Bounce} is a bouncing-ball animation and the entry point for
 * the application.
 *
 * <p>It is a Swing/AWT take on the classic "first game" exercise: a
 * {@link Ball} moves around the screen and bounces off the walls,
 * drawn over a simple two-tone checkerboard background. {@link JPanel}
 * is double-buffered by default, which is the modern equivalent of the
 * offscreen-image technique older, pre-Swing applets used to avoid
 * flicker.</p>
 *
 * @author Coding Company
 */
public class Bounce extends JPanel {

    /** Preferred width of the animation window, in pixels. */
    public static final int WIDTH = 400;

    /** Preferred height of the animation window, in pixels. */
    public static final int HEIGHT = 300;

    /** Delay between animation frames, in milliseconds (~50 fps). */
    public static final int FRAME_DELAY_MS = 20;

    private final Ball ball;
    private final Rectangle bounds;

    /**
     * Creates the animation panel: sizes itself, creates the ball, and
     * starts the animation timer.
     */
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

    /**
     * Paints the checkerboard background and the ball.
     *
     * @param g the graphics context to paint into
     */
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

    /**
     * Opens the application window.
     *
     * @param args unused
     */
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
