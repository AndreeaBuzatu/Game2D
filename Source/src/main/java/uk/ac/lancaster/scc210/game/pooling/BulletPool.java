package uk.ac.lancaster.scc210.game.pooling;

import uk.ac.lancaster.scc210.engine.content.TextureManager;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.pooling.Pool;
import uk.ac.lancaster.scc210.engine.service.ServiceProvider;
import uk.ac.lancaster.scc210.game.content.BulletPrototypeManager;
import uk.ac.lancaster.scc210.game.ecs.component.SpeedComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;
import uk.ac.lancaster.scc210.game.prototypes.BulletPrototype;

/**
 * Pool which stores bullets.
 */
public class BulletPool extends Pool {
    // Who cares if it's static? I don't
    private static final int INITIAL_BULLETS = 5000;

    private final TextureManager textureManager;

    private final BulletPrototypeManager bulletPrototypeManager;

    /**
     * Instantiate a BulletPool
     *
     * @param serviceProvider used to access the PoolService
     */
    public BulletPool(ServiceProvider serviceProvider) {
        // Default to player-shoot
        super(INITIAL_BULLETS, ((BulletPrototypeManager) serviceProvider.get(BulletPrototypeManager.class)).get("player-shoot"));

        bulletPrototypeManager = (BulletPrototypeManager) serviceProvider.get(BulletPrototypeManager.class);

        textureManager = (TextureManager) serviceProvider.get(TextureManager.class);
    }

    @Override
    public Entity borrowEntity(String bulletName) {
        if (!entities.isEmpty()) {
            Entity entity = entities.remove();

            // Set the texture for the given bullet name
            SpriteComponent spriteComponent = (SpriteComponent) entity.findComponent(SpriteComponent.class);

            spriteComponent.getSprite().setTexture(textureManager.get(bulletName));
        }

        return create(bulletName);
    }

    @Override
    protected Entity create(String bulletName) {
        Entity entity = super.borrowEntity();

        BulletPrototype bulletPrototype = bulletPrototypeManager.get(bulletName);

        SpriteComponent spriteComponent = (SpriteComponent) entity.findComponent(SpriteComponent.class);

        SpeedComponent speedComponent = (SpeedComponent) entity.findComponent(SpeedComponent.class);

        spriteComponent.getSprite().setTexture(textureManager.get(bulletPrototype.getTexture()));

        speedComponent.setSpeed(bulletPrototype.getSpeed());

        return entity;
    }
}
