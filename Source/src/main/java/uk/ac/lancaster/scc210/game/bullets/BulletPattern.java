package uk.ac.lancaster.scc210.game.bullets;

import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.pooling.Pool;
import uk.ac.lancaster.scc210.game.patterns.Pattern;

abstract class BulletPattern extends Pattern {
    final Pool pool;

    BulletPattern(Pool pool, Entity entity, Entity[] bullets, String bulletName, Time firingGap) {
        super(entity, bullets, bulletName, firingGap);

        this.pool = pool;
    }
}
