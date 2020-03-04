package uk.ac.lancaster.scc210.game.items;

import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.game.bullets.effects.FreezeBulletEffect;
import uk.ac.lancaster.scc210.game.ecs.component.PlayerComponent;

/**
 * The type Freeze bullet effect item.
 */
public class FreezeBulletEffectItem extends PowerUpEffect {
    @Override
    public void react(Entity entity) {
        super.react(entity);

        if (entity.hasComponent(PlayerComponent.class)) {
            PlayerComponent playerComponent = (PlayerComponent) entity.findComponent(PlayerComponent.class);

            playerComponent.setBulletEffect(new FreezeBulletEffect());
        }
    }
}
