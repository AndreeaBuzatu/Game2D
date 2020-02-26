package uk.ac.lancaster.scc210.game.bullets.effects;

import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.game.bullets.effects.BulletEffect;
import uk.ac.lancaster.scc210.game.ecs.component.LivesComponent;

public abstract class  LivesEffect extends BulletEffect {

    private final int lives;

    LivesEffect(int lives){
        this.lives = lives;
    }

    @Override
    public void react(Entity entity) {
        super.react(entity);

        LivesComponent livesComponent = (LivesComponent) entity.findComponent(LivesComponent.class);

        livesComponent.setLives(livesComponent.getLives() - lives);
    }



    @Override
    public void reset() {
    }

    @Override
    public void update(Time deltaTime) { }
}
