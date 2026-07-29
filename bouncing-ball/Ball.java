import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * A simple ball with position, velocity, size and color.
 * Knows how to move itself and bounce off the edges of a bounding box.
 */
public class Ball {

    private double x, y;
    private double dx, dy;
    private final int size;
    private final Color color;

    public Ball(double x, double y, double dx, double dy, int size, Color color) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.size = size;
        this.color = color;
    }

    /**
     * Advance the ball by its current velocity, then check whether it has
     * crossed any edge of the given bounds. If so, reflect the ball back
     * inside the bounds and reverse the velocity component responsible.
     */
    public void move(Rectangle bounds) {
        x += dx;
        y += dy;

        // Left edge
        if (x < bounds.x && dx < 0) {
            dx = -dx;
            x += 2 * (bounds.x - x);
        }
        // Right edge
        else if ((x + size) > (bounds.x + bounds.width) && dx > 0) {
            dx = -dx;
            x -= 2 * ((x + size) - (bounds.x + bounds.width));
        }

        // Top edge
        if (y < bounds.y && dy < 0) {
            dy = -dy;
            y += 2 * (bounds.y - y);
        }
        // Bottom edge
        else if ((y + size) > (bounds.y + bounds.height) && dy > 0) {
            dy = -dy;
            y -= 2 * ((y + size) - (bounds.y + bounds.height));
        }
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval((int) x, (int) y, size, size);
    }
}
