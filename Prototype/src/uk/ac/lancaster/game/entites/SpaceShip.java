package uk.ac.lancaster.game.entites;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Clock;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;

import java.util.ArrayList;

public class SpaceShip extends Entity {
    private final Time FIRING_GAP = Time.getMilliseconds(70);

    protected ArrayList<Bullet> projectiles;

    protected Clock firingTimer;

    SpaceShip(String fileName) {
        super(fileName);

        projectiles = new ArrayList<>();

        firingTimer = new Clock();

        // Rotate around the front of the sprite, not the middle
        //sprite.setOrigin(sprite.getLocalBounds().width / 2, -sprite.getLocalBounds().height);
    }

    @Override
    public void update() {
        projectiles.removeIf(Entity::outOfBounds);

        for (Bullet projectile : projectiles) {
            projectile.update();
        }
    }

    public void handleCollision(ArrayList<Entity> entities) {
        for (Bullet projectile : projectiles) {
            for (Entity entity : entities) {
                if (projectile.intersects(entity)) {
                    projectile.kill();

                    entity.kill();
                }
            }
        }
    }

    protected void fireBullet() {
        if (firingTimer.getElapsedTime().asSeconds() > FIRING_GAP.asSeconds()) {
            Vector2f spritePos = sprite.getPosition();

            Vector2f rotatedPos = sprite.getTransform().transformPoint(spritePos);

            float rotation = sprite.getRotation();

            float angleX = (float) Math.cos(Math.toRadians(rotation));

            float angleY = (float) Math.sin(Math.toRadians(rotation));

            /*
            Vector2f test = new Vector2f(
                    spritePos.x + angleX,
                    spritePos.y + angleY
            );
             */

            Vector2f test = new Vector2f(200, 200);

            //System.out.println("Actual: " + sprite.getPosition() + "Rotated: " + sprite.getTransform().transformPoint(spritePos));

            projectiles.add(new Bullet(test, rotation));

            firingTimer.restart();
        }
    }

    @Override
    public void draw(RenderTarget target) {
        super.draw(target);

        for (Bullet projectile : projectiles) {
            projectile.draw(target);
        }
    }
}
