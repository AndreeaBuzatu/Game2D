package uk.ac.lancaster.scc210.game.bullets.effects;

import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.game.ecs.component.LivesComponent;

public class Lives2Effect extends BulletEffect {
    @Override
    public void react(Entity entity) {
        super.react(entity);

        LivesComponent livesComponent = (LivesComponent) entity.findComponent(LivesComponent.class);

        livesComponent.setLives(livesComponent.getLives() - 2);
    }

    @Override
    public void reset() {
    }

    @Override
    public void update(Time deltaTime) { }
}
