package uk.ac.lancaster.scc210.game.bullets.patterns;

import org.jsfml.graphics.Sprite;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.pooling.Pool;
import uk.ac.lancaster.scc210.game.ecs.component.BulletComponent;
import uk.ac.lancaster.scc210.game.ecs.component.FiredComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;

/**
 * The type Straight line bullet pattern.
 */
public class StraightLineBulletPattern extends BulletPattern {
    private static final Time FIRING_GAP = Time.getSeconds(1);

    private static final int NUM_BULLETS = 1;

    private final int BULLET_Y_PADDING = -20;

    /**
     * Instantiates a new Straight line bullet pattern.
     *
     * @param pool       the pool
     * @param spaceShip  the space ship
     * @param bulletName the bullet name
     */
    public StraightLineBulletPattern(Pool pool, Entity spaceShip, String bulletName) {
        super(pool, spaceShip, new Entity[NUM_BULLETS], bulletName, FIRING_GAP);
    }

    @Override
    public Entity[] create() {
        toFire[NUM_BULLETS - 1] = pool.borrowEntity(spawnedEntityName);

        toFire[NUM_BULLETS - 1].addComponent(new BulletComponent(spaceShip));

        toFire[NUM_BULLETS - 1].addComponent(new FiredComponent());

        Sprite bulletSprite = ((SpriteComponent) toFire[NUM_BULLETS - 1].findComponent(SpriteComponent.class)).getSprite();

        position(bulletSprite);

        return toFire;
    }

    @Override
    public void position(Sprite toSpawnSprite) {
        // Find the half-width of the entity sprite and the half-width of the bullet sprite
        float halfWidth = (spaceShipSprite.getLocalBounds().width / 2) - (toSpawnSprite.getLocalBounds().width / 2);

        Vector2f middleCentre = new Vector2f(halfWidth, BULLET_Y_PADDING);

        coords[NUM_BULLETS - 1] = spaceShipSprite.getTransform().transformPoint(middleCentre);

        toSpawnSprite.setPosition(coords[NUM_BULLETS - 1]);

        toSpawnSprite.setRotation(spaceShipSprite.getRotation());
    }
}
