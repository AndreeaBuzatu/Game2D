package uk.ac.lancaster.scc210.game.bullets.patterns;

import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.pooling.Pool;
import uk.ac.lancaster.scc210.game.patterns.Pattern;

public abstract class BulletPattern extends Pattern {
    final Pool pool;

    BulletPattern(Pool pool, Entity entity, Entity[] bullets, String bulletName) {
        super(entity, bullets, bulletName);

        this.pool = pool;
    }
}
