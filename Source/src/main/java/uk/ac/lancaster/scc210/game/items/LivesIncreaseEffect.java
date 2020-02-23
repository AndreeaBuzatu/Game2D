package uk.ac.lancaster.scc210.game.items;

import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.game.ecs.component.LivesComponent;

public class LivesIncreaseEffect extends PowerUpEffect {
    @Override
    public void react(Entity entity) {
        super.react(entity);

        if (entity.hasComponent(LivesComponent.class)) {
            LivesComponent livesComponent = (LivesComponent) entity.findComponent(LivesComponent.class);

            int newLives = livesComponent.getLives() + 1;

            livesComponent.setLives(newLives);
        }
    }
}
