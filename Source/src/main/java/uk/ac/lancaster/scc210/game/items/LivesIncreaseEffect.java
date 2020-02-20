package uk.ac.lancaster.scc210.game.items;

import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.game.ecs.component.LivesComponent;

public class LivesIncreaseEffect implements ItemEffect {

    @Override
    public void react(Entity entity) {
        if (entity.hasComponent(LivesComponent.class)) {
            LivesComponent livesComponent = (LivesComponent) entity.findComponent(LivesComponent.class);

            int newLives = livesComponent.getLives() + 1;

            livesComponent.setLives(newLives);
        }
    }

    @Override
    public void reset(Entity entity) {
        //TODO - does anything need to go here? Lives reset at death anyway
    }
}
