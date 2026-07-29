# Bounce

![CI](https://github.com/mrgjhaaws/arithmetic-calculator/actions/workflows/ci.yml/badge.svg)

A bouncing-ball animation built with Java Swing — the first game
project in this repository, and the first in the **Coding Company**
Java game-programming series.

It's a modern take on the classic "bouncing ball" starter exercise
found in beginner Java game-programming material: a `Ball` with
position and velocity moves across the screen each frame and reverses
direction whenever it hits an edge, giving the appearance of bouncing
off the walls.

Since applets are long deprecated and no longer run in any modern
browser, this version uses `javax.swing` (a `JPanel` animated with a
`Timer`, shown in a `JFrame`) instead of the old `java.applet.Applet`
approach. Swing panels are double-buffered automatically, which serves
the same flicker-free purpose that manually drawing to an offscreen
image used to serve.

## Project layout

```
bouncing-ball/
├── pom.xml
├── src/
│   ├── main/java/com/codingcompany/bounce/
│   │   ├── Ball.java       # position, velocity, size, color, move/bounce logic
│   │   └── Bounce.java     # JPanel animation loop + main() entry point
│   └── test/java/com/codingcompany/bounce/
│       └── BallTest.java   # JUnit 5 tests for movement & edge-bounce behavior
├── docs/
│   ├── uml/
│   │   ├── class-diagram.puml
│   │   └── class-diagram.png
│   └── infographic.png
└── (CI workflow lives at repo root: .github/workflows/ci.yml)
```

## Building and running

Requires Java 21 and Maven.

```bash
cd bouncing-ball
mvn package
java -cp target/bounce.jar com.codingcompany.bounce.Bounce
```

A window opens showing a blue ball bouncing around a gray/light-gray
checkerboard background.

## Tests

```bash
mvn test
```

`BallTest` covers straight-line movement, bouncing off each of the
four edges, the "already moving away from an edge" fail-safe case, and
that the ball stays fully inside its bounds over hundreds of
simulated frames.

## Javadoc

Every public class and method is documented. Generate the HTML docs
with:

```bash
mvn javadoc:javadoc
# output in target/site/apidocs/index.html
```

## UML

`docs/uml/class-diagram.puml` is the source-of-truth class diagram
(open with any PlantUML renderer); `docs/uml/class-diagram.png` is a
rendered copy for quick viewing. It shows `Bounce` composing a `Ball`
and extending `JPanel`.

## Continuous Integration

`.github/workflows/ci.yml` (repo root) builds the project and runs the
test suite with Maven on every push and pull request that touches this
folder.

## Credits

Inspired by the "Follow the Bouncing Ball" chapter of *Java Game
Programming For Dummies* (Holder & Bell, 1998) — reimplemented from
scratch for modern Java/Swing rather than reproduced from the book.
