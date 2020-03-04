package uk.ac.lancaster.scc210.game.items;

import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.game.bullets.effects.DegradingLivesEffect;
import uk.ac.lancaster.scc210.game.ecs.component.PlayerComponent;

/**
 * The type Degrading lives bullet effect item.
 */
public class DegradingLivesBulletEffectItem extends TimedItemEffect {
    private final Time SECOND = Time.getSeconds(1);

    private Time elapsedSeconds;

    private PlayerComponent playerComponent;

    private DegradingLivesEffect degradingLivesEffect;

    /**
     * Instantiates a new Degrading lives bullet effect item.
     */
    public DegradingLivesBulletEffectItem() {
        super(Time.getSeconds(10));

        elapsedSeconds = Time.ZERO;
    }

    @Override
    public void update(Time deltaTime) {
        super.update(deltaTime);

        elapsedSeconds = Time.add(elapsedSeconds, deltaTime);

        if (isDead) {
            playerComponent.setBulletEffectToDefault();

            return;
        }

        if (playerComponent != null && elapsedSeconds.asSeconds() >= SECOND.asSeconds()) {
            elapsedSeconds = Time.ZERO;

            if (degradingLivesEffect.getLives() >= 0) {
                degradingLivesEffect.setLives(degradingLivesEffect.getLives() - 1);
            }
        }
    }

    @Override
    public void react(Entity entity) {
        super.react(entity);

        if (entity.hasComponent(PlayerComponent.class)) {
            playerComponent = (PlayerComponent) entity.findComponent(PlayerComponent.class);

            degradingLivesEffect = new DegradingLivesEffect(duration);

            playerComponent.setBulletEffect(degradingLivesEffect);
        }
    }

    @Override
    public void reset(Entity entity) {
        super.reset(entity);

        elapsedSeconds = duration;
    }
}
