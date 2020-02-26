package uk.ac.lancaster.scc210.game.items;

import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.game.bullets.effects.Lives2Effect;
import uk.ac.lancaster.scc210.game.ecs.component.PlayerComponent;

public class Lives2BulletEffectItem extends TimedItemEffect {
    public Lives2BulletEffectItem() {
        super(Time.getSeconds(5));
    }

    @Override
    public void react(Entity entity) {
        super.react(entity);

        if (entity.hasComponent(PlayerComponent.class)) {
            PlayerComponent playerComponent = (PlayerComponent) entity.findComponent(PlayerComponent.class);

            playerComponent.setBulletEffect(new Lives2Effect());
        }
    }
}
