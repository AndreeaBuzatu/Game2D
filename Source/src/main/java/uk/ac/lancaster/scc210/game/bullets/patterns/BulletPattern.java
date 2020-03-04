package uk.ac.lancaster.scc210.game.bullets.patterns;

import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.pooling.Pool;
import uk.ac.lancaster.scc210.game.patterns.Pattern;

/**
 * The type Bullet pattern.
 */
abstract class BulletPattern extends Pattern {
    /**
     * The Pool.
     */
    final Pool pool;

    /**
     * Instantiates a new Bullet pattern.
     *
     * @param pool       the pool
     * @param entity     the entity
     * @param bullets    the bullets
     * @param bulletName the bullet name
     * @param firingGap  the firing gap
     */
    BulletPattern(Pool pool, Entity entity, Entity[] bullets, String bulletName, Time firingGap) {
        super(entity, bullets, bulletName, firingGap);

        this.pool = pool;
    }
}
