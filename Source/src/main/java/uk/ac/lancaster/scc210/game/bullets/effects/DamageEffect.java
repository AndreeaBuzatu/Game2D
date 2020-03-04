package uk.ac.lancaster.scc210.game.bullets.effects;

import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.game.ecs.component.LivesComponent;

/**
 * The type Damage effect.
 */
public abstract class DamageEffect extends BulletEffect {
    private int lives;

    /**
     * Instantiates a new Damage effect.
     *
     * @param lives the lives
     */
    DamageEffect(int lives){
        this.lives = lives;
    }

    @Override
    public void react(Entity entity) {
        super.react(entity);

        LivesComponent livesComponent = (LivesComponent) entity.findComponent(LivesComponent.class);

        livesComponent.setLives(livesComponent.getLives() - lives);
    }

    /**
     * Gets lives.
     *
     * @return the lives
     */
    public int getLives() {
        return lives;
    }

    /**
     * Sets lives.
     *
     * @param lives the lives
     */
    public void setLives(int lives) {
        this.lives = lives;
    }

    @Override
    public void reset() {
    }

    @Override
    public void update(Time deltaTime) { }
}
