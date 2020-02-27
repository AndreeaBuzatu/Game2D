package uk.ac.lancaster.scc210.game.items;

import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.game.ecs.component.LivesComponent;

public abstract class HealthEffectItem extends PowerUpEffect {

    private final int healthIncrease;

    HealthEffectItem(int healthIncrease) {
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