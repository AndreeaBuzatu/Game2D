package uk.ac.lancaster.scc210.game.bullets.patterns;

import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.pooling.Pool;
import uk.ac.lancaster.scc210.game.ecs.component.BulletComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;

public class StraightLinePattern extends Pattern {
    private static final int NUM_BULLETS = 1;

    private final int BULLET_Y_PADDING = -20;

    public StraightLinePattern(Pool pool, Entity spaceShip, String bulletName) {
        super(pool, spaceShip, new Entity[NUM_BULLETS], bulletName);
    }

    @Override
    public Entity[] create() {
        bullets[NUM_BULLETS - 1] = pool.borrowEntity(bulletName);

        bullets[NUM_BULLETS - 1].addComponent(new BulletComponent(entity));

        Sprite bulletSprite = ((SpriteComponent) bullets[NUM_BULLETS - 1].findComponent(SpriteComponent.class)).getSprite();

        position(bulletSprite);

        return bullets;
    }

    @Override
    void position(Sprite bulletSprite) {
        // Find the half-width of the entity sprite and the half-width of the bullet sprite
        float halfWidth = (spaceShipSprite.getLocalBounds().width / 2) - (bulletSprite.getLocalBounds().width / 2);

        Vector2f middleCentre = new Vector2f(halfWidth, BULLET_Y_PADDING);

        coords[NUM_BULLETS - 1] = spaceShipSprite.getTransform().transformPoint(middleCentre);

        bulletSprite.setPosition(coords[NUM_BULLETS - 1]);

        bulletSprite.setRotation(spaceShipSprite.getRotation());
    }
}
