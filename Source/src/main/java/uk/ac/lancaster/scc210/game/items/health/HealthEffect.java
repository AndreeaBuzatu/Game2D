package uk.ac.lancaster.scc210.game.items.health;

import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.game.ecs.component.LivesComponent;
import uk.ac.lancaster.scc210.game.items.PowerUpEffect;

/**
 * The type Health effect.
 */
public abstract class HealthEffect extends PowerUpEffect {
    private final int healthIncrease;

    /**
     * Instantiates a new Health effect.
     *
     * @param healthIncrease the health increase
     */
    HealthEffect(int healthIncrease) {
        this.healthIncrease = healthIncrease;
    }

    @Override
    public void react(Entity entity) {
        super.react(entity);

        if (entity.hasComponent(LivesComponent.class)) {
            LivesComponent livesComponent = (LivesComponent) entity.findComponent(LivesComponent.class);

            int newLives = livesComponent.getLives() + healthIncrease ;

            livesComponent.setLives(newLives);
        }
    }
}