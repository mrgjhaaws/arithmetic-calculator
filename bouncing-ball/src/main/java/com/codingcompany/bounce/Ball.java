package com.codingcompany.bounce;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * A single ball that knows its own position, velocity, size and color,
 * and how to move and bounce itself off the edges of a bounding
 * rectangle.
 *
 * <p>Each call to {@link #move(Rectangle)} advances the ball by its
 * current velocity ({@code dx}, {@code dy}) and then checks whether the
 * ball has crossed any edge of the supplied {@code bounds}. If it has,
 * the ball is reflected back inside the bounds and the velocity
 * component responsible for the collision is reversed, producing a
 * bounce.</p>
 *
 * @author Coding Company
 */
public class Ball {

    private double x;
    private double y;
    private double dx;
    private double dy;
    private final int size;
    private final Color color;

    /**
     * Creates a ball with the given starting position, velocity, size
     * and color.
     *
     * @param x     starting x position, in pixels
     * @param y     starting y position, in pixels
     * @param dx    horizontal velocity, in pixels per frame
     * @param dy    vertical velocity, in pixels per frame
     * @param size  diameter of the ball, in pixels
     * @param color fill color used when drawing the ball
     */
    public Ball(double x, double y, double dx, double dy, int size, Color color) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.size = size;
        this.color = color;
    }

    /**
     * Advances the ball by its current velocity, then checks whether it
     * has crossed any edge of {@code bounds}. If so, the ball is
     * reflected back inside the bounds and the velocity component
     * responsible for the collision is reversed.
     *
     * @param bounds the rectangle the ball must stay inside
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

    /**
     * Draws the ball as a filled oval using the given graphics context.
     *
     * @param g the graphics context to draw into
     */
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval((int) x, (int) y, size, size);
    }

    /**
     * Returns the ball's current x position.
     *
     * @return the x position, in pixels
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the ball's current y position.
     *
     * @return the y position, in pixels
     */
    public double getY() {
        return y;
    }

    /**
     * Returns the ball's current horizontal velocity.
     *
     * @return the horizontal velocity, in pixels per frame
     */
    public double getDx() {
        return dx;
    }

    /**
     * Returns the ball's current vertical velocity.
     *
     * @return the vertical velocity, in pixels per frame
     */
    public double getDy() {
        return dy;
    }

    /**
     * Returns the ball's diameter.
     *
     * @return the diameter, in pixels
     */
    public int getSize() {
        return size;
    }
}
