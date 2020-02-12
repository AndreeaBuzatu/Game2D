package uk.ac.lancaster.scc210.game.bullets.patterns;

import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.pooling.Pool;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;

public class StarPattern extends Pattern {
    private static final int NUM_BULLETS = 8;

    public StarPattern(Pool pool, Entity spaceShip, String bulletName) {
        super(pool, spaceShip, new Entity[NUM_BULLETS], bulletName);

        // It doesn't matter if we waste one allocation that's okay...
        bullets[0] = pool.borrowEntity(bulletName);

        Sprite bulletSprite = ((SpriteComponent) bullets[0].findComponent(SpriteComponent.class)).getSprite();

        // Pre-determine the bullet positions
        position(bulletSprite);

        // Let's return the entity back now
        pool.returnEntity(bullets[0]);
    }

    @Override
    public Entity[] create() {
        for (int i = 0; i < NUM_BULLETS; i++) {
            bullets[i] = pool.borrowEntity(bulletName);

            Sprite bulletSprite = ((SpriteComponent) bullets[i].findComponent(SpriteComponent.class)).getSprite();

            Vector2f bulletPos = spaceShipSprite.getTransform().transformPoint(coords[i]);

            bulletSprite.setPosition(bulletPos);

            bulletSprite.setRotation(spaceShipSprite.getRotation() + (i - 1) * 45);
        }

        return bullets;
    }

    @Override
    void position(Sprite bulletSprite) {
        float startPoint = -bulletSprite.getLocalBounds().width / 2;

        float width = spaceShipSprite.getLocalBounds().width;

        float height = spaceShipSprite.getLocalBounds().height;

        float equalDistance = (float) (Math.sqrt(Math.pow(height, 2) + Math.pow(width, 2)) / Math.sqrt(2));

        float difference = (float) (equalDistance * (Math.sqrt(2) - 1) / 2);

        coords[0] = new Vector2f(startPoint, startPoint);

        coords[1] = new Vector2f(startPoint + equalDistance / 2, -difference);

        coords[2] = new Vector2f(startPoint / 2 + equalDistance, startPoint);

        coords[3] = new Vector2f(startPoint + equalDistance + difference, startPoint + equalDistance / 2);

        coords[4] = new Vector2f(equalDistance, equalDistance);

        coords[5] = new Vector2f(-startPoint + equalDistance / 2, equalDistance + difference);

        coords[6] = new Vector2f(-startPoint, width - startPoint);

        coords[7] = new Vector2f(-startPoint - difference, -startPoint + equalDistance / 2);
    }
}
