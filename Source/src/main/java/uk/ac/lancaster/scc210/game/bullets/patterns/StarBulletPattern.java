package uk.ac.lancaster.scc210.game.bullets.patterns;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.pooling.Pool;
import uk.ac.lancaster.scc210.game.ecs.component.BulletComponent;
import uk.ac.lancaster.scc210.game.ecs.component.FiredComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;

public class StarBulletPattern extends BulletPattern {
    private static final Time FIRING_GAP = Time.getSeconds(3);

    private static final int NUM_BULLETS = 8;

    public StarBulletPattern(Pool pool, Entity spaceShip, String bulletName) {
        super(pool, spaceShip, new Entity[NUM_BULLETS], bulletName, FIRING_GAP);

        // It doesn't matter if we waste one allocation that's okay...
        toFire[0] = pool.borrowEntity(bulletName);

        Sprite bulletSprite = ((SpriteComponent) toFire[0].findComponent(SpriteComponent.class)).getSprite();

        // Pre-determine the bullet positions
        position(bulletSprite);

        // Let's return the entity back now
        pool.returnEntity(toFire[0]);
    }

    @Override
    public Entity[] create() {
        for (int i = 0; i < NUM_BULLETS; i++) {
            toFire[i] = pool.borrowEntity(spawnedEntityName);

            toFire[i].addComponent(new BulletComponent(spaceShip));

            toFire[i].addComponent(new FiredComponent());

            Sprite bulletSprite = ((SpriteComponent) toFire[i].findComponent(SpriteComponent.class)).getSprite();

            Vector2f bulletPos = spaceShipSprite.getTransform().transformPoint(coords[i]);

            bulletSprite.setPosition(bulletPos);

            bulletSprite.setRotation(spaceShipSprite.getRotation() + (i - 1) * 45);
        }

        return toFire;
    }

    @Override
    public void position(Sprite bulletSprite) {
        FloatRect localBounds = bulletSprite.getLocalBounds();

        positionStarPatterns(bulletSprite, localBounds);
    }
}
