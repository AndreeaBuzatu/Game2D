package uk.ac.lancaster.scc210.game.items;

import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.game.ecs.component.SpeedComponent;

public class SpeedIncreaseEffect implements ItemEffect {
    @Override
    public void react(Entity entity) {
        if (entity.hasComponent(SpeedComponent.class)) {
            SpeedComponent speedComponent = (SpeedComponent) entity.findComponent(SpeedComponent.class);

            speedComponent.setSpeed(20);
        }
    }
}
