package uk.ac.lancaster.scc210.game.bullets.effects;

import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.game.ecs.component.EnemyComponent;
import uk.ac.lancaster.scc210.game.ecs.component.LivesComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpeedComponent;

public class FreezeBulletEffect extends TimedBulletEffect {
    private int oldSpeed;

    public FreezeBulletEffect() {
        super(Time.getSeconds(10));

        oldSpeed = -1;
    }

    @Override
    public void react(Entity entity) {
        super.react(entity);

        if (entity.hasComponent(SpeedComponent.class)) {
            SpeedComponent speedComponent = (SpeedComponent) entity.findComponent(SpeedComponent.class);

            if (oldSpeed == -1) {
                oldSpeed = speedComponent.getSpeed();
            }

            speedComponent.setSpeed(0);

            reactedEntities.add(entity);
        }

        // Kill the player if they only have 1 life
        if (entity.hasComponent(LivesComponent.class)) {
            LivesComponent livesComponent = (LivesComponent) entity.findComponent(LivesComponent.class);

            if (livesComponent.getLives() <= 1) {
                livesComponent.kill();
            }
        }

        if (entity.hasComponent(EnemyComponent.class)) {
            EnemyComponent enemyComponent = (EnemyComponent) entity.findComponent(EnemyComponent.class);

            enemyComponent.setFiring(false);
        }
    }

    @Override
    public void reset() {
        reactedEntities.forEach(entity -> {
            if (entity.hasComponent(SpeedComponent.class)) {
                SpeedComponent speedComponent = (SpeedComponent) entity.findComponent(SpeedComponent.class);

                speedComponent.setSpeed(oldSpeed);
            }

            if (entity.hasComponent(EnemyComponent.class)) {
                EnemyComponent enemyComponent = (EnemyComponent) entity.findComponent(EnemyComponent.class);

                enemyComponent.setFiring(true);
            }
        });

        reactedEntities.clear();
    }
}
