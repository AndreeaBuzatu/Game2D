package uk.ac.lancaster.game.entites;

import org.jsfml.window.Keyboard;

public class Player extends SpaceShip {
    private static final int MIN_LIVES = 0;

    private final int MAX_LIVES = 3;

    private final int SPEED = 5;

    private final int MIN_ROTATION = 0;

    private final int MAX_ROTATION = 360;

    private final int ROTATE_AMOUNT = 2;

    private int lives;

    public Player() {
        super("player.png");

        sprite.setPosition(100, 100);

        lives = MAX_LIVES;
    }

    public void update() {
        super.update();

        if (!outOfBounds()) {
            move();
        }

        if (lives < MIN_LIVES) {
            kill();
        }

        if (Keyboard.isKeyPressed(Keyboard.Key.SPACE)) {
            fireBullet();
        }
    }

    private void move() {
        float newX = 0, newY = 0;

        float rotation = sprite.getRotation();

        // We need to get input then move to prevent incorrect (read insane) movement speeds
        if (Keyboard.isKeyPressed(Keyboard.Key.W) || Keyboard.isKeyPressed(Keyboard.Key.UP)) {
            newY = -SPEED;

        } else if (Keyboard.isKeyPressed(Keyboard.Key.S) || Keyboard.isKeyPressed(Keyboard.Key.DOWN)) {
            newY = SPEED;

        } else if (Keyboard.isKeyPressed(Keyboard.Key.A) || Keyboard.isKeyPressed(Keyboard.Key.LEFT)) {
            newX = -SPEED;

        } else if (Keyboard.isKeyPressed(Keyboard.Key.D) || Keyboard.isKeyPressed(Keyboard.Key.RIGHT)) {
            newX = SPEED;

        } else if (Keyboard.isKeyPressed(Keyboard.Key.Q)) {
            if (rotation >= MAX_ROTATION) {
                sprite.setRotation(MIN_ROTATION);

            } else {
                sprite.rotate(ROTATE_AMOUNT);
            }

        } else if (Keyboard.isKeyPressed(Keyboard.Key.E)) {
            if (rotation <= MIN_ROTATION) {
                sprite.setRotation(MAX_ROTATION);

            } else {
                sprite.rotate(-ROTATE_AMOUNT);
            }
        }

        sprite.move(newX, newY);
    }

    @Override
    public void kill() {
        if (lives <= MIN_LIVES) {
            super.kill();

        } else {
            lives--;
        }
    }

    public int getLives() {
        return lives;
    }
}
