package uk.ac.lancaster.scc210.game.ecs.component;

import uk.ac.lancaster.scc210.engine.ecs.Component;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.game.bullets.effects.BulletEffect;
import uk.ac.lancaster.scc210.game.bullets.effects.LivesEffect;

public class BulletComponent implements Component {
    private final Entity creator;

    private BulletEffect bulletEffect;

    public BulletComponent(Entity creator) {
        this.creator = creator;
        bulletEffect = new LivesEffect();
    }

    public Entity getCreator() {
        return creator;
    }

    public BulletEffect getBulletEffect() {
        return bulletEffect;
    }
}
