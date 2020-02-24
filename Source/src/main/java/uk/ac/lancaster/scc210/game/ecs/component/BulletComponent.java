package uk.ac.lancaster.scc210.game.ecs.component;

import uk.ac.lancaster.scc210.engine.ecs.Component;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.game.bullets.effects.BulletEffect;
import uk.ac.lancaster.scc210.game.bullets.effects.FreezeBulletEffect;
import uk.ac.lancaster.scc210.game.bullets.effects.LivesEffect;

public class BulletComponent implements Component {
    private final Entity creator;

    private final BulletEffect defaultEffect;

    private BulletEffect bulletEffect;

    public BulletComponent(Entity creator) {
        this.creator = creator;

        //bulletEffect = new LivesEffect();

        bulletEffect = new FreezeBulletEffect();

        defaultEffect = new LivesEffect();
    }

    public Entity getCreator() {
        return creator;
    }

    public BulletEffect getBulletEffect() {
        return bulletEffect;
    }

    public void setBulletEffectToDefault() {
        this.bulletEffect = defaultEffect;

        defaultEffect.setDead(false);

        if (bulletEffect == defaultEffect) {
            System.out.println("Set to default");
        }
    }
}
