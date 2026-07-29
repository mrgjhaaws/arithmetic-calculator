package com.codingcompany.bounce;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;
import java.awt.Rectangle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link Ball}, covering straight-line movement and
 * bounce behavior off each of the four edges of a bounding rectangle.
 */
class BallTest {

    private static final double DELTA = 1e-9;
    private Rectangle bounds;

    @BeforeEach
    void setUp() {
        bounds = new Rectangle(0, 0, 100, 100);
    }

    @Nested
    @DisplayName("Straight-line movement")
    class Movement {

        @Test
        @DisplayName("moves by dx/dy when nowhere near an edge")
        void movesByVelocityWhenInsideBounds() {
            Ball ball = new Ball(10, 10, 2, 3, 5, Color.BLUE);

            ball.move(bounds);

            assertEquals(12, ball.getX(), DELTA);
            assertEquals(13, ball.getY(), DELTA);
            assertEquals(2, ball.getDx(), DELTA);
            assertEquals(3, ball.getDy(), DELTA);
        }
    }

    @Nested
    @DisplayName("Bounce behavior")
    class Bounce {

        @Test
        @DisplayName("reverses dx and stays in bounds off the left edge")
        void bouncesOffLeftEdge() {
            Ball ball = new Ball(1, 50, -5, 0, 10, Color.RED);

            ball.move(bounds);

            assertTrue(ball.getDx() > 0, "dx should reverse to positive");
            assertTrue(ball.getX() >= bounds.x, "ball should be back inside bounds");
        }

        @Test
        @DisplayName("reverses dx and stays in bounds off the right edge")
        void bouncesOffRightEdge() {
            Ball ball = new Ball(94, 50, 5, 0, 10, Color.RED);

            ball.move(bounds);

            assertTrue(ball.getDx() < 0, "dx should reverse to negative");
            assertTrue(ball.getX() + ball.getSize() <= bounds.x + bounds.width,
                    "ball should be back inside bounds");
        }

        @Test
        @DisplayName("reverses dy and stays in bounds off the top edge")
        void bouncesOffTopEdge() {
            Ball ball = new Ball(50, 1, 0, -5, 10, Color.RED);

            ball.move(bounds);

            assertTrue(ball.getDy() > 0, "dy should reverse to positive");
            assertTrue(ball.getY() >= bounds.y, "ball should be back inside bounds");
        }

        @Test
        @DisplayName("reverses dy and stays in bounds off the bottom edge")
        void bouncesOffBottomEdge() {
            Ball ball = new Ball(50, 94, 0, 5, 10, Color.RED);

            ball.move(bounds);

            assertTrue(ball.getDy() < 0, "dy should reverse to negative");
            assertTrue(ball.getY() + ball.getSize() <= bounds.y + bounds.height,
                    "ball should be back inside bounds");
        }

        @Test
        @DisplayName("does not reverse dx when already moving away from the left edge")
        void doesNotDoubleBounceAtLeftEdge() {
            // Ball starts exactly on the left edge, but already moving right (dx > 0):
            // this should NOT be treated as a left-edge collision.
            Ball ball = new Ball(0, 50, 5, 0, 10, Color.RED);

            ball.move(bounds);

            assertEquals(5, ball.getDx(), DELTA, "dx should not reverse");
        }

        @Test
        @DisplayName("stays fully inside bounds over many frames")
        void staysInBoundsOverManyFrames() {
            Ball ball = new Ball(10, 10, 7, 11, 12, Color.GREEN);

            for (int frame = 0; frame < 500; frame++) {
                ball.move(bounds);

                assertTrue(ball.getX() >= bounds.x - DELTA, "x should not go left of bounds");
                assertTrue(ball.getY() >= bounds.y - DELTA, "y should not go above bounds");
                assertTrue(ball.getX() + ball.getSize() <= bounds.x + bounds.width + DELTA,
                        "x should not go right of bounds");
                assertTrue(ball.getY() + ball.getSize() <= bounds.y + bounds.height + DELTA,
                        "y should not go below bounds");
            }
        }
    }
}
