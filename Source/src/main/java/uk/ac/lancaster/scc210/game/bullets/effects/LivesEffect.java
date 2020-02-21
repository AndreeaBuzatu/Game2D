package uk.ac.lancaster.scc210.game.bullets.effects;

import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.game.ecs.component.LivesComponent;

public class LivesEffect implements BulletEffect{
    @Override
    public void react(Entity entity) {

        LivesComponent livesComponent = (LivesComponent) entity.findComponent(LivesComponent.class);
        livesComponent.setLives(livesComponent.getLives() - 1);

    }
}
