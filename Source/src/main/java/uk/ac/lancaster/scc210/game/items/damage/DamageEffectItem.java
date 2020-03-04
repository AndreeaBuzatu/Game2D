package uk.ac.lancaster.scc210.game.items.damage;

import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.game.bullets.effects.DamageEffect;
import uk.ac.lancaster.scc210.game.ecs.component.PlayerComponent;
import uk.ac.lancaster.scc210.game.items.TimedItemEffect;

/**
 * The type Damage effect item.
 */
public abstract class DamageEffectItem extends TimedItemEffect {
    private final DamageEffect damageEffect;

    /**
     * Instantiates a new Damage effect item.
     *
     * @param damageEffect the damage effect
     */
    DamageEffectItem(DamageEffect damageEffect) {
        super(Time.getSeconds(5));

        this.damageEffect = damageEffect;
    }

    @Override
    public void react(Entity entity) {
        super.react(entity);

        if (entity.hasComponent(PlayerComponent.class)) {
            PlayerComponent playerComponent = (PlayerComponent) entity.findComponent(PlayerComponent.class);

            playerComponent.setBulletEffect(damageEffect);
        }
    }
}
