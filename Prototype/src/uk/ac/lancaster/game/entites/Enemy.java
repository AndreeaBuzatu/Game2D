package uk.ac.lancaster.game.entites;

import org.jsfml.system.Clock;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;

import java.util.Random;

public class Enemy extends SpaceShip {
    private final Time MOVEMENT_TIME = Time.getMilliseconds(400);

    private final int SPEED = 5;

    // Temp random movement
    private Random random;

    private Clock movementClock;

    private Direction direction;

    public Enemy(Vector2f pos) {
        super("alien.png");

        random = new Random();

        movementClock = new Clock();

        direction = chooseDirection();

        sprite.setPosition(pos);
    }

    @Override
    public void update() {
        if (alive) {
            super.update();

            if (randRange(0, 10) == 0) {
                fireBullet();
            }
        }

        if (!outOfBounds()) {
            //move();

        } else {
            direction = chooseDirection();
        }
    }

    private int randRange(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    private void move() {
        if (movementClock.getElapsedTime().asSeconds() < MOVEMENT_TIME.asSeconds()) {
            switch (direction) {
                case UP:
                    sprite.move(0, SPEED);

                    break;

                case DOWN:
                    sprite.move(0, -SPEED);

                    break;

                case LEFT:
                    sprite.move(-SPEED, 0);

                    break;

                case RIGHT:
                    sprite.move(SPEED, 0);

                    break;
            }

        } else {
            movementClock.restart();

            direction = chooseDirection();
        }
    }

    private Direction chooseDirection() {
        switch (randRange(0, 3)) {
            case 0:
                return Direction.UP;

            case 1:
                return Direction.DOWN;

            case 2:
                return Direction.LEFT;

            case 3:
                return Direction.RIGHT;

            default:
                throw new IllegalStateException("Out of range");
        }
    }
}

enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT,
}
