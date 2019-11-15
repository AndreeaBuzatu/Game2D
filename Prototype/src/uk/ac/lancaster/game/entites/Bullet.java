package uk.ac.lancaster.game.entites;

import org.jsfml.system.Vector2f;

public class Bullet extends Entity {
    private final int SPEED = 5;

    Bullet(Vector2f pos, float rotation) {
        super("bullet.png");

        if (pos != null) {
            sprite.setPosition(pos);
        }

        sprite.setRotation(rotation);
    }

    @Override
    public void update() {
        float rotation = sprite.getRotation();

        sprite.move(SPEED * (float) Math.sin(Math.toRadians(rotation)), SPEED * (float) -Math.cos(Math.toRadians(rotation)));
    }
}
