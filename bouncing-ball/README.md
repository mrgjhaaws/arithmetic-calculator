# Bounce

A simple bouncing-ball animation written in Java, and the first game
project in this repository.

It's a modern take on the classic "bouncing ball" starter exercise found
in beginner Java game-programming material: a `Ball` with position and
velocity moves across the screen each frame and reverses direction
whenever it hits an edge, giving the appearance of bouncing off the
walls.

Since applets are long deprecated and no longer run in modern browsers,
this version uses `javax.swing` (a `JPanel` animated with a `Timer`,
shown in a `JFrame`) instead of the old `java.applet.Applet` approach.
Swing panels are double-buffered automatically, which serves the same
flicker-free purpose that manually drawing to an offscreen image used
to serve.

## Files

- `Ball.java` — position, velocity, size, and color for the ball, plus
  the logic to move it and bounce it off a bounding rectangle.
- `Bounce.java` — the animation loop, checkerboard background, and the
  `main` method that opens the window.

## Running it

```bash
javac Ball.java Bounce.java
java Bounce
```

A window opens showing a blue ball bouncing around a gray/light-gray
checkerboard background.
